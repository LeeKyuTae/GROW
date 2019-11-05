package grow.demo.routine.domain.exercise;


import grow.demo.routine.dto.ExerciseDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ExerciseValidator {

    public void registerValidate(ExerciseDto.RegisterRequest registerRequest, Errors errors){
        Set<ExerciseMotion> motions = registerRequest.getExerciseMotions();
    }

}
