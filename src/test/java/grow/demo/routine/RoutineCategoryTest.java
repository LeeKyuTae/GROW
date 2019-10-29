package grow.demo.routine;

import grow.demo.common.BaseControllerTest;
import grow.demo.routine.domain.routine.RoutineCategoryType;
import grow.demo.routine.dto.RoutineCategoryDto;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoutineCategoryTest extends BaseControllerTest {

    //TEST 4
    @Test
    public void registerCategory() throws Exception {
        //GIVEN
        RoutineCategoryDto.RegisterRequest request = RoutineCategoryDto.RegisterRequest.builder()
                                                                            .categoryName("finalTest3")
                                                                            .categoryType(RoutineCategoryType.CUSTOM)
                                                                            .build();

        //WHEN
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-routine-category",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("categoryName").description("카테고리 이름"),
                                fieldWithPath("categoryType").description("카테고리 타입 : 커스텀(일반 유저 가능), 추천(관리자 가능)")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                        ),
                        responseFields(
                                fieldWithPath("[].categoryId").description("카테고리 고유번호"),
                                fieldWithPath("[].categoryName").description("카테고리 이름"),
                                fieldWithPath("[].categoryType").description("카테고리 타입 : 커스텀(일반 유저 가능), 추천(관리자 가능)")

                        )
                ));
    }




    @Test
    public void addRoutineToCategory() throws Exception {
        //GIVEN
        RoutineCategoryDto.RegisterRoutineRequest request = RoutineCategoryDto.RegisterRoutineRequest.builder()
                                                                                    .categoryId(Long.valueOf("7"))
                                                                                    .routineId(Long.valueOf("6"))
                                                                                    .build();

        //WHEN
        mockMvc.perform(post("/category/routine")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryName").exists())
                .andExpect(jsonPath("categoryId").exists())
                .andExpect(jsonPath("categoryType").exists())
                .andDo(document("add routine to category",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("routineId").description("루틴 아이디"),
                                fieldWithPath("categoryId").description("카테고리 아이디")
                        ),
                        responseFields(
                                fieldWithPath("categoryId").description("카테고리 고유번호"),
                                fieldWithPath("categoryName").description("카테고리 이름"),
                                fieldWithPath("categoryType").description("카테고리 타입 : 커스텀(일반 유저 가능), 추천(관리자 가능)")
                        )
                ));
    }

    @Test
    public void addUnExistRoutineToCategory() throws Exception {
        //GIVEN
        RoutineCategoryDto.RegisterRoutineRequest request = RoutineCategoryDto.RegisterRoutineRequest.builder()
                .categoryId(Long.valueOf("7"))
                .routineId(Long.valueOf("1300"))
                .build();

        //WHEN
        mockMvc.perform(post("/category/routine")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getFullInfoCategory() throws Exception {
        mockMvc.perform(get("/category/routines")
                .param("categoryId", "7")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryId").exists())
                .andExpect(jsonPath("categoryName").exists())
                .andExpect(jsonPath("categoryType").exists())
                .andExpect(jsonPath("routineList").exists())
                .andDo(document("get category with routines info",
                        responseFields(
                                fieldWithPath("categoryId").description("카테고리 고유번호"),
                                fieldWithPath("categoryName").description("카테고리 이름"),
                                fieldWithPath("categoryType").description("카테고리 타입 : 커스텀(일반 유저 가능), 추천(관리자 가능)"),
                                fieldWithPath("routineList[]").description("카테고리에 포함된 루틴 정보"),
                                fieldWithPath("routineList[].routineId").description("루틴 고유번호"),
                                fieldWithPath("routineList[].routineName").description("루틴 이름")
                        )
                ));
    }

}
