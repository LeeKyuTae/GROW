package grow.demo.routine.controller;


import grow.demo.account.domain.Account;
import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.service.RoutineCategoryService;
import grow.demo.routine.service.RoutineService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/category")
public class RoutineCategoryController {
    private final RoutineCategoryService routineCategoryService;
    private final RoutineService routineService;

    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity registerRoutineCategory(@RequestBody @Valid RoutineCategoryDto.RegisterRequest routineCategoryDto, Errors error) throws NotFoundException {
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

        Long accountId = jwtService.getAccountId();
        RoutineCategoryType type = routineCategoryDto.getCategoryType();
        // 유저가 ADMIN이 아닌데 type이 RECOMMEND 일경우 -> BadRequest


        //타입이 RECOMMEND 일 경우 모든 유저에게 해당 카테고리 추가해주기
        if(type.equals(RoutineCategoryType.RECOMMEND)){
            routineCategoryService.registerRecommendCategory(routineCategoryDto, accountId);
        }

        RoutineCategoryDto.CategoryResponse category = routineCategoryService.registerRoutineCategory(routineCategoryDto, accountId);
        ControllerLinkBuilder selfLinkBuilder = linkTo(RoutineCategoryController.class).slash(category.getCategoryId());
        URI createdUri = selfLinkBuilder.toUri();
        List<RoutineCategoryDto.CategoryResponse> response = routineCategoryService.getRoutineCategoryList(accountId, category.getCategoryType());
        return ResponseEntity.created(createdUri).body(response);
    }

    @PostMapping("/routine")
    public ResponseEntity addRoutineToCategory(@RequestBody @Valid RoutineCategoryDto.RegisterRoutineRequest routineRequest, Errors error) throws NotFoundException {
        RoutineCategoryDto.CategoryResponse response = routineCategoryService.addRoutine(routineRequest);
        if(error.hasErrors()){
            return ResponseEntity.badRequest().body(error);
        }
        //RECOMMEND의 경우 ADMIN 유저만, CUSTOM의 경우 제작자만 추가할 수 있는 로직 필요
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRoutineCategory(@PathVariable Long id) throws NotFoundException {
        RoutineCategoryDto.CategoryResponse response = routineCategoryService.responseByRoutineCategory(routineCategoryService.getCategory(id));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity getRoutineCategoryByType(@ModelAttribute @Valid RoutineCategoryDto.MyCategoryRequest request) throws NotFoundException {
        List<RoutineCategoryDto.CategoryResponse> response = routineCategoryService.getRoutineCategoryList(jwtService.getAccountId(), request.getCategoryType());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/routines")
    public ResponseEntity getRoutineCategoryWithRoutine(@ModelAttribute @Valid RoutineCategoryDto.CategoryRequest request) throws NotFoundException {
        List<RoutineDto.RoutineInfoResponse> response = routineCategoryService.getCategoryWithRoutineInfo(request.getCategoryId());
        return ResponseEntity.ok(response);
    }
}

