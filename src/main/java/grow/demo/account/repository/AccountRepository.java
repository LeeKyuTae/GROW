package grow.demo.account.repository;

import grow.demo.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long > {
}
