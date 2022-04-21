package com.example.exchange.service;

import com.example.exchange.dto.CurrencyDto.ConvertCurrency;
import com.example.exchange.dto.CurrencyDto.ConvertRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CurrencyServiceImplTest {

    @Autowired
    CurrencyService currencyService;

    @Test
    public void 해당_국가_환율정보() throws Exception {
        //given
        ConvertCurrency currency = currencyService.getExchangeRate("KRW");
        //when - then
        assertThat(currency).isNotNull();
    }

    @Test
    public void 수취_금액_확인() throws Exception {
        //given
        ConvertRequest krwDto = ConvertRequest.builder().country("KRW").send_money(1).build();
        ConvertCurrency convertRate = currencyService.getConvertRate(krwDto);
        ConvertCurrency exchangeRate = currencyService.getExchangeRate("KRW");
        //when
        String usdkrw = exchangeRate.getConvert_rate();
        String convertOutcome = convertRate.getConvert_rate();
        // then
        assertThat(usdkrw).isEqualTo(convertOutcome);

    }


}