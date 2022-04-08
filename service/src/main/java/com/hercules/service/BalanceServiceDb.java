package com.hercules.service;

import com.hercules.domain.BalanceDto;
import com.hercules.mapper.BalanceMapper;
import com.hercules.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BalanceServiceDb implements BalanceService {

    private static final String BALANCE_NOT_FOUND = "Balance with %s doesn't exist.";
    private static final Long ACCOUNT_ID = 1L;

    private final BalanceRepository balanceRepository;
    private final BalanceMapper mapper;

    @Override
    public BalanceDto getBalance() {
        return balanceRepository.findById(ACCOUNT_ID)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BALANCE_NOT_FOUND, ACCOUNT_ID)));
    }

    @Override
    public BalanceDto updateBalance(BalanceDto balanceDto) {
        var balance = balanceRepository.findById(ACCOUNT_ID)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(BALANCE_NOT_FOUND, ACCOUNT_ID))
                );
        balance = mapper.updateFromDto(balanceDto, balance);
        return mapper.fromEntity(balanceRepository.save(balance));
    }
}
