package com.maveric.gateway.filter;

import com.maveric.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
private RouteValidator validator;
    @Autowired
    private RestTemplate template;

    @Autowired
    private JwtUtil jwtUtil;
public AuthenticationFilter(){
    super(Config.class);
}
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
if(validator.isSecured.test(exchange.getRequest())) {
    // Headercontains token or not
    if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
        throw new RuntimeException("Missing Authourization Token");
    }
    String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
    if(authHeader!=null && authHeader.startsWith("Bearer ")){
        authHeader=authHeader.substring(7);
    }
    try {
     //template.getForObject("http://localhost:8083/api/v1/auth/validate?token"+authHeader,String.class);
        jwtUtil.validateToken(authHeader);
    }catch(Exception e){
        System.out.println("Un authourized access");
        throw new RuntimeException("Un authourized access");
    }
}

return chain.filter(exchange);
        }
        );
    }

    public static class Config{

    }

}
