package grow.demo.routine.service;


import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.exception.ExistRoutineException;
import grow.demo.routine.repository.RoutineCollectionRepository;
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
    private final RoutineCollectionRepository routineCollectionRepository;
    private final RoutineRepository routineRepository;

    private final RoutineService routineService;

    private final ModelMapper modelMapper;

    public RoutineCategoryDto.CategoryResponse registerRoutineCategory(RoutineCategoryDto.RegisterRequest routineCategoryDto){
        RoutineCategory routineCategory = RoutineCategory.builder()
                                                    .collectionType(routineCategoryDto.getRoutineCategoryType())
                                                    .categoryName(routineCategoryDto.getCategoryName())
                                                    .build()
                                                    ;
        routineCategory = routineCollectionRepository.save(routineCategory);
        return responseByRoutineCategory(routineCategory);
    }

    public RoutineCategoryDto.CategoryResponse addRoutine(RoutineCategoryDto.RoutineRequest request) throws NotFoundException {
        Routine routine = routineRepository.findById(request.getCategoryId()).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴입니다."));
        RoutineCategory routineCategory = routineCollectionRepository.findById(request.getRoutineId()).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴카테고리입니다."));

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
