package grow.demo.routine.service;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseDto registerExercise(ExerciseDto exerciseDto){
        Exercise exercise = Exercise.builder()
                                    .exerciseMotions(exerciseDto.getExerciseMotions())
                                    .exercisePartials(exerciseDto.getExercisePartials())
                                    .exerciseName(exerciseDto.getExerciseName())
                                    .exerciseTool(exerciseDto.getExerciseTools())
                                    .build()
                                    ;

        if(exerciseRepository.existsByExerciseName(exercise.getExerciseName())){
            throw new ExistExerciseException(exercise.getExerciseName());
        }

        exercise = exerciseRepository.save(exercise);
        return exerciseDtoByExercise(exercise);
    }

    public ExerciseDto exerciseDtoByExercise(Exercise exercise){
         ExerciseDto exerciseDto = ExerciseDto.builder()
                                        .exerciseId(exercise.getExerciseId())
                                        .exerciseName(exercise.getExerciseName())
                                        .exerciseMotions(exercise.getExerciseMotions())
                                        .exercisePartials(exercise.getExercisePartials())
                                        .exerciseTools(exercise.getExerciseTool())
                                        .build()
                                        ;
         return exerciseDto;
    }

    public ExerciseDto getExercise(Long exerciseId){
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        if(!exerciseOptional.isPresent())
            throw new NotExistExerciseException();

        return exerciseDtoByExercise(exerciseOptional.get());
    }
}
