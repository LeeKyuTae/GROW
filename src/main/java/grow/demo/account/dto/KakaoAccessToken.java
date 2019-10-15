package grow.demo.account.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccessToken {
    String access_token;
    String token_type ;
    String refresh_token;
    String expires_in;
    String scope;
    String refresh_token_expires_in;
    String jwt;
}
