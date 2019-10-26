package grow.demo.routine.controller;


import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity registerExercise(@RequestBody ExerciseDto.RegisterRequest request, Errors errors){
        ExerciseDto.ExerciseResponse response = exerciseService.registerExercise(request);
        return ResponseEntity.ok(response);
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
