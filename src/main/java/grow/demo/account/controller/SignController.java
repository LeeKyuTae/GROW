package grow.demo.account.controller;


import com.fasterxml.jackson.databind.JsonNode;
import grow.demo.account.domain.Account;
import grow.demo.account.dto.AccountResource;
import grow.demo.account.dto.KakaoAccessToken;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.account.service.authorization.KakaoService;
import grow.demo.account.service.user.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class SignController {
    private static final String  HEADER_AUTH = "Authorization" ;

    private JwtService jwtService;

    private KakaoService kakaoService;

    private AccountService accountService;

    @PostMapping("/oauth/login/kakao")
    public ResponseEntity kakaoLogin(@RequestBody KakaoAccessToken kakaoAccess_token /*, HttpServletResponse response*/ ) {
        // String access_token = kakaoService.getAccessToken(code);
        JsonNode userInfo = kakaoService.getUserInfo(kakaoAccess_token.getAccess_token());
        String kakaoId = userInfo.path("id").asText();
        System.out.println("kakaoId: " + kakaoId);
        //String email = userInfo.get("email").toString();
        // 존재하지 않는 아이디일 경우 DB 저장
        /*
        if (accountService.IsExistAccountFromKakao(kakaoId) == false) {
            // 데이터 최대한 넣은 후 create
            JsonNode properties = userInfo.path("properties");
            JsonNode kakao_account = userInfo.path("kakao_account");

            String nickName = properties.path("nickname").asText();
            String email = kakao_account.path("email").asText();
            String gender = kakao_account.path("gender").asText();

            System.out.println("nickName: " + nickName);
            System.out.println("email: " + email);
            System.out.println("gender: " + gender);

            AccountDto accountDto = AccountDto.builder().email(email)
                    .gender(gender).kakaoId(Long.parseLong(kakaoId)).nickName(nickName).build();

            System.out.println("SUCCESS MAKE ACCOUNTDTO");
            accountService.createAccount(accountDto);
            System.out.println("SUCCESS CREATE ACCOUNT");
        }
         */
        //JWT Toeken 발급
        Account account = accountService.getAccountByKakaoID(kakaoId);
        String jwtToken = jwtService.generateToken(account.getId());
        System.out.println("JWT: " + jwtToken);
        //   response.addHeader(HEADER_AUTH, jwtToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HEADER_AUTH, jwtToken);

        /*
        // kakaoAccess_token.setJwt(jwtToken);
        Account account = accountService.getAccount(kakaoId);
        AccountResource accountResource = new AccountResource(account);
         */
        //Set Account Resource
        AccountResource accountResource = new AccountResource(accountService.getAccountDto(account));
        return ResponseEntity.ok().headers(responseHeaders).body(accountResource);
    }
}
