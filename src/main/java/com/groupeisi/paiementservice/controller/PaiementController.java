package com.groupeisi.paiementservice.controller;
import com.groupeisi.paiementservice.dto.PaiementDTO;
import com.groupeisi.paiementservice.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/paiements") @RequiredArgsConstructor
@Tag(name = "Paiements") @SecurityRequirement(name = "bearerAuth")
public class PaiementController {
    private final PaiementService paiementService;
    @GetMapping @Operation(summary = "Tous les paiements") public ResponseEntity<List<PaiementDTO>> findAll() { return ResponseEntity.ok(paiementService.findAll()); }
    @GetMapping("/{id}") @Operation(summary = "Paiement par ID") public ResponseEntity<PaiementDTO> findById(@PathVariable Long id) { return ResponseEntity.ok(paiementService.findById(id)); }
    @GetMapping("/etudiant/{etudiantId}") @Operation(summary = "Paiements d'un étudiant") public ResponseEntity<List<PaiementDTO>> findByEtudiant(@PathVariable Long etudiantId) { return ResponseEntity.ok(paiementService.findByEtudiant(etudiantId)); }
    @PutMapping("/{id}/payer") @Operation(summary = "Effectuer un paiement") public ResponseEntity<PaiementDTO> payer(@PathVariable Long id) { return ResponseEntity.ok(paiementService.effectuerPaiement(id)); }
}
