package com.example.exchange.service;

import com.example.exchange.dto.CurrencyDto;

public interface CurrencyService {

    CurrencyDto getReceivable(String country);
}
