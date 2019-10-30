package grow.demo.account.controller;


import com.fasterxml.jackson.databind.JsonNode;
import grow.demo.account.domain.Account;
import grow.demo.account.dto.AccountDto;
import grow.demo.account.dto.AccountResource;
import grow.demo.account.dto.KakaoAccessToken;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.account.service.authorization.KakaoService;
import grow.demo.account.service.user.AccountService;
import grow.demo.routine.controller.ExerciseController;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@AllArgsConstructor
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class SignController {
    private static final String  HEADER_AUTH = "Authorization" ;

    private final JwtService jwtService;

    private final KakaoService kakaoService;

    private final AccountService accountService;

    @PostMapping("/oauth/login/kakao")
    public ResponseEntity kakaoLogin(@RequestBody KakaoAccessToken kakaoAccess_token /*, HttpServletResponse response*/ ) throws NotFoundException {
        // String access_token = kakaoService.getAccessToken(code);
        JsonNode userInfo = kakaoService.getUserInfo(kakaoAccess_token.getAccess_token());
        String id = userInfo.path("id").asText();
        Long kakaoId = Long.valueOf(id);

        //Response Body
        Boolean isSignIn = true;

        // 존재하지 않는 아이디일 경우 DB 저장
        if(accountService.isExistAccount(kakaoId) == false){
            accountService.registerAccountByKakao(kakaoId);
            isSignIn = false;
        }
        AccountDto.SignInResponse response = accountService.getAccountByKakaoID(Long.valueOf(kakaoId));
        response.setIsSignIn(isSignIn);

        //JWT Toeken 발급
        String jwtToken = jwtService.generateToken(response.getAccountId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HEADER_AUTH, jwtToken);

        //Set Account Resource
        if(isSignIn == false){
            ControllerLinkBuilder selfLinkBuilder = linkTo("/account/").slash(response.getAccountId());
            URI createdUri = selfLinkBuilder.toUri();
            return ResponseEntity.created(createdUri).body(response);
        }

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }
}
