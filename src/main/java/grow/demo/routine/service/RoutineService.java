package grow.demo.routine.service;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import grow.demo.routine.repository.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    private final ExerciseRepository exerciseRepository;


    public Routine registerRoutine(RoutineDto routineDto){
        Routine routine = Routine.builder()
                            .routineName(routineDto.getRoutineName())
                            .exerciseList(routineDto.getExerciseList())
                            .routineCollection(routineDto.getRoutineCollection())
                            .build()
                            ;

        return routineRepository.save(routine);
    }

    public RoutineDto addExercise(RoutineDto routineDto, Long exerciseId){
        Routine routine = routineRepository.findById(routineDto.getRoutineId()).get();
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        List<Exercise> exerciseList = routine.getExerciseList();
        if(exerciseList.contains(exercise)){
            throw new ExistExerciseException(exercise.getExerciseName());
        }

        if(exerciseList.isEmpty()){
            exerciseList = new ArrayList<>();
            exerciseList.add(exercise);
            routine.builder().exerciseList(exerciseList).build();
        }else{
            exerciseList.add(exercise);
        }
        return routineDtoByRoutine(routine);
    }

    public RoutineDto deleteExercise(RoutineDto routineDto, Long exerciseId){
        Routine routine = routineRepository.findById(routineDto.getRoutineId()).get();
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        List<Exercise> exerciseList = routine.getExerciseList();

        if(!exerciseList.contains(exercise)){
            throw new NotExistExerciseException(exercise.getExerciseName());
        }
        exerciseList.remove(exercise);
        return routineDtoByRoutine(routine);
    }

    public RoutineDto routineDtoByRoutine(Routine routine){
        RoutineDto routineDto = RoutineDto.builder()
                .routineId(routine.getRoutineId())
                .exerciseList(routine.getExerciseList())
                .routineCollection(routine.getRoutineCollection())
                .routineName(routine.getRoutineName())
                .build()
                ;
        return routineDto;
    }
}
