package com.hercules.controller;

import com.hercules.domain.BalanceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("savings")
public interface GatewayApi {

    @GetMapping("a/balance")
    BalanceDto getCurrentBalanceA();

    @GetMapping("b/balance")
    BalanceDto getCurrentBalanceB();

    @PostMapping("a/balance")
    BalanceDto changeBalanceA(BalanceDto balance);

    @PostMapping("b/balance")
    BalanceDto changeBalanceB(BalanceDto balance);


}
