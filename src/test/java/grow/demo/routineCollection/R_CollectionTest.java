package grow.demo.routineCollection;


import com.fasterxml.jackson.databind.ObjectMapper;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.exercise.ExerciseMotion;
import grow.demo.routine.domain.exercise.ExercisePartial;
import grow.demo.routine.domain.exercise.ExerciseTool;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.service.ExerciseService;
import grow.demo.routine.service.RoutineCollectionService;
import grow.demo.routine.service.RoutineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class R_CollectionTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private RoutineService routineService;

    @Autowired
    private RoutineCollectionService routineCollectionService;

    @Test
    public void createExercise() {
        ExerciseDto exerciseDto = ExerciseDto.builder()
                                    .exerciseName("벤치 프레스")
                                    .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Press)))
                                    .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Chest)))
                                    .exerciseTools(ExerciseTool.Barbell)
                                    .build()
                                    ;
        exerciseService.registerExercise(exerciseDto);

        ExerciseDto exerciseDto2 = ExerciseDto.builder()
                .exerciseName("스쿼트")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Squat)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Leg)))
                .exerciseTools(ExerciseTool.Barbell)
                .build()
                ;
        exerciseService.registerExercise(exerciseDto2);

        ExerciseDto exerciseDto3 = ExerciseDto.builder()
                .exerciseName("데드 리프트")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Raise)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Back)))
                .exerciseTools(ExerciseTool.Barbell)
                .build()
                ;
        exerciseService.registerExercise(exerciseDto3);
    }

    @Test
    public void createRoutine(){
        RoutineDto routineDto1 = RoutineDto.builder()
                                .routineName("등")
                                .build()
                                ;
        routineService.registerRoutine(routineDto1);

        RoutineDto routineDto2 = RoutineDto.builder()
                                .routineName("가슴test")
                                .exerciseList(new ArrayList<>(Arrays.asList(exerciseService.getExercise(Long.valueOf("1")))))
                                .build();
        routineService.registerRoutine(routineDto2);

        RoutineDto routineDto3 = RoutineDto.builder()
                .routineName("TESTST")
                .exerciseList(new ArrayList<>(Arrays.asList(exerciseService.getExercise(Long.valueOf("1")),exerciseService.getExercise(Long.valueOf("2")) )))
                .build();
        routineService.registerRoutine(routineDto3);




    }
}
