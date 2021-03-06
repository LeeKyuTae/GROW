package grow.demo.routine.controller;


import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.service.RoutineService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/routine")
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity registerRoutine(@RequestBody @Valid RoutineDto.RegisterRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        RoutineDto.RoutineInfoResponse routineApply = routineService.registerRoutine(request.getRoutineName(), request.getCategoryId());
        ControllerLinkBuilder selfLinkBuilder = linkTo(RoutineController.class).slash(routineApply.getRoutineId());
        URI createdUri = selfLinkBuilder.toUri();

       // List<RoutineDto.RoutineInfoResponse> response = routineService.getRoutineByCategory(request.getCategoryId());
        //RoutineDto.RoutineListInfoResponse response = routineService.getRoutineByCategory(request.getCategoryId());
        return ResponseEntity.created(createdUri).body(routineApply);
    }

    @GetMapping
    public ResponseEntity getRoutineInfo(@ModelAttribute @Valid RoutineDto.RoutineInfoRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        RoutineDto.RoutineInfoResponse response = routineService.getRoutine(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity getRoutineListInfo(@ModelAttribute @Valid RoutineDto.RoutineListInfoRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }
        List<RoutineDto.RoutineInfoResponse> response = routineService.getRoutineByCategory(request.getCategory_id());

      //  RoutineDto.RoutineListInfoResponse response = routineService.getRoutineByCategory(request.getCategory_id());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/exercise")
    public ResponseEntity addExerciseToRoutine(@RequestBody @Valid RoutineDto.ExerciseRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        RoutineDto.RoutineInfoResponse response = routineService.addExercise(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exercise-list")
    public ResponseEntity getFullInfoToRoutine(@ModelAttribute @Valid RoutineDto.RoutineInfoRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        RoutineDto.FullInfoResponse response = routineService.getFullInfoRoutine(request.getRoutineId());
        return ResponseEntity.ok(response);
    }
}
