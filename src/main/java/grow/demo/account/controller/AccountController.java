package grow.demo.account.controller;


import grow.demo.account.domain.Account;
import grow.demo.account.dto.AccountDto;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.account.service.user.AccountService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService accountService;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity updateAccountInfo(@ModelAttribute @Valid AccountDto.AccountInfoUpdateRequest request, Errors errors) throws NotFoundException {
        Long accountId = jwtService.getAccountId();
        // validate request info

        AccountDto.AccountResponse response = accountService.updateAccountInfo(request, accountId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity updateWeightInfo(@ModelAttribute @Valid AccountDto.WeightUpdateRequest request, Errors errors) throws NotFoundException {
        Long accountId = jwtService.getAccountId();
        // validate request info

        AccountDto.AccountResponse response = accountService.updateWeight(request, accountId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity getAccountInfo(@PathVariable Long accountId) throws NotFoundException {
        Account account = accountService.getAccount(accountId);
        AccountDto.AccountResponse response = accountService.ResponseByAccount(account);
        return ResponseEntity.ok(response);
    }

}
