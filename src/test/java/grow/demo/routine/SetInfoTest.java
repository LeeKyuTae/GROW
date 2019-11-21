package grow.demo.routine;

import grow.demo.common.BaseControllerTest;
import grow.demo.routine.dto.SetInfoDto;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SetInfoTest extends BaseControllerTest {

    @Test
    public void registerSetInfo() throws Exception {
        //GIVEN
        SetInfoDto.RegisterRequest request =  SetInfoDto.RegisterRequest.builder()
                                                        .routineId(Long.valueOf(20))
                                                        .exerciseId(Long.valueOf(3))
                                                        .reps(5)
                                                        .setNumber(2)
                                                        .restTime(80)
                                                        .weight(40F)
                                                        .build();

        //WHEN
        mockMvc.perform(post("/routine-set")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                ;

    }


    @Test
    public void getSetInfo(){}


    @Test
    public void updateSetInfo(){}

}
