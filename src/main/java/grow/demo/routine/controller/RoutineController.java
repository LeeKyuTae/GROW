package grow.demo.routine.controller;


import grow.demo.account.service.authorization.JwtService;
import grow.demo.routine.dto.RoutineDto;
import grow.demo.routine.service.RoutineService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping(value = "/register/routine")
    public ResponseEntity registerRoutine(@RequestBody RoutineDto.RegisterRequest request, Errors errors){
        RoutineDto.RoutineInfoResponse response = routineService.registerRoutine(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/routine")
    public ResponseEntity getRoutineInfo(@ModelAttribute RoutineDto.RoutineInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineInfoResponse response = routineService.getRoutine(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/detail")
    public ResponseEntity getRoutineListInfo(@ModelAttribute RoutineDto.RoutineListInfoRequest request, Errors errors) throws NotFoundException {
        RoutineDto.RoutineListInfoResponse response = routineService.getRoutineByCategory(request);
        return ResponseEntity.ok(response);
    }


}