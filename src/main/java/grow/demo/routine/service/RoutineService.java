package grow.demo.routine.service;


import grow.demo.account.domain.Account;
import grow.demo.account.repository.AccountRepository;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineImage;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.exception.ExistExerciseException;
import grow.demo.routine.exception.ExistRoutineException;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.exception.NotModifyCategoryException;
import grow.demo.routine.repository.ExerciseRepository;
import grow.demo.routine.repository.RoutineCategoryRepository;
import grow.demo.routine.repository.RoutineRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional @AllArgsConstructor
public class RoutineService {
    private final AccountRepository accountRepository;

    private final RoutineRepository routineRepository;

    private final ExerciseRepository exerciseRepository;

    private final ExerciseService exerciseService;

    private final RoutineCategoryRepository routineCategoryRepository;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;


    public RoutineDto.RoutineInfoResponse registerRoutine(String routineName, Long categoryId) throws NotFoundException {
        Routine routine = Routine.builder()
                            .routineName(routineName)
                            .build()
                            ;
        RoutineCategory routineCategory = routineCategoryRepository.findById(categoryId).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴카테고리입니다."));
        boolean registerCategory = routine.registerCategory(routineCategory);
        if(registerCategory == false)
            throw new NotModifyCategoryException();
        routine = routineRepository.save(routine);
        return ResponseByRoutine(routine);
    }

    public RoutineDto.RoutineInfoResponse getRoutine(RoutineDto.RoutineInfoRequest request) throws NotFoundException {
        Routine routine = routineRepository.findById(request.getRoutineId()).orElseThrow(() -> new NotFoundException("존재하지 않는 루틴입니다."));
        return ResponseByRoutine(routine);
    }

    public List<RoutineDto.RoutineInfoResponse> getRoutineByCategory(Long categoryId) throws NotFoundException {
        List<Routine> routineList = routineRepository.findByRoutineCategory_CategoryId(categoryId);
        if(routineList == null){
            throw new NotFoundException("해당 카테고리는 존재하지 않습니다.");
        }
        List<RoutineDto.RoutineInfoResponse> responsebody = new ArrayList<>();
        for(Routine routine : routineList){
            responsebody.add(ResponseByRoutine(routine));
        }
      // RoutineDto.RoutineListInfoResponse response = RoutineDto.RoutineListInfoResponse.builder().responses(responsebody).build();
        return responsebody;
    }
    public RoutineDto.RoutineInfoResponse addExercise(RoutineDto.ExerciseRequest routineDto) throws NotFoundException {
        Routine routine = routineRepository.findById(routineDto.getRoutineId()).orElseThrow(()-> new NotFoundException("존재하지 않는 루틴입니다."));
        final Exercise exercise = exerciseRepository.findById(routineDto.getExerciseId()).orElseThrow(() -> new NotExistExerciseException());
        System.out.println("!!!!!받은 루틴 아이디: " + routineDto.getRoutineId() + " 운동 아이디 : " + routineDto.getExerciseId());
        Set<Exercise> exerciseList = routine.getExerciseList();
        if(exerciseList == null) {
            routine.generateExerciseList();
            exerciseList = routine.getExerciseList();
        } else if(exerciseList.contains(exercise)){
            System.out.println("삐빅 겹침");
            throw new ExistExerciseException(exercise.getExerciseName(), routine.getRoutineName());
        }
        System.out.println("@@@저장하는 운동 : " + exercise.getExerciseId());
        routine.getExerciseList().add(exercise);
        routineRepository.flush();


        return ResponseByRoutine(routine);
    }

    public RoutineDto.RoutineInfoResponse deleteExercise(RoutineDto.ExerciseRequest routineDto) throws NotFoundException {
        Routine routine = routineRepository.findById(routineDto.getRoutineId()).orElseThrow(()-> new NotFoundException("존재하지 않는 루틴입니다."));
        Exercise exercise = exerciseRepository.findById(routineDto.getExerciseId()).orElseThrow(() -> new NotExistExerciseException());
        Set<Exercise> exerciseList = routine.getExerciseList();
        if(exerciseList == null) {
            throw new NotExistExerciseException();
        }

        if(!exerciseList.contains(exercise)){
            throw new NotExistExerciseException(exercise.getExerciseName(), routine.getRoutineName());
        }
        exerciseList.remove(exercise);
        return ResponseByRoutine(routine);
    }

    public RoutineDto.FullInfoResponse getFullInfoRoutine(Long routineId) throws NotFoundException {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()-> new NotFoundException("존재하지 않는 루틴입니다."));
        Set<Exercise> exerciseList = routine.getExerciseList();

        RoutineDto.FullInfoResponse response = RoutineDto.FullInfoResponse.builder()
                                                                    .routineId(routine.getRoutineId())
                                                                    .routineName(routine.getRoutineName())
                                                                    .exerciseList(exerciseList.stream().map(exercise -> exerciseService.ResponseByExercise(exercise)).collect(Collectors.toList()))
                                                                    .build();

        return response;
    }


    public RoutineDto.RoutineInfoResponse ResponseByRoutine(Routine routine) {
        RoutineDto.RoutineInfoResponse response = modelMapper.map(routine, RoutineDto.RoutineInfoResponse.class);
        Optional<Account> accountOptional = accountRepository.findById(jwtService.getAccountId());
        String gender = "male";
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            gender = account.getGender();
        }
        response.setImageUrl(RoutineImage.getImageUrl(routine.getRoutineId(), gender));
        return response;
    }
}
