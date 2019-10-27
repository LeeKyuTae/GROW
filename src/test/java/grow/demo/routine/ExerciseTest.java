package grow.demo.routine;

import grow.demo.common.BaseControllerTest;
import grow.demo.routine.domain.exercise.ExerciseMotion;
import grow.demo.routine.domain.exercise.ExercisePartial;
import grow.demo.routine.domain.exercise.ExerciseTool;
import grow.demo.routine.dto.ExerciseDto;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashSet;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExerciseTest extends BaseControllerTest {

    @Test
    public void createEvent() throws Exception {
        //GIVEN
        ExerciseDto.RegisterRequest request = ExerciseDto.RegisterRequest.builder()
                .exerciseName("더블 펀치 어셈블")
                .exerciseMotions(new HashSet<>(Arrays.asList(ExerciseMotion.Press, ExerciseMotion.Squat)))
                .exercisePartials(new HashSet<>(Arrays.asList(ExercisePartial.Chest, ExercisePartial.Arm)))
                .exerciseTool(ExerciseTool.Barbell)
                .build()
                ;


        //WHEN
        mockMvc.perform(post("/exercise")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(request)))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("exerciseId").exists())
                        .andExpect(jsonPath("exerciseName").exists())
                        .andExpect(jsonPath("exercisePartials").exists())
                        .andExpect(jsonPath("exerciseMotions").exists())
                        .andExpect(jsonPath("exerciseTool").exists())
                .andDo(document("create-exercise",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("exerciseName").description("운동명"),
                                fieldWithPath("exercisePartials").description("운동 적용 부위"),
                                fieldWithPath("exerciseMotions").description("운동 사용 동작"),
                                fieldWithPath("exerciseTool").description("운동 사용 기구")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                        ),
                        responseFields(
                                fieldWithPath("exerciseId").description("운동 고유 번호"),
                                fieldWithPath("exerciseName").description("운동명"),
                                fieldWithPath("exercisePartials").description("운동 적용 부위"),
                                fieldWithPath("exerciseMotions").description("운동 사용 동작"),
                                fieldWithPath("exerciseTool").description("운동 사용 기구")
                        )
                        ));







        //THEN
    }
}
