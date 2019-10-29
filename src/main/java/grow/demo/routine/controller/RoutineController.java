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
    public ResponseEntity registerRoutine(@RequestBody RoutineDto.RegisterRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineInfoResponse routineApply = routineService.registerRoutine(request.getRoutineName(), request.getCategoryId());
        ControllerLinkBuilder selfLinkBuilder = linkTo(RoutineController.class).slash(routineApply.getRoutineId());
        URI createdUri = selfLinkBuilder.toUri();

        RoutineDto.RoutineListInfoResponse response = routineService.getRoutineByCategory(request.getCategoryId());
        return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping
    public ResponseEntity getRoutineInfo(@ModelAttribute RoutineDto.RoutineInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineInfoResponse response = routineService.getRoutine(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity getRoutineListInfo(@ModelAttribute RoutineDto.RoutineListInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineListInfoResponse response = routineService.getRoutineByCategory(request.getCategory_id());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/exercise")
    public ResponseEntity addExerciseToRoutine(@RequestBody RoutineDto.ExerciseRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineInfoResponse response = routineService.addExercise(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exercise-list")
    public ResponseEntity getFullInfoToRoutine(@ModelAttribute RoutineDto.RoutineInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.FullInfoResponse response = routineService.getFullInfoRoutine(request.getRoutineId());
        return ResponseEntity.ok(response);
    }
}
