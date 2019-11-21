package grow.demo.account;

import grow.demo.common.BaseControllerTest;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class AccountTest extends BaseControllerTest {

    @Test
    public void deleteAccount() throws Exception {
        mockMvc.perform(delete("/account/death/{accountId}", 220L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON))
                .andDo(print());
    }
}
