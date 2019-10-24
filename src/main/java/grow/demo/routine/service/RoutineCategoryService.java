package grow.demo.routine.service;


import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.exception.ExistRoutineException;
import grow.demo.routine.repository.RoutineCategoryRepository;
import grow.demo.routine.repository.RoutineRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class RoutineCategoryService {
    private final RoutineCategoryRepository routineCategoryRepository;
    private final RoutineRepository routineRepository;

    private final RoutineService routineService;

    private final ModelMapper modelMapper;

    public RoutineCategoryDto.CategoryResponse registerRoutineCategory(RoutineCategoryDto.RegisterRequest routineCategoryDto){
        RoutineCategory routineCategory = RoutineCategory.builder()
                                                    .categoryType(routineCategoryDto.getRoutineCategoryType())
                                                    .categoryName(routineCategoryDto.getCategoryName())
                                                    .build()
                                                    ;
        routineCategory = routineCategoryRepository.save(routineCategory);
        return responseByRoutineCategory(routineCategory);
    }

    public List<RoutineCategoryDto.CategoryResponse> getAccountCategory(RoutineCategoryDto.MyCategoryRequest request, Long userId){
        List<RoutineCategory> routineCategory = routineCategoryRepository.findAllByCategoryType(request.getRoutineCategoryType());
        List<RoutineCategoryDto.CategoryResponse> responses = new ArrayList<>();
        for(RoutineCategory category : routineCategory){
            responses.add(responseByRoutineCategory(category));
        }
        return responses;
    }

    public RoutineCategoryDto.CategoryResponse getCategory(Long categoryId) throws NotFoundException {
        RoutineCategory category = routineCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리 입니다."));
        return responseByRoutineCategory(category);
    }

    public RoutineCategoryDto.CategoryResponse addRoutine(RoutineCategoryDto.RoutineRequest request) throws NotFoundException {
        Routine routine = routineRepository.findById(request.getCategoryId()).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴입니다."));
        RoutineCategory routineCategory = routineCategoryRepository.findById(request.getRoutineId()).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴카테고리입니다."));

        List<Routine>  routineList = routineCategory.getRoutineList();
        if(routineList == null){
            routineList = new ArrayList<>();
            routineCategory.builder().routineList(routineList).build();
        }
        else if(routineList.contains(routine)){
            throw new ExistRoutineException(routine.getRoutineName());
        }
        routineList.add(routine);
        return responseByRoutineCategory(routineCategory);
    }

    public RoutineCategoryDto.CategoryResponse responseByRoutineCategory(RoutineCategory routineCategory){
        RoutineCategoryDto.CategoryResponse categoryResponse = modelMapper.map(routineCategory, RoutineCategoryDto.CategoryResponse.class);
        return categoryResponse;
    }
}
