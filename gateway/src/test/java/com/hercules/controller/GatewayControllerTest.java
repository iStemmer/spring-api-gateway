package com.hercules.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.hercules.domain.BalanceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWireMock(port = 0)
class GatewayControllerTest {

    private static final String BASE_URL = "/savings/";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void getCurrentBalanceA() throws Exception {
        BalanceDto balance = new BalanceDto();
        balance.setAmount(BigDecimal.valueOf(500.34));
        stubFor(WireMock.get(urlEqualTo("/balance"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(objectMapper.writeValueAsString(balance)))
        );

        mvc.perform(get(BASE_URL + "a/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", equalTo(500.34)));

    }

    @Test
    void getCurrentBalanceAWithTimeout() throws Exception {
        BalanceDto balance = new BalanceDto();
        balance.setAmount(BigDecimal.valueOf(500.34));
        stubFor(WireMock.get(urlEqualTo("/balance"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withFixedDelay(600)
                        .withBody(objectMapper.writeValueAsString(balance)))
        );

        mvc.perform(get(BASE_URL + "a/balance"))
                .andExpect(status().is(HttpStatus.GATEWAY_TIMEOUT.value()));

    }


    @Test
    void getCurrentBalanceB() throws Exception {
        BalanceDto balance = new BalanceDto();
        balance.setAmount(BigDecimal.valueOf(500.35));
        stubFor(WireMock.get(urlEqualTo("/balance"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(objectMapper.writeValueAsString(balance)))
        );

        mvc.perform(get(BASE_URL + "a/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", equalTo(500.35)));

    }

    @Test
    void changeBalanceA() throws Exception {
        BalanceDto balance = new BalanceDto();
        balance.setAmount(BigDecimal.valueOf(333.33));
        stubFor(WireMock.post(urlEqualTo("/balance"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(objectMapper.writeValueAsString(balance)))
        );
        mvc.perform(post(BASE_URL + "a/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(balance))
                    )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", equalTo(333.33)));
    }

    @Test
    void changeBalanceB() throws Exception {
        BalanceDto balance = new BalanceDto();
        balance.setAmount(BigDecimal.valueOf(333.33));
        stubFor(WireMock.post(urlEqualTo("/balance"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(objectMapper.writeValueAsString(balance)))
        );
        mvc.perform(post(BASE_URL + "a/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(balance))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", equalTo(333.33)));
    }

    @Test
    void changeBalanceBWithTimeout() throws Exception {
        BalanceDto balance = new BalanceDto();
        balance.setAmount(BigDecimal.valueOf(444.44));
        stubFor(WireMock.post(urlEqualTo("/balance"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withFixedDelay(600)
                        .withBody(objectMapper.writeValueAsString(balance)))
        );
        mvc.perform(post(BASE_URL + "a/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(balance))
                )
                .andExpect(status().is(HttpStatus.GATEWAY_TIMEOUT.value()));
    }




}
