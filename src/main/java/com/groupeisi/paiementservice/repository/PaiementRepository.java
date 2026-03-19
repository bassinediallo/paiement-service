package com.groupeisi.paiementservice.repository;
import com.groupeisi.paiementservice.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByEtudiantId(Long etudiantId);
    // Vérifier si une commande existe avant d'ajouter un paiement (logique du prof)
    Optional<Paiement> findByCommandeId(Long commandeId);
    boolean existsByCommandeId(Long commandeId);
}
