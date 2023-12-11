package cn.young.im.springboot.starter.adapter.registry.adapter;

import cn.young.im.common.exception.YoungImException;
import cn.young.im.common.util.IpUtils;
import cn.young.im.spi.Join;
import cn.young.im.springboot.starter.adapter.registry.InstanceEntity;
import cn.young.im.springboot.starter.adapter.registry.InstanceRegistryService;
import cn.young.im.springboot.starter.adapter.registry.Status;
import cn.young.im.springboot.starter.adapter.registry.annotation.AutomaticRegistry;
import cn.young.im.springboot.starter.adapter.registry.config.NacosConfig;
import cn.young.im.springboot.starter.adapter.registry.config.RegisterConfig;
import cn.young.im.util.AnnotationScanner;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static cn.young.im.common.constants.Const.COLONS;
import static cn.young.im.common.constants.YoungConst.BASE_PACKAGE;
import static cn.young.im.common.constants.YoungConst.DEFAULT;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Nacos 实例注册服务
 * @date 2023/12/10
 */
@Slf4j
@Join
public class NacosInstanceRegistryService implements
        InstanceRegistryService, EnvironmentAware {

    private ConfigurableEnvironment environment;

    private NamingService namingService;

    private String group;


    @Override
    public void init(RegisterConfig config) {
        // 1. 提取配置
        NacosConfig nacos = config.getNacos();
        this.group = nacos.getGroup();

        // 2. 装填配置
        Properties properties = new Properties();
        properties.put("serverAddr", config.getServerLists());
        properties.put("namespace", nacos.getNamespace());
        properties.put("username", nacos.getUsername());
        properties.put("password", nacos.getPassword());
        properties.put("group", group);

        try {
            // 3. 实例化
            this.namingService = NamingFactory.createNamingService(properties);
        } catch (NacosException e) {
            log.error("init nacos registry center  occur error");
            throw new YoungImException(e);
        }

        // 4. 提取注解发起注册
        Map<Class<?>, Annotation> classAnnotationMap =
                AnnotationScanner.scannerClassByAnnotation(BASE_PACKAGE, AutomaticRegistry.class);
        for (Annotation annotation : classAnnotationMap.values()) {
            AutomaticRegistry registry = (AutomaticRegistry) annotation;

            String serviceName = registry.serviceName().equals(DEFAULT)
                    ? environment.getProperty("spring.application.name") : registry.serviceName();
            String port = registry.port().equals(DEFAULT)
                    ? environment.getProperty("local.server.port") : registry.port();

            String host = registry.host().equals(DEFAULT) ? IpUtils.getHost() : registry.host();
            double weight = registry.weight();

            assert port != null;

            InstanceEntity instanceEntity = InstanceEntity.builder().serviceName(serviceName)
                    .host(host)
                    .port(Integer.parseInt(port))
                    .weight(weight)
                    .build();

            registry(instanceEntity);
        }
    }

    @Override
    public void registry(final InstanceEntity instance) {
        try {
            Instance inst = new Instance();
            inst.setWeight(instance.getWeight());
            inst.setEphemeral(true);
            inst.setIp(instance.getHost());
            inst.setPort(instance.getPort());
            inst.setInstanceId(buildInstanceNodeName(instance));
            inst.setServiceName(instance.getServiceName());

            namingService.registerInstance(instance.getServiceName(), group, inst);
        } catch (NacosException e) {
            log.error("service registry error: ", e);
            throw new YoungImException(e);
        }
    }

    @Override
    public List<InstanceEntity> listOfInstance(String tenant) {
        return listOfInstance(tenant, group, true);
    }

    public List<InstanceEntity> listOfInstance(String tenant, String group, boolean healthy) {
        List<InstanceEntity> result = new ArrayList<>();
        try {
            List<Instance> instances = namingService.selectInstances(tenant, group, healthy);
            instances.forEach(instance -> result.add(convertFromInstance(instance)));
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    private String buildInstanceNodeName(final InstanceEntity instance) {
        String host = instance.getHost();
        int port = instance.getPort();
        return String.join(COLONS, host, Integer.toString(port));
    }

    private InstanceEntity convertFromInstance(final Instance instance) {
        return InstanceEntity.builder()
                .port(instance.getPort())
                .host(instance.getIp())
                .weight(instance.getWeight())
                .serviceName(instance.getServiceName())
                .status(Status.HEALTH)
                .build();
    }
}
