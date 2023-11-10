package cn.young.im.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName WebClientConfig
 * @Description 配置 WebFlux，配置WebClient。
 * @date 2023/10/7 9:32
 * @Author yanceysong
 * @Version 1.0
 */
@Configuration
public class WebClientConfig {
    /**
     * URLConnection's write timeout (in milliseconds).
     */
    @Value("${http.write-timeout:3000}")
    private Integer writeTimeout;
    /**
     * URLConnection's connect timeout (in milliseconds).
     */
    @Value("${http.connect-timeout:3000}")
    private Integer connectTimeout;
    /**
     * URLConnection's read timeout (in milliseconds).
     */
    @Value("${http.read-timeout:3000}")
    private Integer readTimeout;
    /**
     * 缓冲区最大内存，单位mb
     */
    @Value("${http.max-buffer-size:10}")
    private Integer maxBufferSize;

    /**
     * 注入 WebClient
     *
     * @param objectMapper ObjectMapper实例
     * @return WebClient 实例
     * @see WebClient
     */
    @Bean("webClient")
    public WebClient webClient(ObjectMapper objectMapper) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS))
                );
//        HttpClient httpClient = HttpClient.create().tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
//                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
//                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS))));
        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs((ClientCodecConfigurer clientDefaultCodecsConfigurer) -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().maxInMemorySize(Math.toIntExact(1024L * 1024 * maxBufferSize));
                })
                .build();
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
