package grow.demo.routine.controller;


import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.service.RoutineCategoryService;
import grow.demo.routine.service.RoutineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController(value = "/routinCategory")
public class RoutineCategoryController {
    private final RoutineCategoryService routineCategoryService;
    private final RoutineService routineService;

    @PostMapping("/register")
    public ResponseEntity registerRoutineCategory(@RequestBody RoutineCategoryDto.RegisterRequest routineCategoryDto, Errors error){

        /*
        List<RoutineDto> routineList = routineCategoryDto.getRoutineList();
        if(routineList == null){
            routineList = new ArrayList<>();
        }else{
            for(RoutineDto routineDto : routineList){
                routineService.registerRoutine(routineDto);
            }
        }

         */

        RoutineCategoryType type = routineCategoryDto.getRoutineCategoryType();
        // 유저가 ADMIN이 아닌데 type이 RECOMMEND 일경우 -> BadRequest



        RoutineCategoryDto.CategoryResponse routineCategoryDtos = routineCategoryService.registerRoutineCategory(routineCategoryDto);
        return ResponseEntity.ok(routineCategoryDtos);
    }

}
