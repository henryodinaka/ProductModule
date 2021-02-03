package com.inits.productservice.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Configuration
public class AppConfig {

    @Value("${spring.redis.host}")
    private String redisHostName;

    @Autowired
    private Environment env;


    @Bean
    public RestTemplate getResTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectionRequestTimeout(50000);
        clientHttpRequestFactory.setReadTimeout(180000);
        clientHttpRequestFactory.setConnectTimeout(40000);
        clientHttpRequestFactory.setHttpClient(HttpClientBuilder.create().build());
        return clientHttpRequestFactory;
    }
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        try {
//            URI redisUri = new URI(redisHostName);
//
//            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//            String host = redisHostName;
//            int port = 6379;
//            String password = "";
//            String activeProfile = env.getProperty("spring.profiles.active");
//            System.out.println("Printing active profile to configure redis " + activeProfile);
//            if ("staging".equalsIgnoreCase(activeProfile)) {
//                host = redisUri.getHost();
//                port = redisUri.getPort();
//                password = redisUri.getUserInfo().split(":", 2)[1];
//            } else {
//                password = env.getProperty("spring.redis.password");
//            }
//
//            jedisConnectionFactory.setHostName(host);
//            jedisConnectionFactory.setPort(port);
//            if (password != null && !password.isEmpty())
//                jedisConnectionFactory.setPassword(password);
//            jedisConnectionFactory.setUsePool(true);
//            jedisConnectionFactory.getPoolConfig().setMaxIdle(30);
//            jedisConnectionFactory.getPoolConfig().setMinIdle(10);
//            return jedisConnectionFactory;
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> initRedis() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }

    @Bean
    RouterFunction<ServerResponse> routerFunctionSwagger() {
        return route(GET("/swagger"), req ->
                ServerResponse.temporaryRedirect(URI.create("/product/swagger-ui.html")).build());
    }
}
