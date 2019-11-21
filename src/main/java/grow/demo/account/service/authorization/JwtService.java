package grow.demo.account.service.authorization;


import grow.demo.account.repository.AccountRepository;
import grow.demo.config.UnauthorizedException;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;



@Slf4j
@Service
public class JwtService {

    public JwtService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    private final AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;


    @Value("${app.jwtWebExpirationInMs}")
    private long jwtWebExpirationInMs;

    public String generateToken(Long accountId){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(String.valueOf(accountId))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateWebToken(Long accountId){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtWebExpirationInMs);

        return Jwts.builder()
                .setSubject(String.valueOf(accountId))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    /*
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
     */

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    /*
    public Map<String, Object> get(String key){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error(e.getMessage());
            }
            throw new UnauthorizedException();
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        return value;
    }
    */

    public Long getAccountId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("authorization");

        String token = getJwtFromRequest(jwt);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

            Long accountId = Long.parseLong(claims.getSubject());
            System.out.println("accountID: " + claims.getSubject());
            accountRepository.findById(accountId).orElseThrow(()-> new UnauthorizedException());
            return accountId;
        } catch (Exception e) {
            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error(e.getMessage());
            }
            throw new UnauthorizedException();
        }
    }

    public String getJwtFromRequest(String token ) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

}
