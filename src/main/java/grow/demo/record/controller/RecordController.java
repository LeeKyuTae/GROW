package grow.demo.record.controller;


import grow.demo.record.dto.RecordsDto;
import grow.demo.record.service.RecordService;
import grow.demo.routine.controller.ExerciseController;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/record")
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    public ResponseEntity registerRecord(@RequestBody @Valid RecordsDto.RegisterRequest request, Errors errors) throws NotFoundException {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors);
        }

        RecordsDto.RecordResponse response = recordService.addRecord(request);
        ControllerLinkBuilder selfLinkBuilder = linkTo(RecordController.class).slash(response.getRecordId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(response);
    }

    @GetMapping
    public ResponseEntity getRecordsByDate(@RequestParam LocalDateTime date){
        List<RecordsDto.RecordResponse> response = recordService.getRecordList(date);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecordsByExercise(@PathVariable Long id){
        List<RecordsDto.RecordResponse> response = recordService.getRecordListByExerciseId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exercise")
    public ResponseEntity getAllExerciseList(){
        List<RecordsDto.ExerciseResponse> response = recordService.getAllExerciseList();
        return ResponseEntity.ok(response);
    }



}
