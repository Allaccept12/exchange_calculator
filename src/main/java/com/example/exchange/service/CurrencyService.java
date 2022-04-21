package com.example.exchange.service;

import com.example.exchange.dto.CurrencyDto;
import com.example.exchange.dto.CurrencyDto.ConvertCurrency;
import com.example.exchange.dto.CurrencyDto.ConvertRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyService {

    private final DecimalFormat df = new DecimalFormat("#,##0.00");
    private final RestTemplate restTemplate;
    private final String DEFAULT_COUNTRY = "USD";
    private CurrencyDto forObject;
    private LocalDateTime requestTime;

    public CurrencyService(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    //환율정보
    public ConvertCurrency getExchangeRate(String country) {
        Double currentExchangeRate = getExchange(country);
        return ConvertCurrency.builder().convert_rate(df.format(currentExchangeRate)).build();
    }

    //수취금액
    public ConvertCurrency getConvertRate(ConvertRequest dto) {
        Double toExchangeRate = getExchange(dto.getCountry());
        double outcome = toExchangeRate * dto.getSend_money();
        return ConvertCurrency.builder().convert_rate(df.format(outcome)).build();
    }


    private Double getExchange(String country) {
        CurrencyDto allExchangeRate = getAllExchangeRate();
        return allExchangeRate.getQuotes().get(DEFAULT_COUNTRY + country);
    }

    private CurrencyDto getAllExchangeRate() {
        String defaultUrl = "http://api.currencylayer.com/live?access_key=b3aaa238fa0c923f4431951ea9df31f0&source=USD";
        if (timeCheck()) {
            requestTime = LocalDateTime.now();
            forObject = restTemplate.getForObject(defaultUrl, CurrencyDto.class);
        }
        return forObject;
    }

    //무료 버전의 API 사용중이므로 텀을 주고 새 값을 가져와야함. 텀이 너무 짧으면(약 2초이후) API 호출안함
    private boolean timeCheck(){
        if(forObject == null){
            return true;
        }
        Timestamp requestTimeStamp = Timestamp.valueOf(requestTime);
        Timestamp currentTimeStamp = Timestamp.valueOf(LocalDateTime.now());
        return (currentTimeStamp.getTime() - requestTimeStamp.getTime()) / 3600000 > 0;

    }

}