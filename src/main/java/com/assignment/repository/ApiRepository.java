package com.assignment.repository;

import com.assignment.model.AdStatistic;
import com.assignment.model.dto.AdStatisticResponse;
import com.assignment.model.dto.AdStatisticResponseInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApiRepository extends JpaRepository<AdStatistic, Long> {

    @Query(value =
            "SELECT "+
                "SUM(ad.click) AS click, "+
                "SUM(ad.request) AS request, "+
                "SUM(ad.response) AS response "+
            "FROM AD_STATISTIC ad "+
            "WHERE ad.date=?1 "+
            "GROUP BY ad.date; "
            ,nativeQuery = true)
    AdStatisticResponseInterface getSumGroupByDate(LocalDate date);

    AdStatistic findByDateAndTime(LocalDate date, Integer time);

}
