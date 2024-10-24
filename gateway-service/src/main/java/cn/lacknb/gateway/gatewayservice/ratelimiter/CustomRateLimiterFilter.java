package cn.lacknb.gateway.gatewayservice.ratelimiter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <h2>  </h2>
 *
 * @description:
 * @menu
 * @author: gitsilence
 * @description:
 * @date: 2024/10/24 08:03
 **/
@Component
public class CustomRateLimiterFilter extends AbstractGatewayFilterFactory<Object>  {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.defer(() -> {
            HttpStatus statusCode = exchange.getResponse().getStatusCode();
            if (statusCode == HttpStatus.TOO_MANY_REQUESTS) {
                exchange.getResponse().setStatusCode(HttpStatus.OK);  // 修改状态码为200
                // 自定义返回的响应体内容
                exchange.getResponse().getHeaders().add("Content-Type", "application/json");
                byte[] bytes = "{\"message\":\"Too many requests, please try again later.\"}".getBytes();
                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory().wrap(bytes)));
            }
            return Mono.empty();
        }));
    }
}
