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

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/routine")
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity registerRoutine(@RequestBody RoutineDto.RegisterRequest request, Errors errors){
        RoutineDto.RoutineInfoResponse response = routineService.registerRoutine(request);
        ControllerLinkBuilder selfLinkBuilder = linkTo(RoutineController.class).slash(response.getRoutineId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping
    public ResponseEntity getRoutineInfo(@ModelAttribute RoutineDto.RoutineInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineInfoResponse response = routineService.getRoutine(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity getRoutineListInfo(@ModelAttribute RoutineDto.RoutineListInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineListInfoResponse response = routineService.getRoutineByCategory(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/exercise")
    public ResponseEntity addExerciseToRoutine(@RequestBody RoutineDto.ExerciseRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineInfoResponse response = routineService.addExercise(request);
        return ResponseEntity.ok(response);
    }
}
