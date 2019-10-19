package grow.demo.routine.service;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                                    .exerciseTools(exerciseDto.getExerciseTools())
                                    .build()
                                    ;

        if(exerciseRepository.findByExerciseNameExists(exercise.getExerciseName())){
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
                                        .exerciseTools(exercise.getExerciseTools())
                                        .build()
                                        ;
         return exerciseDto;
    }
}
