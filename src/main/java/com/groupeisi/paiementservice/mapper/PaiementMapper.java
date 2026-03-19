package com.groupeisi.paiementservice.mapper;
import com.groupeisi.paiementservice.dto.PaiementDTO;
import com.groupeisi.paiementservice.entities.Paiement;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface PaiementMapper {
    PaiementDTO toDTO(Paiement p);
    Paiement toEntity(PaiementDTO dto);
}
