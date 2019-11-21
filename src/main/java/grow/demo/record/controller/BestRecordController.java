package grow.demo.record.controller;


import grow.demo.account.service.authorization.JwtService;
import grow.demo.record.dto.BestRecordDto;
import grow.demo.record.dto.RecordsDto;
import grow.demo.record.service.BestRecordService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/1rm")
public class BestRecordController {
    private final JwtService jwtService;
    private final BestRecordService bestRecordService;

    @PostMapping
    public ResponseEntity registerBestRecord(@RequestBody @Valid BestRecordDto.RegisterRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }
        Long accountId = jwtService.getAccountId();
        BestRecordDto.RecordResponse response = bestRecordService.registerBestRecord(request, accountId);
        ControllerLinkBuilder selfLinkBuilder = linkTo(BestRecordController.class).slash(response.getRecordId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping
    public ResponseEntity getBestRecord(@ModelAttribute @Valid BestRecordDto.InfoRequest request) throws NotFoundException {
        Long accountId = jwtService.getAccountId();
        BestRecordDto.RecordResponse response = bestRecordService.getBestRecord(request, accountId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity getBestRecordList(@ModelAttribute @Valid BestRecordDto.InfoRequest request) throws NotFoundException {
        Long accountId = jwtService.getAccountId();
        List<BestRecordDto.RecordResponse> response = bestRecordService.getBestRecordList(request, accountId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exercise")
    public ResponseEntity getAllExerciseList(){
        List<RecordsDto.ExerciseResponse> response = bestRecordService.getAllExerciseList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/5best")
    public ResponseEntity get5BestExerciseList(){
        List<RecordsDto.ExerciseResponse> response = bestRecordService.getFiveExerciseList();
        for(RecordsDto.ExerciseResponse value : response){
            System.out.println("VALUE: " + value.getExerciseName());
        }
        return ResponseEntity.ok(response);
    }

}
