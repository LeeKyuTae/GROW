package grow.demo.account.controller;


import grow.demo.account.service.user.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@AllArgsConstructor
@RestControllerAdvice
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService accountService;


}
