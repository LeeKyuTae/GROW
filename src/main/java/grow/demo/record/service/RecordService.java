package grow.demo.record.service;

import grow.demo.account.domain.Account;
import grow.demo.account.repository.AccountRepository;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.record.domain.Records;
import grow.demo.record.dto.RecordsDto;
import grow.demo.record.repository.RecordRepository;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.SetInfo;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import grow.demo.routine.repository.RoutineRepository;
import grow.demo.routine.repository.RoutineSetRepository;
import grow.demo.routine.service.ExerciseService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class RecordService {
    private final ModelMapper modelMapper;

    private final RecordRepository recordRepository;

    private final RoutineSetRepository routineSetRepository;

    private final ExerciseRepository exerciseRepository;

    private final RoutineRepository routineRepository;

    private final JwtService jwtService;


    public RecordsDto.RecordResponse addRecord(RecordsDto.RegisterRequest request) throws NotFoundException {
        SetInfo  setInfo = routineSetRepository.findById(request.getSetId()).orElseThrow(() ->new NotFoundException("존재하지 않는 세트입니다."));
        Account account = setInfo.getAccount();
        Exercise exercise = setInfo.getExercise();
        Routine routine = setInfo.getRoutine();

        Records records = Records.builder()
                                .account(account)
                                .exercise(exercise)
                                .reps(setInfo.getReps())
                                .restTime(setInfo.getRestTime())
                                .setNumber(setInfo.getSetNumber())
                                .userWeight(account.getWeight())
                                .toolWeight(setInfo.getWeight())
                                .routine(routine)
                                .build();

        records = recordRepository.save(records);
        return responseByRecords(records);
    }

    public List<RecordsDto.RecordResponse> getRecordList(LocalDateTime date){
        LocalDate pre = date.toLocalDate();
        LocalDate next = pre.plusDays(1);
        List<RecordsDto.RecordResponse> response = recordRepository.findAllByExerciseDateBetween(pre,next).stream().map(records -> responseByRecords(records)).collect(Collectors.toList());
        return response;
    }

    public List<RecordsDto.RecordResponse> getRecordListByExerciseId(Long exerciseId){
        Exercise exercise = exerciseRepository.findById(exerciseId)  .orElseThrow(() -> new NotExistExerciseException());
        List<RecordsDto.RecordResponse> response = recordRepository.findAllByExercise_ExerciseId(exercise.getExerciseId()).stream().map(records -> responseByRecords(records)).collect(Collectors.toList());
        return response;
    }

    public List<RecordsDto.ExerciseResponse> getAllExerciseList(){
        Long acountId = jwtService.getAccountId();
        List<Records> recordsList = recordRepository.findAllByAccount_Id(acountId);
        Set<Exercise> exerciseList = new HashSet<>();
        for(Records records : recordsList){
            exerciseList.add(records.getExercise());
        }
        List<RecordsDto.ExerciseResponse> response = exerciseList.stream().map(exercise -> responseByRecordsExercise(exercise)).collect(Collectors.toList());
        return response;
    }

    public RecordsDto.ExerciseResponse responseByRecordsExercise(Exercise exercise){
        RecordsDto.ExerciseResponse response = modelMapper.map(exercise, RecordsDto.ExerciseResponse.class);
        return response;
    }


    public RecordsDto.RecordResponse responseByRecords(Records records){
        RecordsDto.RecordResponse response = modelMapper.map(records, RecordsDto.RecordResponse.class);
        return response;
    }
}
