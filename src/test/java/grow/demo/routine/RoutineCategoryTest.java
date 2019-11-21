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

    public static String jwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTgiLCJpYXQiOjE1NzQwMDU3NzAsImV4cCI6MTU3NzE1OTM3MH0.SnK4EtRuIVoA0Xmf5DtH2YlvIpkjDNYMs59CH2LQIUMrG6lqSoGJ6aZEhoBlyACi4nlWDy-EU5GkTpPfGhVUBQ";
//Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MiIsImlhdCI6MTU3Mzk4MTg1MSwiZXhwIjoxNTc3MTM1NDUxfQ.d4zgGSTLEVls0CSG_xFaRtM267nTtn9OEpUBzbUqd3ITjnhgCttuTVXlszZY0tDbq3_7yEjb-DbPFGZnwNN92g
//public static String jwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5MiIsImlhdCI6MTU3Mzk4MTg1MSwiZXhwIjoxNTc3MTM1NDUxfQ.d4zgGSTLEVls0CSG_xFaRtM267nTtn9OEpUBzbUqd3ITjnhgCttuTVXlszZY0tDbq3_7yEjb-DbPFGZnwNN92g";
//public static String jwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4OCIsImlhdCI6MTU3MzkxMjIwMSwiZXhwIjoxNTc3MDY1ODAxfQ.hQqpJ7ZGM9_41vIMc18PDgt0ysmsvw0tqBqM2F0FyAEem4i1c2PlL5b7fZMKowHnVjVrwkgqkJ_6XThlX8McIQ";


    @Test
    public void addMadCowCategory() throws Exception {
        RoutineCategoryDto.RegisterRequest request = RoutineCategoryDto.RegisterRequest.builder()
                                                                        .categoryName("명준 워크아웃테스트")
                                                                        .categoryType(RoutineCategoryType.CUSTOM)
                                                                        .build();

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("authorization", jwtToken)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(request))
        );
    }





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

    @Test
    public void getRoutineCategoryByType() throws Exception {
        mockMvc.perform(get("/category/all")
                       .param("categoryType", String.valueOf(RoutineCategoryType.CUSTOM))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .header("authorization", jwtToken)
                     //   .header("authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4OCIsImlhdCI6MTU3MzkxMjIwMSwiZXhwIjoxNTc3MDY1ODAxfQ.hQqpJ7ZGM9_41vIMc18PDgt0ysmsvw0tqBqM2F0FyAEem4i1c2PlL5b7fZMKowHnVjVrwkgqkJ_6XThlX8McIQ")
                        )
                .andDo(print())
                .andDo(document("get categoryList by categoryType",
                        responseFields(
                                fieldWithPath("[].categoryId").description("카테고리 고유번호"),
                                fieldWithPath("[].categoryType").description("카테고리 타입"),
                                fieldWithPath("[].categoryName").description("카테고리 이름"),
                                fieldWithPath("[].imageUrl").description("카테고리 사진 정보")
                        )
                        ));
    }

}
