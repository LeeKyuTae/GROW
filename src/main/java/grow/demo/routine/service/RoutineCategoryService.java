package grow.demo.routine.service;


import grow.demo.account.domain.Account;
import grow.demo.account.dto.AccountDto;
import grow.demo.account.repository.AccountRepository;
import grow.demo.account.service.user.AccountService;
import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.exception.ExistRoutineException;
import grow.demo.routine.exception.NotModifyCategoryException;
import grow.demo.routine.repository.RoutineCategoryRepository;
import grow.demo.routine.repository.RoutineRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class RoutineCategoryService {
    private final RoutineCategoryRepository routineCategoryRepository;
    private final RoutineRepository routineRepository;

    private final RoutineService routineService;

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    public List<RoutineCategoryDto.CategoryResponse> getRoutineCategoryList(Long accountId, RoutineCategoryType type) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        List<RoutineCategory> categoryList = account.getRoutineCategoryList().stream()
                                                                .filter(routineCategory -> routineCategory.getCategoryType().equals(type))
                                                                .collect(Collectors.toList());
        List<RoutineCategoryDto.CategoryResponse> response = new ArrayList<>();
        for(RoutineCategory category : categoryList){
            response.add(responseByRoutineCategory(category));
        }
        return response;
    }

    public RoutineCategoryDto.CategoryResponse registerRoutineCategory(RoutineCategoryDto.RegisterRequest routineCategoryDto, Long accountId) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        RoutineCategory routineCategory = RoutineCategory.builder()
                                                    .categoryType(routineCategoryDto.getCategoryType())
                                                    .categoryName(routineCategoryDto.getCategoryName())
                                                    .build()
                                                    ;
        routineCategory = routineCategoryRepository.save(routineCategory);
        List<RoutineCategory> categoryList = account.getRoutineCategoryList();
        if(categoryList == null) {
            categoryList = new ArrayList<>();
            account.registerCategoryList(categoryList);
        }
        categoryList.add(routineCategory);
        return responseByRoutineCategory(routineCategory);
    }

    public RoutineCategory getCategory(Long categoryId) throws NotFoundException {
        RoutineCategory category = routineCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리 입니다."));
        return category;
    }

    public List<RoutineCategory> getRecommendedCategory() {
        List<RoutineCategory> categoryList = routineCategoryRepository.findAllByCategoryType(RoutineCategoryType.RECOMMEND);
        return categoryList;
    }


    public RoutineCategoryDto.CategoryResponse addRoutine(RoutineCategoryDto.RegisterRoutineRequest request) throws NotFoundException {
        Routine routine = routineRepository.findById(request.getRoutineId()).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴입니다."));
        RoutineCategory routineCategory = routineCategoryRepository.findById(request.getCategoryId()).orElseThrow(() ->  new NotFoundException("존재하지 않는 루틴카테고리입니다."));

        boolean registerCategory = routine.registerCategory(routineCategory);
        if(registerCategory == false)
            throw new NotModifyCategoryException();
        return responseByRoutineCategory(routineCategory);
        /*
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
         */
    }


    public RoutineCategoryDto.FullCategoryResponse getCategoryWithRoutineInfo(Long categoryId) throws NotFoundException {
        RoutineCategory category = routineCategoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리 입니다."));

        RoutineCategoryDto.FullCategoryResponse response = RoutineCategoryDto.FullCategoryResponse.builder()
                                                                                        .categoryId(category.getCategoryId())
                                                                                        .categoryName(category.getCategoryName())
                                                                                        .categoryType(category.getCategoryType())
                                                                                        .routineList(category.getRoutineList().stream().map(routine -> routineService.ResponseByRoutine(routine))
                                                                                                                                        .collect(Collectors.toList()))
                                                                                        .build();
        return response;
    }

    public RoutineCategoryDto.CategoryResponse responseByRoutineCategory(RoutineCategory routineCategory){
        RoutineCategoryDto.CategoryResponse categoryResponse = modelMapper.map(routineCategory, RoutineCategoryDto.CategoryResponse.class);
        return categoryResponse;
    }
}
