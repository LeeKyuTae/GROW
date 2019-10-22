package grow.demo.routine.service;


import grow.demo.routine.domain.routine.Routine;
import grow.demo.routine.domain.routine.RoutineCollection;
import grow.demo.routine.domain.routine.RoutineCollectionRole;
import grow.demo.routine.dto.RoutineCollectionDto;
import grow.demo.routine.dto.RoutineDto;
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

@AllArgsConstructor
@Transactional
@Service
public class RoutineCollectionService {
    private final RoutineCollectionRepository routineCollectionRepository;
    private final RoutineRepository routineRepository;

    public RoutineCollectionDto registerRoutineCollection(RoutineCollectionDto routineCollectionDto, RoutineCollectionRole role){
        RoutineCollection routineCollection = RoutineCollection.builder()
                                                    .collectionRole(role)
                                                    .routineList(routineCollectionDto.getRoutineList())
                                                    .routineName(routineCollectionDto.getRoutineName())
                                                    .build()
                                                    ;
        routineCollection = routineCollectionRepository.save(routineCollection);
        return dtoBYRoutineCollection(routineCollection);
    }

    public RoutineCollectionDto addRoutineToCollection(Long collectionId, Long routineId) throws NotFoundException {
        Optional<Routine> routineOptional = routineRepository.findById(routineId);
        Optional<RoutineCollection> routineCollectionOptional = routineCollectionRepository.findById(collectionId);
        if(!routineCollectionOptional.isPresent() || !routineOptional.isPresent()){
            throw new NotFoundException("존재하지 않는 루틴입니다.");
        }

        Routine routine = routineOptional.get();
        RoutineCollection routineCollection = routineCollectionOptional.get();

        List<Routine>  routineList = routineCollection.getRoutineList();
        if(routineList == null){
            routineList = new ArrayList<>();
            routineCollection.builder().routineList(routineList).build();
        }
        else if(routineList.contains(routine)){
            throw new ExistRoutineException(routine.getRoutineName());
        }
        routineList.add(routine);
        return dtoBYRoutineCollection(routineCollection);
    }

    public RoutineCollectionDto dtoBYRoutineCollection(RoutineCollection routineCollection){
        RoutineCollectionDto routineCollectionDto = RoutineCollectionDto.builder()
                                                    .routineList(routineCollection.getRoutineList())
                                                    .routineName(routineCollection.getRoutineName())
                                                    .id(routineCollection.getCollectionId())
                                                    .role(routineCollection.getCollectionRole())
                                                    .build()
                                                    ;
        return routineCollectionDto;
    }
}
