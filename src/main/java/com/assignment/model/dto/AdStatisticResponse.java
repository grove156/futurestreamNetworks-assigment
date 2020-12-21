package com.assignment.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
* 지정일 & 지정 시간 조회 dto
* */
public class AdStatisticResponse implements AdStatisticResponseInterface {

    private Long click;

    private Long request;

    private Long response;
}
