package com.groupeisi.paiementservice.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name = "paiements") @Data @NoArgsConstructor @AllArgsConstructor
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long commandeId;
    @Column(nullable = false) private Long etudiantId;
    @Column(nullable = false) private Long produitId;
    @Column(nullable = false) private String nomProduit;
    @Column(nullable = false) private Double montant;
    @Column(nullable = false) private LocalDateTime datePaiement;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Statut statut;
    public enum Statut { EN_ATTENTE, PAYE, ECHEC }
    @PrePersist public void prePersist() {
        this.datePaiement = LocalDateTime.now();
        if (this.statut == null) this.statut = Statut.EN_ATTENTE;
        if (this.montant == null) this.montant = 50000.0;
    }
}
