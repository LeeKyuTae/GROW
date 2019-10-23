package grow.demo.routine.service;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    private final ModelMapper modelMapper;

    public ExerciseDto.ExerciseResponse registerExercise(ExerciseDto.RegisterRequest exerciseDto){
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
        return ResponseByExercise(exercise);
    }

    public Exercise getExercise(Long exerciseId){
        Exercise exercise = exerciseRepository.findById(exerciseId)
                                            .orElseThrow(() -> new NotExistExerciseException());

        return exercise;
    }

    public ExerciseDto.ExerciseResponse ResponseByExercise(Exercise exercise){
        ExerciseDto.ExerciseResponse exerciseRegisterResponse = modelMapper.map(exercise, ExerciseDto.ExerciseResponse.class);
        return exerciseRegisterResponse;
    }
}
