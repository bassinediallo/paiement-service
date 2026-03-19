package com.groupeisi.paiementservice.kafka;
import com.groupeisi.paiementservice.entities.Paiement;
import com.groupeisi.paiementservice.repository.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Consommateur Kafka — logique exacte du prof :
 * "paiement récupère les informations envoyées via Kafka
 *  et à chaque fois qu'il doit ajouter un paiement,
 *  il va dans sa base vérifier si déjà une commande dédiée existe"
 */
@Service @RequiredArgsConstructor
public class CommandeConsumer {
    private static final Logger log = LoggerFactory.getLogger(CommandeConsumer.class);
    private final PaiementRepository paiementRepository;

    @KafkaListener(topics = "commande-topic", groupId = "paiement-group")
    public void consommer(CommandeEvent event) {
        log.info("📥 Message Kafka reçu — commandeId: {}, produit: {}, etudiantId: {}",
            event.getCommandeId(), event.getNomProduit(), event.getEtudiantId());

        // Vérifier dans la base si une commande dédiée existe déjà
        if (paiementRepository.existsByCommandeId(event.getCommandeId())) {
            log.warn("⚠️ Un paiement existe déjà pour commandeId: {}", event.getCommandeId());
            return;
        }

        // Créer le paiement en attente
        Paiement paiement = new Paiement();
        paiement.setCommandeId(event.getCommandeId());
        paiement.setEtudiantId(event.getEtudiantId());
        paiement.setProduitId(event.getProduitId());
        paiement.setNomProduit(event.getNomProduit());
        paiement.setStatut(Paiement.Statut.EN_ATTENTE);
        paiementRepository.save(paiement);

        log.info("✅ Paiement créé en attente pour commandeId: {}", event.getCommandeId());
    }
}
