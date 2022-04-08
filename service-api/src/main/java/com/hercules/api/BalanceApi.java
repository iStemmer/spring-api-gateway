package com.hercules.api;

import com.hercules.domain.BalanceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface BalanceApi {

    @GetMapping("balance")
    BalanceDto getCurrentBalance();


    @PostMapping("balance")
    BalanceDto changeBalance(BalanceDto balance);

}
