package cn.young.im.young.im.netty.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ImNettyConfig
 * @Description ImNetty模块启动配置文件
 * @date 2023/11/15 14:14
 * @Author yanceysong
 * @Version 1.0
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "im-netty-config")
public class ImNettyConfig {
    /**
     * websocket 端口号
     */
    private Integer WebSocketPort;
    /**
     * 心跳超时时间 单位ms
     */
    private Long heartBeatTime;
}
