package grow.demo.account.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class AccountResource extends Resource<AccountDto> {
    public AccountResource(AccountDto content, Link... links) {
        super(content, links);
    }
}
