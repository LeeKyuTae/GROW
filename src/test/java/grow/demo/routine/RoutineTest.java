package grow.demo.routine;

import grow.demo.common.BaseControllerTest;
import grow.demo.routine.dto.RoutineDto;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoutineTest extends BaseControllerTest {

    @Test
    public void getRoutine() throws Exception {
        //GIVEN
        /*
        RoutineDto.RoutineInfoRequest request = RoutineDto.RoutineInfoRequest.builder()
                                                                            .routineId(Long.valueOf("4"))
                                                                            .build();


         */
        //WHEN
        mockMvc.perform(get("/routine")
                        .param("routineId", "5")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)

                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("routineId").exists())
                .andExpect(jsonPath("routineName").exists())
                .andDo(document("get-routine",
                        responseFields(
                                fieldWithPath("routineId").description("루틴 고유 번호"),
                                fieldWithPath("routineName").description("루티명")

                        )
                ));
    }

    @Test
    public void registerRoutine() throws Exception {
        //GIVEN
        RoutineDto.RegisterRequest request = RoutineDto.RegisterRequest.builder()
                .routineName("명준 시크릿")
                .build();
        //WHEN
        mockMvc.perform(post("/routine")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("routineId").exists())
                .andExpect(jsonPath("routineName").exists())
                .andDo(document("create-routine",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("routineName").description("루틴명")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                        ),
                        responseFields(
                                fieldWithPath("routineId").description("루틴 고유 번호"),
                                fieldWithPath("routineName").description("루티명")

                        )
                ));
    }
}
