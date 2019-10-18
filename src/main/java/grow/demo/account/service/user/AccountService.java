package grow.demo.account.service.user;


import grow.demo.account.domain.Account;
import grow.demo.account.dto.AccountDto;
import grow.demo.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service @AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    public Account getAccountByKakaoID(String kakaoId){
        return accountRepository.findByKakaoId(Long.parseLong(kakaoId));
    }

    public AccountDto getAccountDto(Account account){
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        return accountDto;
    }



}
