package com.openmarket.bct.service.mapper;

import com.openmarket.bct.domain.*;
import com.openmarket.bct.service.dto.FeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fee and its DTO FeeDTO.
 */
@Mapper(componentModel = "spring", uses = {InvoiceMapper.class})
public interface FeeMapper extends EntityMapper<FeeDTO, Fee> {

    @Mapping(source = "invoice.id", target = "invoiceId")
    FeeDTO toDto(Fee fee);

    @Mapping(source = "invoiceId", target = "invoice")
    Fee toEntity(FeeDTO feeDTO);

    default Fee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fee fee = new Fee();
        fee.setId(id);
        return fee;
    }
}
