package com.assignment.controller;

import com.assignment.model.dto.AdStatisticResponse;
import com.assignment.service.ApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
class ApiControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ApiService apiService;

    /*
    * 지정일 광고 통계 컨트롤러 테스트
    * */
    @Test
    public void getStatisticByDateWithValidParams() throws Exception {
        AdStatisticResponse result = new AdStatisticResponse().builder()
                .response(10L)
                .request(10L)
                .click(10L)
                .build();

        String date = LocalDate.of(2020,11,1).toString();

        given(apiService.getStatisticByDate(LocalDate.of(2020,11,1))).willReturn(result);

        mvc.perform(get("/api/statistic/"+date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\"request\": 10,\n" +
                        "\"response\": 10,\n" +
                        "\"click\": 10\n" +
                        "}"));
    }

    @Test
    public void getStatisticByDataWithInvalidDateFormat1() throws Exception {
        String date = "2020-13-03";
        mvc.perform(get("/api/statistic/"+date))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(2))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    public void getStatisticByDataWithInvalidDateFormat2() throws Exception {
        String date= "2020-Nov-01";
        mvc.perform(get("/api/statistic/"+date))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(2))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    public void getStatisticByDataWithInvalidDateFormat3() throws Exception {
        int date= 20201101;
        mvc.perform(get("/api/statistic/"+date))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(2))
                .andExpect(jsonPath("$.status").value(400));
    }

    /*
     * 지정일&지정시간 광고 통계 컨트롤러 테스트
     * */
    @Test
    public void getStatisticByDateAndTimeWithValidParams() throws Exception {

        String date = LocalDate.of(2020,11,1).toString();
        Integer time = 22;

        AdStatisticResponse result = new AdStatisticResponse().builder()
                .response(10L)
                .request(10L)
                .click(10L)
                .build();

        given(apiService.getStatisticByDateAndTime(LocalDate.of(2020,11,1),time))
                .willReturn(result);

        mvc.perform(get("/api/statistic/"+date+"/"+time))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                        "\"request\": 10,\n" +
                        "\"response\": 10,\n" +
                        "\"click\": 10\n" +
                        "}"));
    }

    /*
     * 광고 통계 업로드 컨트롤러 테스트
     * */
    @Test
    public void createOrUpdateStatistic() throws Exception {
        mvc.perform(post("/api/statistic")
                .contentType(MediaType.APPLICATION_JSON)
                .content("" +
                        "{\n" +
                        "  \"date\": \"2020-11-01\",\n" +
                        "  \"time\":22,\n" +
                        "  \"click\":32,\n" +
                        "  \"request\":22,\n" +
                        "  \"response\":22\n" +
                        "}" +
                        ""))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}