package com.assignment.service;

import com.assignment.controller.exceptionAdvisor.exceptions.DataNotFoundException;
import com.assignment.model.AdStatistic;
import com.assignment.model.dto.AdStatisticResponseInterface;
import com.assignment.repository.ApiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.assignment.controller.exceptionAdvisor.exceptions.DataNotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiServiceTests {

    @Autowired
    private ApiService apiService;

    @Autowired
    private ApiRepository apiRepository;

    private final LocalDate date = LocalDate.of(2020,12,1);
    private final Integer time = 10;

    @BeforeEach
    private void setup(){
        AdStatistic adStatistic = new AdStatistic().builder()
                .id(1L)
                .date(date)
                .time(time)
                .click(100L)
                .request(100L)
                .response(100L)
                .build();
        apiService.updateOrCreate(adStatistic);
    }

    /*
     * 지정일 광고 통계 service 테스트
     * */
    @Test
    public void getStatisticByDateWithSuccess(){
        AdStatisticResponseInterface result = apiService.getStatisticByDate(date);
        assertEquals(100, result.getClick());
    }

    @Test
    public void getStatisticByDateWithNullReturn(){
        LocalDate date = LocalDate.of(2020,12,11);

        assertThrows(DataNotFoundException.class, ()->{
            apiService.getStatisticByDate(date);
        });
    }

    /*
     * 지정일&지정 시간 광고 통계 service 테스트
     * */
    @Test
    public void getStatisticByDateAndTimeWithSucess(){
        AdStatisticResponseInterface result = apiService.getStatisticByDateAndTime(date, time);
        assertEquals(100, result.getClick());
    }

    @Test
    public void getStatisticByDateAndTimeWithNullReturn(){
        LocalDate date = LocalDate.of(2020,12,11);

        assertThrows(DataNotFoundException.class, ()->{
            apiService.getStatisticByDateAndTime(date, time);
        });
    }

    /*
     * 광고 통계 업로드 service 테스트
     * */
    @Test
    public void updateOrCreateWithSuccess(){
        LocalDate date = LocalDate.of(2020,12,30);
        Integer time = 10;

        AdStatistic adStatistic = new AdStatistic().builder()
                .id(1L)
                .date(date)
                .time(time)
                .click(1000L)
                .request(1000L)
                .response(1000L)
                .build();
        apiService.updateOrCreate(adStatistic);

        AdStatisticResponseInterface result = apiService.getStatisticByDateAndTime(date, time);
        assertEquals(1000, result.getClick());
    }
}