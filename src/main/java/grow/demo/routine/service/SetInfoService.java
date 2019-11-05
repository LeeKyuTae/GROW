package grow.demo.routine.service;


import grow.demo.account.domain.Account;
import grow.demo.account.repository.AccountRepository;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.SetInfo;
import grow.demo.routine.dto.SetInfoDto;
import grow.demo.routine.repository.ExerciseRepository;
import grow.demo.routine.repository.RoutineRepository;
import grow.demo.routine.repository.RoutineSetRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class SetInfoService {

    private final ExerciseRepository exerciseRepository;

    private final RoutineSetRepository routineSetRepository;

    private final RoutineRepository routineRepository;

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    public SetInfoDto.RegisterResponse registerSetInfo(SetInfoDto.RegisterRequest request) throws NotFoundException {
        Account  account = accountRepository.findById(request.getAccountId()).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        Exercise exercise = exerciseRepository.findById(request.getExerciseId()).orElseThrow(()-> new NotFoundException("존재하지 않는 운동입니다."));
        Routine routine = routineRepository.findById(request.getRoutineId()).orElseThrow(()-> new NotFoundException("존재하지 않는 루틴입니다."));
        isContainData(account,routine,exercise);

        SetInfo setInfo = SetInfo.builder()
                                .setNumber(request.getSetNumber())
                                .reps(request.getReps())
                                .restTime(request.getRestTime())
                                .account(account)
                                .exercise(exercise)
                                .routine(routine)
                                .build();

        setInfo = routineSetRepository.save(setInfo);
        SetInfoDto.RegisterResponse response = modelMapper.map(setInfo, SetInfoDto.RegisterResponse.class);
        return response;
    }

    public void isContainData(Account account, Routine routine, Exercise exercise) throws NotFoundException {
        RoutineCategory category = routine.getRoutineCategory();
        List<RoutineCategory> checkCategoryList = account.getRoutineCategoryList();
        if(checkCategoryList.contains(category) == false){
            throw new NotFoundException("해당 루틴은 유저정보와 매칭되지 않는 정보입니다.");
        }

        List<Exercise> checkExerciseList = routine.getExerciseList();
        if(checkExerciseList.contains(exercise) == false){
            throw new NotFoundException("해당 운동은 루틴정보와 매칭되지 않는 정보입니다.");
        }

    }

    public SetInfoDto.SetInfoResponse getSetInfoCollection(Long accountId, Long routineId, Long exerciseId) {
        List<SetInfo> setInfoList = routineSetRepository.findAllByAccount_IdAndRoutine_RoutineIdAndExercise_ExerciseId(accountId, routineId, exerciseId);
        List<SetInfoDto.SetDetail> details = new ArrayList<>();
        for(SetInfo setInfo : setInfoList )
            details.add(responseBySetInfo(setInfo));

        SetInfoDto.SetInfoResponse response = SetInfoDto.SetInfoResponse.builder()
                                                            .exerciseId(exerciseId)
                                                            .routineId(routineId)
                                                            .detailList(details)
                                                            .build();
        return response;
    }

   public SetInfoDto.SetInfoResponse updateSetInfo(SetInfoDto.UpdateRequest request) throws NotFoundException {
        SetInfo setInfo = routineSetRepository.findById(request.getSetId()).orElseThrow(() -> new NotFoundException("존재하지 않는 세트입니다."));
        setInfo.updateSetInfo(request);
        return getSetInfoCollection(setInfo.getAccount().getId(), setInfo.getRoutine().getRoutineId(), setInfo.getExercise().getExerciseId());
   }

   public SetInfoDto.SetDetail getSetInfo(Long setId) throws NotFoundException {
       SetInfo setInfo = routineSetRepository.findById(setId).orElseThrow(() -> new NotFoundException("존재하지 않는 세트입니다."));
       return responseBySetInfo(setInfo);
   }

   public SetInfoDto.SetInfoResponse deleteSetInfo(Long setId) throws NotFoundException {
       SetInfo setInfo = routineSetRepository.findById(setId).orElseThrow(() -> new NotFoundException("존재하지 않는 세트입니다."));
       Integer deletedSetNum = setInfo.getSetNumber();
       SetInfoDto.SetInfoRequest request = SetInfoDto.SetInfoRequest.builder()
                                                        .accountId(setInfo.getAccount().getId())
                                                        .exerciseId(setInfo.getExercise().getExerciseId())
                                                        .routineId(setInfo.getRoutine().getRoutineId())
                                                        .build();

       routineSetRepository.delete(setInfo);
       List<SetInfo> setInfoList = routineSetRepository.findAllByAccount_IdAndRoutine_RoutineIdAndExercise_ExerciseId(setInfo.getAccount().getId(), setInfo.getRoutine().getRoutineId(), setInfo.getExercise().getExerciseId());
       setInfoList = setInfoList.stream().filter(setInfos -> setInfos.getSetNumber() > deletedSetNum)
                                            .map(info -> info.reduceSetNumber())
                                            .collect(Collectors.toList());

       return getSetInfoCollection(request.getAccountId(), request.getRoutineId(), request.getExerciseId());
   }


    public SetInfoDto.SetDetail responseBySetInfo(SetInfo setInfo){
        SetInfoDto.SetDetail response = modelMapper.map(setInfo, SetInfoDto.SetDetail.class);
        return response;
    }
}
