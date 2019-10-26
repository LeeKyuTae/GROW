package grow.demo.routine.controller;


import grow.demo.account.domain.Account;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.service.RoutineCategoryService;
import grow.demo.routine.service.RoutineService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/category")
public class RoutineCategoryController {
    private final RoutineCategoryService routineCategoryService;
    private final RoutineService routineService;

    private final JwtService jwtService;

    @PostMapping
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



        RoutineCategoryDto.CategoryResponse response = routineCategoryService.registerRoutineCategory(routineCategoryDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("-routine")
    public ResponseEntity addRoutineToCategory(@RequestBody RoutineCategoryDto.RoutineRequest routineRequest, Errors error) throws NotFoundException {
        RoutineCategoryDto.CategoryResponse response = routineCategoryService.addRoutine(routineRequest);


        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getRoutineCategory(@ModelAttribute RoutineCategoryDto.MyCategoryRequest request){
        List<RoutineCategoryDto.CategoryResponse> response = routineCategoryService.getAccountCategory(request, Long.valueOf(1));


        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRoutineCategory(@PathVariable Long categoryId) throws NotFoundException {
        RoutineCategoryDto.CategoryResponse response = routineCategoryService.getCategory(categoryId);
        return ResponseEntity.ok(response);
    }
}
