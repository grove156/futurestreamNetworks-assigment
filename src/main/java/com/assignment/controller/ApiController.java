package com.assignment.controller;

import com.assignment.model.AdStatistic;
import com.assignment.model.dto.AdStatisticResponse;
import com.assignment.model.dto.AdStatisticResponseInterface;
import com.assignment.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URL;
import java.time.LocalDate;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    /*
    * 지정 날짜 광고 통계 조회 interface
    * */
    @GetMapping("/api/statistic/{date}")
    public AdStatisticResponseInterface getStatisticByDate(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @PathVariable("date") LocalDate date) throws Exception{

        return apiService.getStatisticByDate(date);
    }

    /*
     * 특정 날짜&시간 으로 광고 통계 조회 interface
     * */
    @GetMapping("/api/statistic/{date}/{time}")
    public AdStatisticResponse getStatisticByDateAndTime(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @PathVariable("date") LocalDate date,
            @PathVariable("time") @Valid int time) throws Exception{
        return apiService.getStatisticByDateAndTime(date, time);
    }

    /*
     * 광고 통계 업로드 interface
     * */
    @PostMapping("/api/statistic")
    public ResponseEntity postStatistic(@RequestBody @Valid AdStatistic adStatistic) throws Exception{

        apiService.updateOrCreate(adStatistic);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
