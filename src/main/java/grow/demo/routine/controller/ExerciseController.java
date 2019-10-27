package grow.demo.routine.controller;


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
    public ResponseEntity registerExercise(@RequestBody ExerciseDto.RegisterRequest request, Errors errors){
        try {
            ExerciseDto.ExerciseResponse response = exerciseService.registerExercise(request);
            ControllerLinkBuilder selfLinkBuilder = linkTo(ExerciseController.class).slash(response.getExerciseId());
            URI createdUri = selfLinkBuilder.toUri();
            return ResponseEntity.created(createdUri).body(response);
        } catch (ExistExerciseException e){
            return new ResponseEntity(Collections.singletonMap("error", e.getMessage()), httpHeaders, HttpStatus.BAD_REQUEST);

        }


    }

    @GetMapping
    public ResponseEntity getExercises(@RequestParam String exerciseName, Errors errors){
        List<ExerciseDto.ExerciseResponse> responseList = exerciseService.getExerciseList("%" + exerciseName + "%");
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity getExercise(@PathVariable Long exerciseId, Errors errors){
        ExerciseDto.ExerciseResponse response = exerciseService.getExercise(exerciseId);
        return ResponseEntity.ok(response);
    }
}
