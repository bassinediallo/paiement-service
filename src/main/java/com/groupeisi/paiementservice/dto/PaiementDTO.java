package com.groupeisi.paiementservice.dto;
import lombok.*;
import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor
public class PaiementDTO {
    private Long id;
    private Long commandeId;
    private Long etudiantId;
    private Long produitId;
    private String nomProduit;
    private Double montant;
    private LocalDateTime datePaiement;
    private String statut;
}
