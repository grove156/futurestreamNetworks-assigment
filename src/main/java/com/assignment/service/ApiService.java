package com.assignment.service;

import com.assignment.controller.exceptionAdvisor.exceptions.DataNotFoundException;
import com.assignment.model.enums.ErrorCodes;
import com.assignment.model.AdStatistic;
import com.assignment.model.dto.AdStatisticResponse;
import com.assignment.model.dto.AdStatisticResponseInterface;
import com.assignment.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    ApiRepository apiRepository;

    /*
     * 지정일 광고 통계 조회 service
     * */
    public AdStatisticResponseInterface getStatisticByDate(LocalDate date){
        AdStatisticResponseInterface adStatisticResponseInterface = Optional.ofNullable(apiRepository.getSumGroupByDate(date))
                .orElseThrow(()->new DataNotFoundException(ErrorCodes.valueOf("DataNotFoundException").getMessage()));


        return adStatisticResponseInterface;
    }

    /*
     * 지정일 & 지정 시간 광고 통계 조회 service
     * */
    public AdStatisticResponse getStatisticByDateAndTime(LocalDate date, int time){
        AdStatistic adStatistic = Optional.ofNullable(apiRepository.findByDateAndTime(date, time))
                .orElseThrow(()->new DataNotFoundException(ErrorCodes.valueOf("DataNotFoundException").getMessage()));

        AdStatisticResponse adStatisticResponse = AdStatisticResponse.builder()
                .click(adStatistic.getClick())
                .request(adStatistic.getRequest())
                .response(adStatistic.getResponse())
                .build();

        return adStatisticResponse;
    }

    /*
     * 광고 통계 업로드 Service
     * */
    @Transactional
    public void updateOrCreate(AdStatistic adStatistic) {
       AdStatistic foundAdStatistic = apiRepository.findByDateAndTime(adStatistic.getDate(),adStatistic.getTime());

        if(foundAdStatistic == null){
            apiRepository.save(adStatistic);
        }else{
            foundAdStatistic.setClick(adStatistic.getClick());
            foundAdStatistic.setRequest(adStatistic.getRequest());
            foundAdStatistic.setResponse(adStatistic.getResponse());

            apiRepository.save(foundAdStatistic);
        }
    }
}
