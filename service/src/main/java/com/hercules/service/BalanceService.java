package com.hercules.service;

import com.hercules.domain.BalanceDto;

public interface BalanceService {

    BalanceDto getBalance();

    BalanceDto updateBalance(BalanceDto balanceDto);
}
