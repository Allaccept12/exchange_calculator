package com.example.exchange.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.Map;

@Getter
public class CurrencyDto {

    private boolean success;
    private String source;
    private Map<String,Double> quotes;


    @Getter
    @Builder
    public static class ConvertCurrency{
        private String convert_rate;
    }

    @Getter
    @Builder
    public static class ConvertRequest {

        @Min(0)
        @Max(10000)
        private double send_money;
        private String country;
    }


}