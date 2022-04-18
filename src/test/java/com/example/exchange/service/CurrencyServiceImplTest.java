package com.example.exchange.service;

import com.example.exchange.dto.CurrencyDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class CurrencyServiceImplTest {

    @Autowired
    CurrencyServiceImpl currencyService;

    @Test
    public void 해당_국가_환율정보() throws Exception {
        //given
        CurrencyDto currency = currencyService.getReceivable("KRW");
        //when - then
        Assertions.assertThat(currency).isNotNull();
    }

}