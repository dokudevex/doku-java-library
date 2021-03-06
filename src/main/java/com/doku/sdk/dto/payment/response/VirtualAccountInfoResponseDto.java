package com.doku.sdk.dto.payment.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Setter
@Getter
public class VirtualAccountInfoResponseDto {
    String virtualAccountNumber;
    String howToPayPage;
    String howToPayApi;
    String createdDate;
    String expiredDate;
}