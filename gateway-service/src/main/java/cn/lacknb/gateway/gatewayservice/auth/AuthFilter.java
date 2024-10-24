package cn.lacknb.gateway.gatewayservice.auth;

import cn.lacknb.gateway.gatewayservice.exception.AuthException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <h2> 网关统一鉴权 </h2>
 *
 * @description:
 * @menu
 * @author: gitsilence
 * @description:
 * @date: 2024/10/24 07:11
 **/
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 如果get请求的token是空的，去header中拿
        if (token == null) {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            // 如果请求头中没有 Authorization 字段，返回 401 未授权
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new AuthException("Unauthorized request, missing");
            } else {
                token = authHeader.substring(7); // Bearer 后面的 Token
            }
        }

        // 进行 Token 验证的逻辑，假设这里简单验证 Token 是否有效
        if (!isValidToken(token)) {
            throw new AuthException("invalid token");
        }

        // 如果 Token 合法，继续执行请求
        return chain.filter(exchange);
    }

    // 一个简单的 Token 验证方法
    private boolean isValidToken(String token) {
        // 实际项目中可以调用验证服务或解析 JWT Token
        return "valid-token".equals(token);
    }

    @Override
    public int getOrder() {
        return -1;  // 过滤器顺序，越小优先级越高
    }
}
