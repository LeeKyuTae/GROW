package grow.demo.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@AllArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
            "/oauth/login/**", "/favicon.ico", "/", "/webapp/WEB-INF/views/index.jsp"
            ,"/**/*.png", "/oauth/login/kakao" , "/category/**"
    };

    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("Content-Type",  "authorization","Accept" , "X-Requested-With", "Access-Control-Request-Method", "Access_Control-Request-Headers")
                .exposedHeaders("Authorization")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .maxAge(3600)
                .allowCredentials(false)
        ;
    }

}