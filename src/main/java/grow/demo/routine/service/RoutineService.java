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
import java.util.stream.Collectors;

@Service
@Transactional @AllArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    private final ExerciseRepository exerciseRepository;

    private final ExerciseService exerciseService;


    public Routine registerRoutine(RoutineDto routineDto){
        List<Exercise> exerciseList = routineDto.getExerciseList().stream()
                                                .map(exerciseDto -> exerciseRepository.findById(exerciseDto.getExerciseId()).get()).collect(Collectors.toList());
        Routine routine = Routine.builder()
                            .routineName(routineDto.getRoutineName())
                            .exerciseList(exerciseList)
                            .routineCategory(routineDto.getRoutineCollection())
                            .build()
                            ;

        return routineRepository.save(routine);
    }

    public RoutineDto addExercise(RoutineDto routineDto, Long exerciseId){
        Routine routine = routineRepository.findById(routineDto.getRoutineId()).get();
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        List<Exercise> exerciseList = routine.getExerciseList();
        if(exerciseList == null) {
            exerciseList = new ArrayList<>();
            routine.builder().exerciseList(exerciseList).build();
        }
        else if(exerciseList.contains(exercise)){
            throw new ExistExerciseException(exercise.getExerciseName(), routine.getRoutineName());
        }
        exerciseList.add(exercise);

        return routineDtoByRoutine(routine);
    }

    public RoutineDto deleteExercise(RoutineDto routineDto, Long exerciseId){
        Routine routine = routineRepository.findById(routineDto.getRoutineId()).get();
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        List<Exercise> exerciseList = routine.getExerciseList();

        if(!exerciseList.contains(exercise)){
            throw new NotExistExerciseException(exercise.getExerciseName(), routine.getRoutineName());
        }
        exerciseList.remove(exercise);
        return routineDtoByRoutine(routine);
    }

    public RoutineDto routineDtoByRoutine(Routine routine){
        RoutineDto routineDto = RoutineDto.builder()
                .routineId(routine.getRoutineId())
                .exerciseList(routine.getExerciseList().stream().map( exercise -> exerciseService.exerciseDtoByExercise(exercise)).collect(Collectors.toList()))
                .routineCollection(routine.getRoutineCategory())
                .routineName(routine.getRoutineName())
                .build()
                ;
        return routineDto;
    }
}
