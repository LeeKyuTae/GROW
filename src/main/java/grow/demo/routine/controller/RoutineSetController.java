package grow.demo.routine.controller;


import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.dto.SetInfoDto;
import grow.demo.routine.service.SetInfoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/set-info")
public class RoutineSetController {

    private final JwtService jwtService;

    private final SetInfoService setInfoService;

    @PostMapping
    public ResponseEntity registerRoutineSet(@RequestBody @Valid SetInfoDto.RegisterRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        Long accountId = jwtService.getAccountId();
        SetInfoDto.RegisterResponse registerResponse = setInfoService.registerSetInfo(request, accountId);

        SetInfoDto.SetInfoResponse response = setInfoService.getSetInfoCollection(accountId, registerResponse.getRoutineId(), registerResponse.getExerciseId());
        ControllerLinkBuilder selfLinkBuilder = linkTo(RoutineSetController.class).slash(registerResponse.getSetId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping
    public ResponseEntity getSetList(@ModelAttribute @Valid SetInfoDto.SetInfoRequest request, Errors errors) {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }
        Long accountId = jwtService.getAccountId();
        SetInfoDto.SetInfoResponse response = setInfoService.getSetInfoCollection(accountId, request.getRoutineId(), request.getExerciseId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{setId}")
    public ResponseEntity getSetInfo(@PathVariable Long setId) throws NotFoundException {
        SetInfoDto.SetDetail response = setInfoService.getSetInfo(setId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity updateSetInfo(@RequestBody @Valid SetInfoDto.UpdateRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        Long accountId = jwtService.getAccountId();
        SetInfoDto.SetInfoResponse response = setInfoService.updateSetInfo(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity deleteSetInfo(@PathVariable @Valid Long setId) throws NotFoundException {
        Long accountId = jwtService.getAccountId();
        SetInfoDto.SetInfoResponse response = setInfoService.deleteSetInfo(setId, accountId);
        return ResponseEntity.ok(response);
    }

}
