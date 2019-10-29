package grow.demo.account.service.user;


import grow.demo.account.domain.Account;
import grow.demo.account.domain.AccountRole;
import grow.demo.account.dto.AccountDto;
import grow.demo.account.exception.ExistUserException;
import grow.demo.account.repository.AccountRepository;
import grow.demo.routine.domain.routine.RoutineCategory;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import grow.demo.routine.exception.ExistCategoryException;
import grow.demo.routine.service.RoutineCategoryService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service @AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final RoutineCategoryService categoryService;

    private final ModelMapper modelMapper;

    public AccountDto.AccountResponse registerAccountByKakao(Long kakaoId) throws NotFoundException {
        Account account = Account.builder().accountRoles(new HashSet<>(Arrays.asList(AccountRole.USER)))
                                            .kakaoId(kakaoId)
                                            .build();
        if(accountRepository.existsAccountByKakaoId(kakaoId) == true){
            throw new ExistUserException();
        }
        account = accountRepository.save(account);
        AddRecommendCategory(account.getId());
        return ResponseByAccount(account);
    }

    public void AddRecommendCategory(Long accountId)throws NotFoundException {
        Account account = accountRepository.findByKakaoId(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        List<RoutineCategory> categoryList = account.getRoutineCategoryList();
        List<RoutineCategory> recommendedCategoryList = categoryService.getRecommendedCategory();
        for(RoutineCategory routineCategory : recommendedCategoryList){
            categoryList.add(routineCategory);
        }
    }

    public AccountDto.AccountResponse updateAccountInfo(AccountDto.AccountInfoUpdateRequest request, Long accountId) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        account.updateAccountInfo(request);
        return ResponseByAccount(account);
    }

    public AccountDto.AccountResponse updateWeight(AccountDto.WeightUpdateRequest request, Long accountId)throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        account.updateWeight(request);
        return ResponseByAccount(account);
    }


    public AccountDto.AccountResponse getAccountByKakaoID(Long kakaoId) throws NotFoundException {
        Account account = accountRepository.findByKakaoId(kakaoId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        return ResponseByAccount(account);
    }

    public AccountDto.AccountFullResponse addCategoryToAccount(Long accountId, Long categoryId) throws NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        RoutineCategory category = categoryService.getCategory(categoryId);
        List<RoutineCategory> categoryList = new ArrayList<>();
        categoryList = account.getRoutineCategoryList();
        if(categoryList.contains(category)){
            throw new ExistCategoryException();
        }
        categoryList.add(category);

        AccountDto.AccountFullResponse response = modelMapper.map(account, AccountDto.AccountFullResponse.class);
        List<RoutineCategoryDto.CategoryResponse> categoryResponses = categoryList.stream()
                .filter(routineCategory -> routineCategory.getCategoryType().equals(routineCategory.getCategoryType()))
                .map(routineCategory -> categoryService.responseByRoutineCategory(routineCategory))
                .collect(Collectors.toList());
        response.setRoutineCollectionList(categoryResponses);
        return response;
    }

    public AccountDto.AccountFullResponse getCategoryByTypeAndAccount(Long accountId, RoutineCategoryType type) throws NotFoundException {
        Account account = accountRepository.findByKakaoId(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        List<RoutineCategoryDto.CategoryResponse> categoryList = new ArrayList<>();
        categoryList = account.getRoutineCategoryList().stream()
                                .filter(routineCategory -> routineCategory.getCategoryType().equals(type))
                                .map(category -> categoryService.responseByRoutineCategory(category))
                                .collect(Collectors.toList());

        AccountDto.AccountFullResponse response = modelMapper.map(account, AccountDto.AccountFullResponse.class);
        response.setRoutineCollectionList(categoryList);
        return response;
    }

    public Account getAccount(Long accountId) throws NotFoundException {
        Account account = accountRepository.findByKakaoId(accountId).orElseThrow(()-> new NotFoundException("존재하지 않는 유저입니다."));
        return account;
    }


    public AccountDto.AccountResponse ResponseByAccount(Account account){
        AccountDto.AccountResponse response = modelMapper.map(account, AccountDto.AccountResponse.class);
        return response;
    }



}
