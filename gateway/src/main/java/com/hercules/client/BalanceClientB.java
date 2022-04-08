package com.hercules.client;

import com.hercules.api.BalanceApi;
import com.hercules.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name="balance-client-b",
        url="${rest.balance-b.client}",
        configuration = FeignConfiguration.class)
public interface BalanceClientB extends BalanceApi {
}
