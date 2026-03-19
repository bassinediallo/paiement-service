package com.groupeisi.paiementservice.service;
import com.groupeisi.paiementservice.dto.PaiementDTO;
import com.groupeisi.paiementservice.entities.Paiement;
import com.groupeisi.paiementservice.exception.ResourceNotFoundException;
import com.groupeisi.paiementservice.mapper.PaiementMapper;
import com.groupeisi.paiementservice.repository.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final PaiementMapper paiementMapper;
    @Cacheable("paiements") public List<PaiementDTO> findAll() { return paiementRepository.findAll().stream().map(paiementMapper::toDTO).collect(Collectors.toList()); }
    public PaiementDTO findById(Long id) { return paiementMapper.toDTO(paiementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé: " + id))); }
    public List<PaiementDTO> findByEtudiant(Long etudiantId) { return paiementRepository.findByEtudiantId(etudiantId).stream().map(paiementMapper::toDTO).collect(Collectors.toList()); }
    @CacheEvict(value="paiements", allEntries=true)
    public PaiementDTO effectuerPaiement(Long id) {
        Paiement p = paiementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paiement non trouvé: " + id));
        if (p.getStatut() == Paiement.Statut.PAYE) throw new IllegalArgumentException("Paiement déjà effectué");
        p.setStatut(Paiement.Statut.PAYE);
        return paiementMapper.toDTO(paiementRepository.save(p));
    }
}
