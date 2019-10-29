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

    //TEST 3 , 6
    @Test
    public void registerRoutine() throws Exception {
        //GIVEN
        RoutineDto.RegisterRequest request = RoutineDto.RegisterRequest.builder()
                .routineName("FINAL test")
                .categoryId(Long.valueOf(4))
                .build();
        //WHEN
        mockMvc.perform(post("/routine")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-routine",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("routineName").description("루틴명"),
                                fieldWithPath("categoryId").description("카테고리 고유번호")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                        ),
                        responseFields(
                                fieldWithPath("responses[].routineId").description("루틴 고유 번호"),
                                fieldWithPath("responses[].routineName").description("루틴명")

                        )
                ));
    }

    @Test
    public void addExerciseToRoutine() throws Exception {
        //GIVEN
        RoutineDto.ExerciseRequest request = RoutineDto.ExerciseRequest.builder()
                                                                .exerciseId(Long.valueOf("3"))
                                                                .routineId(Long.valueOf("5"))
                                                                .build();

        mockMvc.perform(post("/routine/exercise")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("routineId").exists())
                .andExpect(jsonPath("routineName").exists())
                .andDo(document("add exercise to routine",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("exerciseId").description("운동 아이디"),
                                fieldWithPath("routineId").description("루틴 아이디")
                        ),
                        responseFields(
                                fieldWithPath("routineId").description("루틴 고유 번호"),
                                fieldWithPath("routineName").description("루틴명")

                        )
                ));
    }

    @Test
    public void registerRoutineToCategory() throws Exception {

    }
    @Test
    public void getFullInfoToRoutine() throws Exception{
        mockMvc.perform(get("/routine/exercise-list")
                        .param("routineId", "5")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("routineId").exists())
                .andExpect(jsonPath("routineName").exists())
                .andExpect(jsonPath("exerciseList").exists())
                .andDo(document("get-routine-full-information",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        responseFields(
                                fieldWithPath("routineId").description("루틴 고유 번호"),
                                fieldWithPath("routineName").description("루틴명"),
                                fieldWithPath("exerciseList[]").description("루틴에 포함된 운동 정보"),
                                fieldWithPath("exerciseList[].exerciseId").description("운동 고유 번호"),
                                fieldWithPath("exerciseList[].exerciseName").description("운동명"),
                                fieldWithPath("exerciseList[].exercisePartials").description("운동 적용 부위"),
                                fieldWithPath("exerciseList[].exerciseMotions").description("운동 사용 동작"),
                                fieldWithPath("exerciseList[].exerciseTool").description("운동 사용 기구")
                        )
                ));

    }

    @Test
    public void getFullInfoToRoutineWithNullExercise() throws Exception  {
        mockMvc.perform(get("/routine/exercise-list")
                .param("routineId", "6")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("routineId").exists())
                .andExpect(jsonPath("routineName").exists())
                .andExpect(jsonPath("exerciseList").exists())
        ;
    }

}
