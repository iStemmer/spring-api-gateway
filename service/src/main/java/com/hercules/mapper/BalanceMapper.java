package com.hercules.mapper;

import com.hercules.domain.BalanceDto;
import com.hercules.domain.db.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface BalanceMapper {

    BalanceDto fromEntity(Balance balance);

    Balance updateFromDto(BalanceDto balanceDto, @MappingTarget Balance balance);

}
