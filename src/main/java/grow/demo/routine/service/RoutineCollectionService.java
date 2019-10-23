package grow.demo.routine.service;


import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.exception.ExistRoutineException;
import grow.demo.routine.repository.RoutineCollectionRepository;
import grow.demo.routine.repository.RoutineRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class RoutineCollectionService {
    private final RoutineCollectionRepository routineCollectionRepository;
    private final RoutineRepository routineRepository;

    private final RoutineService routineService;

    public RoutineCategoryDto registerRoutineCollection(RoutineCategoryDto routineCategoryDto, RoutineCategoryType role){
        List<Routine> routineList = routineCategoryDto.getRoutineList().stream().map(routineDto -> routineService.registerRoutine(routineDto)).collect(Collectors.toList());
        RoutineCategory routineCategory = RoutineCategory.builder()
                                                    .collectionRole(role)
                                                    .routineList(routineList)
                                                    .routineName(routineCategoryDto.getRoutineName())
                                                    .build()
                                                    ;
        routineCategory = routineCollectionRepository.save(routineCategory);
        return dtoBYRoutineCollection(routineCategory);
    }

    public RoutineCategoryDto addRoutineToCollection(Long collectionId, Long routineId) throws NotFoundException {
        Optional<Routine> routineOptional = routineRepository.findById(routineId);
        Optional<RoutineCategory> routineCollectionOptional = routineCollectionRepository.findById(collectionId);
        if(!routineCollectionOptional.isPresent() || !routineOptional.isPresent()){
            throw new NotFoundException("존재하지 않는 루틴입니다.");
        }

        Routine routine = routineOptional.get();
        RoutineCategory routineCategory = routineCollectionOptional.get();

        List<Routine>  routineList = routineCategory.getRoutineList();
        if(routineList == null){
            routineList = new ArrayList<>();
            routineCategory.builder().routineList(routineList).build();
        }
        else if(routineList.contains(routine)){
            throw new ExistRoutineException(routine.getRoutineName());
        }
        routineList.add(routine);
        return dtoBYRoutineCollection(routineCategory);
    }

    public RoutineCategoryDto dtoBYRoutineCollection(RoutineCategory routineCategory){
        RoutineCategoryDto routineCategoryDto = RoutineCategoryDto.builder()
                                                    .routineList(routineCategory.getRoutineList().stream().map(routine -> routineService.routineDtoByRoutine(routine)).collect(Collectors.toList()))
                                                    .routineName(routineCategory.getRoutineName())
                                                    .id(routineCategory.getCollectionId())
                                                    .role(routineCategory.getCollectionRole())
                                                    .build()
                                                    ;
        return routineCategoryDto;
    }
}
