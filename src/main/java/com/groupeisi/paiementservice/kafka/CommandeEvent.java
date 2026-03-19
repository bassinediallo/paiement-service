package com.groupeisi.paiementservice.kafka;
import lombok.*;
import java.time.LocalDateTime;
@Data @NoArgsConstructor @AllArgsConstructor
public class CommandeEvent {
    private Long commandeId;
    private Long etudiantId;
    private Long produitId;
    private String nomProduit;
    private Double quantite;
    private LocalDateTime dateCommande;
    private String statut;
}
