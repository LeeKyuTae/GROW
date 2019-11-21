package grow.demo.routineCollection;


import com.fasterxml.jackson.databind.ObjectMapper;
import grow.demo.account.domain.Account;
import grow.demo.account.dto.AccountDto;
import grow.demo.account.repository.AccountRepository;
import grow.demo.account.service.user.AccountService;
import grow.demo.config.UnauthorizedException;
import grow.demo.record.domain.BestRecord;
import grow.demo.record.dto.BestRecordDto;
import grow.demo.record.service.BestRecordService;
import grow.demo.routine.domain.exercise.ExerciseMotion;
import grow.demo.routine.domain.exercise.ExercisePartial;
import grow.demo.routine.domain.exercise.ExerciseTool;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.ExerciseDto;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.repository.RoutineCategoryRepository;
import grow.demo.routine.repository.RoutineRepository;
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
import java.time.LocalDateTime;
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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoutineCategoryRepository routineCategoryRepository;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private BestRecordService bestRecordService;


    @Test
    public void makeMadCowCategory(){
        RoutineCategory routineCategory = RoutineCategory.builder()
                                                    .categoryType(RoutineCategoryType.RECOMMEND)
                                                    .categoryName("매드카우 5X5")
                                                    .build();
        routineCategoryRepository.save(routineCategory);
    }

    @Test
    public void makeMadCowCategoryHTS(){
        RoutineCategory routineCategory = RoutineCategory.builder()
                .categoryType(RoutineCategoryType.RECOMMEND)
                .categoryName("HTS")
                .build();
        routineCategoryRepository.save(routineCategory);
    }

    @Test
    public void addMadCowRoutine(){
        RoutineCategory category = routineCategoryRepository.findById(149L).orElseThrow(()-> new UnauthorizedException());
        Routine routine = Routine.builder()
                                .routineName("Day 1")
                                .routineCategory(category)
                                .build();
        routineRepository.save(routine);

        Routine routine2 = Routine.builder()
                .routineName("Day 2")
                .routineCategory(category)
                .build();
        routineRepository.save(routine2);

        Routine routine3 = Routine.builder()
                .routineName("Day 3")
                .routineCategory(category)
                .build();
        routineRepository.save(routine3);
    }

    @Test
    public void add1Rm() throws NotFoundException {
        Long accountId = 153L;
        set1RM(88L, accountId, 100F);
        set1RM(66L, accountId, 80F);
        set1RM(142L, accountId, 60F);
        set1RM(106L, accountId, 50F);
        set1RM(49L, accountId, 120F);
    }

    public void set1RM(Long exerciseId, Long accountId, Float weight) throws NotFoundException {
        BestRecordDto.RegisterRequest request = BestRecordDto.RegisterRequest.builder()
                                                                    .exerciseId(exerciseId)
                                                                    .weight(weight)
                                                                    .build();
        bestRecordService.registerBestRecord(request, accountId);
    }

    @Test
    public void addExerciseToRoutineMadCow() throws NotFoundException {
        addExerciseToRoutine(88L,146L);
        addExerciseToRoutine(66L,146L);
        addExerciseToRoutine(142L,146L);
        addExerciseToRoutine(88L,147L);
        addExerciseToRoutine(106L,147L);
        addExerciseToRoutine(49L,147L);
        addExerciseToRoutine(88L,148L);
        addExerciseToRoutine(66L,148L);
        addExerciseToRoutine(142L,148L);
    }

    public void addExerciseToRoutine(Long exerciseId, Long routineId) throws NotFoundException {
        RoutineDto.ExerciseRequest request = RoutineDto.ExerciseRequest.builder()
                                                                .exerciseId(exerciseId)
                                                                .routineId(routineId)
                                                                .build();
        routineService.addExercise(request);
    }


    //TEST 5
    @Test
    public void createTestUser(){
        Account account = Account.builder().userName("tester1").userEmail("qwe@test.com").birth("1995-04-03").gender("MALE").height(188F).weight(69.5F).kakaoId(9999999L).build();
        account = accountRepository.save(account);
    }


    //TEST 1.
    @Test
    public void createExercise() {

        exerciseService.registerExercise(makeExercise("딥스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("어시스트 딥스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.assisted));
        exerciseService.registerExercise(makeExercise("바벨 로우", ExercisePartial.back, ExerciseMotion.pull, ExerciseTool.barbell));
        /*
        ExerciseDto.RegisterRequest exerciseDto = ExerciseDto.RegisterRequest.builder()
                                    .exerciseName("벤치 프레스")
                                    .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.press)))
                                    .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.chest)))
                                    .exerciseTool(ExerciseTool.barbell)
                                    .build()
                                    ;
        exerciseService.registerExercise(exerciseDto);

        ExerciseDto.RegisterRequest exerciseDto2 = ExerciseDto.RegisterRequest.builder()
                .exerciseName("스쿼트")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.squat)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.leg)))
                .exerciseTool(ExerciseTool.barbell)
                .build()
                ;
        exerciseService.registerExercise(exerciseDto2);

        ExerciseDto.RegisterRequest exerciseDto3 = ExerciseDto.RegisterRequest.builder()
                .exerciseName("데드 리프트")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.raise)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.back)))
                .exerciseTool(ExerciseTool.barbell)
                .build()
                ;
        exerciseService.registerExercise(exerciseDto3);
         */
    }

    @Test
    public void createCategoryMadCow(){

    }





    @Test
    public void registerExercises(){
        //exerciseService.registerExercise(makeExercise("", ExercisePartial, ExerciseMotion, ExerciseTool));

        exerciseService.registerExercise(makeExercise("케이블 크런치", ExercisePartial.core, ExerciseMotion.pull_down, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("케이블 트위스트", ExercisePartial.core, ExerciseMotion.twist, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("머신 크런치", ExercisePartial.core, ExerciseMotion.curl, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 데드리프트", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 벤치 오버 원 암 로우", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("바벨 벤치 오버 로우", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 벤치 오버 로우", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("바벨 벤치 오버 로우 -언더핸드", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("어시스트 친 업", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.assisted));
        exerciseService.registerExercise(makeExercise("친 업", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("덤벨 인클라인 로우", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("케이블 랫풀다운", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("머신 랫풀다운", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("케이블 와이드 그립 랫풀다운", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("케이블 랫풀다운 -언더핸드", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("바벨 팬들레이 로우", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("풀 업", ExercisePartial.back, ExerciseMotion.pull_down, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("케이블 시트 로우", ExercisePartial.back, ExerciseMotion.pull, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("머신 시트 로우", ExercisePartial.back, ExerciseMotion.pull, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("티 바 로우", ExercisePartial.back, ExerciseMotion.raise, ExerciseTool.machine));


        exerciseService.registerExercise(makeExercise("바벨 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케이블 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 벤치 프렛스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("케이블 크로스 오버", ExercisePartial.chest, ExerciseMotion.extension, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("체스트 딥", ExercisePartial.chest, ExerciseMotion.extension, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("어시스트 체스트 딥", ExercisePartial.chest, ExerciseMotion.extension, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("머신 체스트 플라이", ExercisePartial.chest, ExerciseMotion.fly, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("덤벨 체스트 플라이", ExercisePartial.chest, ExerciseMotion.fly, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 체스트 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 디클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 디클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("스미스머신 디클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 인클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케이블 인클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 인클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 인클라인 벤치 프레스", ExercisePartial.chest, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("덤벨 인클라인 체스트 플라이", ExercisePartial.chest, ExerciseMotion.fly, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("덤벨 풀 오버", ExercisePartial.chest, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 풀 오버", ExercisePartial.chest, ExerciseMotion.raise, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("푸쉬 업", ExercisePartial.chest, ExerciseMotion.raise, ExerciseTool.no_tool));


        exerciseService.registerExercise(makeExercise("스쿼트", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("바벨 스쿼트", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("바벨 프론트 스쿼트", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케틀벨 고블린 스쿼트", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.kettlebell));
        exerciseService.registerExercise(makeExercise("바벨 힙 트러스트", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("머신 레그 익스텐션", ExercisePartial.leg, ExerciseMotion.extension, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("레그 프레스", ExercisePartial.leg, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 런지", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("런지", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("덤벨 런지", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("덤벨 스쿼트", ExercisePartial.leg, ExerciseMotion.squat, ExerciseTool.dumbbell));


        exerciseService.registerExercise(makeExercise("덤벨 아놀드 프레스", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("케이블 페이스 풀", ExercisePartial.sholder, ExerciseMotion.pull, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("바벨 프론트 레이즈", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케이블 프론트 레이즈", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 프론트 레이즈", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("케이블 레터럴 레이즈", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 레터럴 레이즈", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 레터럴 레이즈", ExercisePartial.sholder, ExerciseMotion.raise, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 오버헤드 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케이블 오버헤드 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 오버헤드 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("스미스머신 오버헤드 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 푸쉬 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케이블 리버스 플라이", ExercisePartial.sholder, ExerciseMotion.fly, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 리버스 플라이", ExercisePartial.sholder, ExerciseMotion.fly, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 리버스 플라이", ExercisePartial.sholder, ExerciseMotion.fly, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 시트 오버헤드 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 시트 오버헤드 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 숄더 프레스", ExercisePartial.sholder, ExerciseMotion.press, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 슈러그", ExercisePartial.sholder, ExerciseMotion.shrug, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤베 슈러그", ExercisePartial.sholder, ExerciseMotion.shrug, ExerciseTool.dumbbell));



        exerciseService.registerExercise(makeExercise("벤치 딥", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("바벨 바이셉스 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("케이블 바이셉스 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 바이셉스 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 바이셉스 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("케이블 햄머 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("덤벨 햄머 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("덤벨 인클라인 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("바벨 프리쳐 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 프리쳐 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 프리쳐 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.machine));
        exerciseService.registerExercise(makeExercise("바벨 리버스 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 리버스 컬", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("바벨 스컬 크러셔", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 스컬 크러셔", ExercisePartial.arm, ExerciseMotion.curl, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("트라이셉스 딥", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.no_tool));
        exerciseService.registerExercise(makeExercise("어시스트 트라이셉스 딥", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.assisted));
        exerciseService.registerExercise(makeExercise("케이블 트라이셉스 익스텐션", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.cable));
        exerciseService.registerExercise(makeExercise("바벨 트라이셉스 익스텐션", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.barbell));
        exerciseService.registerExercise(makeExercise("덤벨 트라이셉스 익스텐션", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.dumbbell));
        exerciseService.registerExercise(makeExercise("머신 트라이셉스 익스텐션", ExercisePartial.arm, ExerciseMotion.extension, ExerciseTool.machine));



    }



    public ExerciseDto.RegisterRequest makeExercise(String exerciseName, ExercisePartial partial,ExerciseMotion motion, ExerciseTool tool){
        //ExerciseDto.RegisterRequest exerciseDto = ExerciseDto.RegisterRequest.builder()
        //        .exerciseName(exerciseName)
        //        .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.valueOf(partial))))
        //        .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.valueOf(motion))))
        //        .exerciseTool(ExerciseTool.valueOf(tool))
        //        .build();
        ExerciseDto.RegisterRequest exerciseDto = ExerciseDto.RegisterRequest.builder()
                                                        .exerciseName(exerciseName)
                                                        .exercisePartials(new HashSet<>(Arrays.asList(partial)))
                                                        .exerciseMotions(new HashSet<>(Arrays.asList(motion)))
                                                        .exerciseTool(tool)
                                                        .build();
        return exerciseDto;
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

    //TEST 2
    @Test
    public void createCategory() throws NotFoundException, SQLException {
        RoutineCategoryDto.RegisterRequest registerRequest = RoutineCategoryDto.RegisterRequest.builder()
                                                                            .categoryName("명준이거말해봐")
                                                                            .categoryType(RoutineCategoryType.RECOMMEND)
                                                                            .build()
                                                                            ;
        Long accountId = 7L;
        routineCategoryService.registerRoutineCategory(registerRequest, accountId);
    }


    @Test
    public void addCategoryToAccount() throws Exception {
        accountService.addCategoryToAccount(Long.valueOf(7), Long.valueOf(4));
    }
}
