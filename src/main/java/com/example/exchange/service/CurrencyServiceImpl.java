package com.example.exchange.service;

import com.example.exchange.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final RestTemplate restTemplate;

    public CurrencyServiceImpl(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public CurrencyDto getReceivable(String country) {
        String defaultUrl = "http://api.currencylayer.com/live?access_key=b3aaa238fa0c923f4431951ea9df31f0&source=USD";
        return restTemplate.getForObject(defaultUrl + "&currencies=" + country,CurrencyDto.class);
    }
}
