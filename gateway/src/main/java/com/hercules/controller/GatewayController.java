package com.hercules.controller;

import com.hercules.domain.BalanceDto;
import com.hercules.service.GatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GatewayController implements GatewayApi {

    private final GatewayService gatewayService;


    @Override
    public BalanceDto getCurrentBalanceA() {
        return gatewayService.getCurrentBalanceA();
    }

    @Override
    public BalanceDto getCurrentBalanceB() {
        return gatewayService.getCurrentBalanceB();
    }

    @Override
    public BalanceDto changeBalanceA(BalanceDto balance) {
        return gatewayService.changeBalanceA(balance);
    }

    @Override
    public BalanceDto changeBalanceB(BalanceDto balance) {
        return gatewayService.changeBalanceB(balance);
    }
}
