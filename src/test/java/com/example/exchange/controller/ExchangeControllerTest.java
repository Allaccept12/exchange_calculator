package com.example.exchange.controller;

import com.example.exchange.dto.CurrencyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ExchangeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void 해당_국가의_환율_Controller() throws Exception {
        String uri = "/api/exchange?exchange_country=KRW";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertThat(status).isEqualTo(200);
    }

    @Test
    public void 수취금액_Controller() throws Exception {
        String uri = "/api/receivable";
        //given
        double sendMoney = 10;
        String country = "KRW";
        //when
        String body = mapper.writeValueAsString(
                CurrencyDto.ConvertRequest.builder().send_money(sendMoney).country(country).build()
        );
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(body) //HTTP Body에 데이터를 담는다
                        .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                )
                .andExpect(status().isOk());

    }


}