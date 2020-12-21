package com.assignment.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AdStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Min(value = 0, message = "it has to be 0 or bigger")
    @Max(value = 24, message = "it has to be 24 or smaller")
    private Integer time;

    @Min(value = 0, message = "it has to be 0 or bigger")
    private Long click;

    @Min(value = 0, message = "it has to be 0 or bigger")
    private Long request;

    @Min(value = 0, message = "it has to be 0 or bigger")
    private Long response;
}
