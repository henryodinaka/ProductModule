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
    @Bean
    RouterFunction<ServerResponse> routerFunctionSwagger() {
        return route(GET("/swagger"), req ->
                ServerResponse.temporaryRedirect(URI.create("/product/swagger-ui.html")).build());
    }
}
