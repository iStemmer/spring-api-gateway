package com.hercules.service;

import com.hercules.domain.BalanceDto;

public interface GatewayService {

    BalanceDto getCurrentBalanceA();

    BalanceDto getCurrentBalanceB();

    BalanceDto changeBalanceA(BalanceDto balance);

    BalanceDto changeBalanceB(BalanceDto balance);

}
