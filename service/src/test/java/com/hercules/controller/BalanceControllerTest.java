package com.hercules.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hercules.domain.BalanceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(value = {"classpath:db/test_script.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCurrentBalance() throws Exception {
        mockMvc.perform(get("/balance"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.amount", equalTo(500.28)));
    }

    @Test
    void changeBalance() throws Exception {
        var balanceDto = new BalanceDto();
        balanceDto.setAmount(BigDecimal.valueOf(635.25));
        mockMvc.perform(post("/balance")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(balanceDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.amount", equalTo(635.25)));
    }
}
