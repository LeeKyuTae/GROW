package grow.demo.routine.controller;


import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCollectionRole;
import grow.demo.routine.dto.RoutineCollectionDto;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.service.RoutineCollectionService;
import grow.demo.routine.service.RoutineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController(value = "/routinCollection")
public class RoutineCollectionController {
    private final RoutineCollectionService routineCollectionService;
    private final RoutineService routineService;

    @PostMapping("/register")
    public ResponseEntity registerRoutineCollection(@RequestBody RoutineCollectionDto routineCollectionDto, Errors error){
        List<Routine> routineList = routineCollectionDto.getRoutineList();
        if(routineList == null){
            routineList = new ArrayList<>();
        }else{
            for(Routine routine : routineList){
                RoutineDto routineDto = routineService.routineDtoByRoutine(routine);
                routineService.registerRoutine(routineDto);
            }
        }

        RoutineCollectionDto routineCollectionDtos = routineCollectionService.registerRoutineCollection(routineCollectionDto, RoutineCollectionRole.CUSTOM);
        return ResponseEntity.ok(routineCollectionDtos);
    }

}
