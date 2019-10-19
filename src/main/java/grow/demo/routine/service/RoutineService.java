package grow.demo.routine.service;


import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.repository.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional @AllArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;


    public Routine registerRoutine(RoutineDto routineDto){
        Routine routine = Routine.builder()
                            .routineName(routineDto.getRoutineName())
                            .exerciseList(routineDto.getExerciseList())
                            .routineCollection(routineDto.getRoutineCollection())
                            .build()
                            ;

        return routineRepository.save(routine);
    }

    public RoutineDto addExercise(RoutineDto routineDto, Exercise exercise){

    }



}
