package com.example.exchange.controller;


import com.example.exchange.dto.CurrencyDto.ConvertCurrency;
import com.example.exchange.dto.CurrencyDto.ConvertRequest;
import com.example.exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final CurrencyService currencyService;

    @GetMapping("/api/exchange")
    public ResponseEntity<ConvertCurrency> getExchangeRate(@RequestParam("exchange_country") String country) {
        return new ResponseEntity<>(currencyService.getExchangeRate(country), HttpStatus.OK);
    }

    @PostMapping("/api/receivable")
    public ResponseEntity<ConvertCurrency> getReceivable(@Valid @RequestBody ConvertRequest dto) {
        return new ResponseEntity<>(currencyService.getConvertRate(dto),HttpStatus.OK);
    }


}