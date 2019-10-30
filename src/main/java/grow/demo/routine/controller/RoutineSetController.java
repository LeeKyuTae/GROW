package grow.demo.routine.controller;


import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.dto.SetInfoDto;
import grow.demo.routine.service.SetInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/routine-set")
public class RoutineSetController {

    private final JwtService jwtService;

    private final SetInfoService setInfoService;

    @PostMapping
    public ResponseEntity registerRoutineSet(@ModelAttribute @Valid SetInfoDto.RegisterRequest request, Errors error) {
        Long accountId = jwtService.getAccountId();
        request.setAccountId(accountId);




    }
}
