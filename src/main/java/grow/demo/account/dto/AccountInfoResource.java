package grow.demo.account.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class AccountInfoResource extends Resource<AccountInfoDto> {
    public AccountInfoResource(AccountInfoDto content, Link... links) {
        super(content, links);
    }
}
