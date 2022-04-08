package com.hercules.service;

import com.hercules.client.BalanceClientA;
import com.hercules.client.BalanceClientB;
import com.hercules.domain.BalanceDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GatewayServiceFeign implements GatewayService {

    private final BalanceClientA balanceClientA;
    private final BalanceClientB balanceClientB;

    @Override
    public BalanceDto getCurrentBalanceA() {
        return balanceClientA.getCurrentBalance();
    }

    @Override
    public BalanceDto getCurrentBalanceB() {
        return balanceClientB.getCurrentBalance();
    }

    @Override
    public BalanceDto changeBalanceA(BalanceDto balance) {
        return balanceClientA.changeBalance(balance);
    }

    @Override
    public BalanceDto changeBalanceB(BalanceDto balance) {
        return balanceClientB.changeBalance(balance);
    }
}
