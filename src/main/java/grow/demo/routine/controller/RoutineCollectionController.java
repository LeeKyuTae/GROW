package grow.demo.routine.controller;


import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
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
    public ResponseEntity registerRoutineCollection(@RequestBody RoutineCategoryDto routineCategoryDto, Errors error){
        List<RoutineDto> routineList = routineCategoryDto.getRoutineList();
        if(routineList == null){
            routineList = new ArrayList<>();
        }else{
            for(RoutineDto routineDto : routineList){
                routineService.registerRoutine(routineDto);
            }
        }

        RoutineCategoryDto routineCategoryDtos = routineCollectionService.registerRoutineCollection(routineCategoryDto, RoutineCategoryType.CUSTOM);
        return ResponseEntity.ok(routineCategoryDtos);
    }

}
