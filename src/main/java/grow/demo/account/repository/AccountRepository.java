package grow.demo.account.repository;

import grow.demo.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long > {

    Optional<Account> findByKakaoId(Long kakaoId);
    Boolean existsAccountByKakaoId(Long kakaoId);
}
