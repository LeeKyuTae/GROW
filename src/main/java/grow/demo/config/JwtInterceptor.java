package grow.demo.config;


import grow.demo.account.service.authorization.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@AllArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String  HEADER_AUTH = "authorization" ;


    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization_token =request.getHeader(HEADER_AUTH);
        System.out.println(authorization_token);
        String  jwttoken = jwtService.getJwtFromRequest(authorization_token);

        if(jwttoken != null && jwtService.validateToken(jwttoken)){
            return true;
        }else{
            throw new UnauthorizedException();
        }
    }

    /*
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_AUTH);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
     */
}