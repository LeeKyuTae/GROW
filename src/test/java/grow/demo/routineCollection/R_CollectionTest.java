package grow.demo.routineCollection;


import com.fasterxml.jackson.databind.ObjectMapper;
import grow.demo.routine.domain.exercise.ExerciseMotion;
import grow.demo.routine.domain.exercise.ExercisePartial;
import grow.demo.routine.domain.exercise.ExerciseTool;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.service.ExerciseService;
import grow.demo.routine.service.RoutineCategoryService;
import grow.demo.routine.service.RoutineService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

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
    private RoutineCategoryService routineCategoryService;

    @Test
    public void createExercise() {
        /*
        ExerciseDto.RegisterRequest exerciseDto = ExerciseDto.RegisterRequest.builder()
                                    .exerciseName("벤치 프레스")
                                    .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Press)))
                                    .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Chest)))
                                    .exerciseTool(ExerciseTool.Barbell)
                                    .build()
                                    ;
        exerciseService.registerExercise(exerciseDto);

        ExerciseDto.RegisterRequest exerciseDto2 = ExerciseDto.RegisterRequest.builder()
                .exerciseName("스쿼트")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Squat)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Leg)))
                .exerciseTool(ExerciseTool.Barbell)
                .build()
                ;
        exerciseService.registerExercise(exerciseDto2);

        ExerciseDto.RegisterRequest exerciseDto3 = ExerciseDto.RegisterRequest.builder()
                .exerciseName("데드 리프트")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Raise)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Back)))
                .exerciseTool(ExerciseTool.Barbell)
                .build()
                ;
        exerciseService.registerExercise(exerciseDto3);

         */
    }

    @Test
    public void createRoutine() throws NotFoundException {
        /*
        RoutineDto.RegisterRequest routineDto = RoutineDto.RegisterRequest.builder()
                                                        .routineName("등1")
                                                        .build()
                                                        ;

        RoutineDto.RoutineInfoResponse response = routineService.registerRoutine(routineDto);
        RoutineDto.ExerciseRequest exerciseRequest = RoutineDto.ExerciseRequest.builder()
                                                        .exerciseId(Long.valueOf(1))
                                                        .routineId(response.getRoutineId())
                                                        .build();

        routineService.addExercise(exerciseRequest);

        RoutineDto.RegisterRequest routineDto1 = RoutineDto.RegisterRequest.builder()
                .routineName("가슴")
                .build()
                ;

        routineService.registerRoutine(routineDto1);

         */

     //======================================================위에가 돌아감
        /*
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


         */
    }

    @Test
    public void createCategory() throws NotFoundException, SQLException {
        RoutineCategoryDto.RegisterRequest registerRequest = RoutineCategoryDto.RegisterRequest.builder()
                                                                            .categoryName("명준이거말해봐")
                                                                            .routineCategoryType(RoutineCategoryType.RECOMMEND)
                                                                            .build()
                                                                            ;
        routineCategoryService.registerRoutineCategory(registerRequest);


    }
}
