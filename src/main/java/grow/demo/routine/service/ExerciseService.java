package grow.demo.routine.service;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    private final ModelMapper modelMapper;

    @CacheEvict(value = "allExercise")
    public ExerciseDto.ExerciseResponse registerExercise(ExerciseDto.RegisterRequest exerciseDto){
        Exercise exercise = Exercise.builder()
                                    .exerciseMotions(exerciseDto.getExerciseMotions())
                                    .exercisePartials(exerciseDto.getExercisePartials())
                                    .exerciseName(exerciseDto.getExerciseName())
                                    .exerciseTool(exerciseDto.getExerciseTool())
                                    .build()
                                    ;

        if(exerciseRepository.existsByExerciseName(exercise.getExerciseName())){
            throw new ExistExerciseException(exercise.getExerciseName());
        }

        exercise = exerciseRepository.save(exercise);
        return ResponseByExercise(exercise);
    }

    public List<ExerciseDto.ExerciseResponse> getExerciseList(String exerciseNameLike){
        List<Exercise> exerciseList = exerciseRepository.findAllByExerciseNameLike(exerciseNameLike);
        if(exerciseList == null){
            throw new NotExistExerciseException();
        }
        List<ExerciseDto.ExerciseResponse> responseList = new ArrayList<>();
        for(Exercise exercise : exerciseList){
            ExerciseDto.ExerciseResponse response = ResponseByExercise(exercise);
            responseList.add(response);
        }
        return responseList;
    }

    @Cacheable(value = "allExercise")
    public List<ExerciseDto.ExerciseResponse> getAllExerciseList(){
        List<Exercise> exerciseList = exerciseRepository.findAll();
        if(exerciseList == null){
            throw new NotExistExerciseException();
        }
        List<ExerciseDto.ExerciseResponse> responseList = new ArrayList<>();
        for(Exercise exercise : exerciseList){
            ExerciseDto.ExerciseResponse response = ResponseByExercise(exercise);
            responseList.add(response);
        }
        Collections.sort(responseList);
        return responseList;
    }


    public ExerciseDto.ExerciseResponse getExercise(Long exerciseId){
        Exercise exercise = exerciseRepository.findById(exerciseId)
                                            .orElseThrow(() -> new NotExistExerciseException());

        return ResponseByExercise(exercise);
    }

    public ExerciseDto.ExerciseResponse ResponseByExercise(Exercise exercise){
        ExerciseDto.ExerciseResponse exerciseRegisterResponse = modelMapper.map(exercise, ExerciseDto.ExerciseResponse.class);
        return exerciseRegisterResponse;
    }
}
