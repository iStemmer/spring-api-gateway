package com.hercules.client;

import com.hercules.api.BalanceApi;
import com.hercules.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name="balance-client-a",
        url="${rest.balance-a.client}",
        configuration = FeignConfiguration.class)
public interface BalanceClientA extends BalanceApi {
}
