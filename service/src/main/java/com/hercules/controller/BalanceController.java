package com.hercules.controller;

import com.hercules.api.BalanceApi;
import com.hercules.domain.BalanceDto;
import com.hercules.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BalanceController implements BalanceApi {

    private final BalanceService balanceService;

    @Override
    public BalanceDto getCurrentBalance() {
        return balanceService.getBalance();
    }

    @Override
    public BalanceDto changeBalance(@RequestBody BalanceDto balance) {
        return balanceService.updateBalance(balance);
    }
}
