package grow.demo.record.service;


import grow.demo.account.domain.Account;
import grow.demo.account.repository.AccountRepository;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.record.domain.BestRecord;
import grow.demo.record.dto.BestRecordDto;
import grow.demo.record.dto.RecordsDto;
import grow.demo.record.repository.BestRecordRepository;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.exception.NotExistExerciseException;
import grow.demo.routine.repository.ExerciseRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class BestRecordService {
    private final ModelMapper modelMapper;

    private final BestRecordRepository bestRecordRepository;

    private final AccountRepository accountRepository;

    private final ExerciseRepository exerciseRepository;

    private final JwtService jwtService;

    final Comparator<BestRecord> maxComparator = (r1, r2) -> Float.compare(r1.getWeight(), r2.getWeight());

    public BestRecordDto.RecordResponse registerBestRecord(BestRecordDto.RegisterRequest request, Long accountId) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        Exercise exercise = exerciseRepository.findById(request.getExerciseId()).orElseThrow(() -> new NotExistExerciseException());



        BestRecord bestRecord = BestRecord.builder()
                                        .exercise(exercise)
                                        .weight(request.getWeight())
                                        .account(account)
                                        .build();

        bestRecord = bestRecordRepository.save(bestRecord);
        return ResponseByBestRecord(bestRecord);
    }

    public BestRecordDto.RecordResponse getBestRecord(BestRecordDto.InfoRequest request, Long accountId) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        BestRecord bestRecord = bestRecordRepository.findAllByExercise_ExerciseIdOrderByExerciseDate(request.getExerciseId()).stream().max(maxComparator).get();
        return ResponseByBestRecord(bestRecord);
    }

    public List<BestRecordDto.RecordResponse> getBestRecordList(BestRecordDto.InfoRequest request, Long accountId) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        List<BestRecord> bestRecordList = bestRecordRepository.findAllByExercise_ExerciseIdOrderByExerciseDate(request.getExerciseId());
        List<BestRecordDto.RecordResponse> response = new ArrayList<>();
        for(BestRecord bestRecord : bestRecordList){
            response.add(ResponseByBestRecord(bestRecord));
        }
        return response;
    }

    public List<RecordsDto.ExerciseResponse> getAllExerciseList() {
        Long acountId = jwtService.getAccountId();
        List<BestRecord> recordsList = bestRecordRepository.findAllByAccount_Id(acountId);
        Set<Exercise> exerciseList = new HashSet<>();
        for(BestRecord records : recordsList){
            exerciseList.add(records.getExercise());
        }
        List<RecordsDto.ExerciseResponse> response = exerciseList.stream().map(exercise -> responseByRecordsExercise(exercise)).collect(Collectors.toList());
        return response;
    }

    public List<RecordsDto.ExerciseResponse> getFiveExerciseList() {
        List<String> arr = new ArrayList<>();
        arr.add("바벨 스쿼트");
        arr.add("바벨 벤치 프레스");
        arr.add("바벨 로우");
        arr.add("바벨 오버헤드 프레스");
        arr.add("바벨 데드리프트");
        List<RecordsDto.ExerciseResponse> response = new ArrayList<>();
        for(String exerciseName : arr){
            Exercise exercise = exerciseRepository.findByExerciseName(exerciseName);
            response.add(responseByRecordsExercise(exercise));
        }
        return response;
    }

    public RecordsDto.ExerciseResponse responseByRecordsExercise(Exercise exercise){
        RecordsDto.ExerciseResponse response = modelMapper.map(exercise, RecordsDto.ExerciseResponse.class);
        return response;
    }




    public BestRecordDto.RecordResponse ResponseByBestRecord(BestRecord bestRecord){
        BestRecordDto.RecordResponse response = BestRecordDto.RecordResponse.builder()
                                                        .exerciseDate(bestRecord.getExerciseDate())
                                                        .exerciseId(bestRecord.getExercise().getExerciseId())
                                                        .exerciseName(bestRecord.getExercise().getExerciseName())
                                                        .weight(bestRecord.getWeight())
                                                        .recordId(bestRecord.getId())
                                                        .build();
        return response;
    }
}
