package cn.young.im.springboot.starter.adapter.registry.adapter;

import cn.young.im.common.exception.YoungImException;
import cn.young.im.springboot.starter.adapter.registry.Instance;
import cn.young.im.springboot.starter.adapter.registry.InstanceRegistryService;
import cn.young.im.springboot.starter.adapter.registry.config.NacosConfig;
import cn.young.im.springboot.starter.adapter.registry.config.RegisterConfig;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Nacos 实例注册服务
 * @date 2023/12/10
 */
@Slf4j
public class NacosInstanceRegistryService implements InstanceRegistryService {

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

    }

    @Override
    public void registry(Instance instance) {

    }

    @Override
    public List<Instance> listOfInstance(String tenant) {
        try {
            namingService.selectInstances(tenant, group, true);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
