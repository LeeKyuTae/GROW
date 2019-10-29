package grow.demo.routine.controller;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/exercise")
public class ExerciseController {
    private static final HttpHeaders httpHeaders = new HttpHeaders();

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity registerExercise(@RequestBody @Valid ExerciseDto.RegisterRequest request, Errors errors){
            ExerciseDto.ExerciseResponse response = exerciseService.registerExercise(request);
            ControllerLinkBuilder selfLinkBuilder = linkTo(ExerciseController.class).slash(response.getExerciseId());
            URI createdUri = selfLinkBuilder.toUri();
            return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping("/collection")
    public ResponseEntity getExercises(@RequestParam String exerciseName, Errors errors){
        List<ExerciseDto.ExerciseResponse> responseList = exerciseService.getExerciseList("%" + exerciseName + "%");
        return ResponseEntity.ok(responseList);
    }

    @GetMapping
    public ResponseEntity getExercise(@ModelAttribute ExerciseDto.ExerciseRequest request, Errors errors){
        ExerciseDto.ExerciseResponse response = exerciseService.getExercise(request.getExerciseId());
        return ResponseEntity.ok(response);
    }

}
