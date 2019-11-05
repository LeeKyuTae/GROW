package grow.demo.record.service;

import grow.demo.account.domain.Account;
import grow.demo.account.repository.AccountRepository;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.record.domain.Records;
import grow.demo.record.dto.RecordsDto;
import grow.demo.record.repository.RecordRepository;
import grow.demo.routine.domain.exercise.Exercise;
import grow.demo.routine.domain.routine.SetInfo;
import grow.demo.routine.repository.ExerciseRepository;
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
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class RecordService {
    private final ModelMapper modelMapper;

    private final RecordRepository recordRepository;

    private final RoutineSetRepository routineSetRepository;


    public RecordsDto.RecordResponse addRecord(RecordsDto.RegisterRequest request) throws NotFoundException {
        SetInfo  setInfo = routineSetRepository.findById(request.getSetId()).orElseThrow(() ->new NotFoundException("존재하지 않는 세트입니다."));
        Account account = setInfo.getAccount();
        Exercise exercise = setInfo.getExercise();

        Records records = Records.builder()
                                .account(account)
                                .exercise(exercise)
                                .reps(setInfo.getReps())
                                .restTime(setInfo.getRestTime())
                                .setNumber(setInfo.getSetNumber())
                                .userWeight(account.getWeight())
                                .toolWeight(setInfo.getWeight())
                                .build();

        records = recordRepository.save(records);
        return responseByRecords(records);
    }

    public RecordsDto.RecordResponse responseByRecords(Records records){
        RecordsDto.RecordResponse response = modelMapper.map(records, RecordsDto.RecordResponse.class);
        return response;
    }
}
