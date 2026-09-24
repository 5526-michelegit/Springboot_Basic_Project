package com.springboot_basic_project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_basic_project.dto.ProdottoRequestDTO;
import com.springboot_basic_project.dto.ProdottoResponseDTO;
import com.springboot_basic_project.service.ProdottoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/prodotti")
@RequiredArgsConstructor 
public class Prodottocontroller {


    private final ProdottoService prodottoService;

    @PostMapping
    public ResponseEntity<ProdottoResponseDTO> creaProdotto(@Valid @RequestBody ProdottoRequestDTO requestDTO) {
        return new ResponseEntity<>(prodottoService.creaProdotto(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdottoResponseDTO>> trovaTutti() {
        return ResponseEntity.ok(prodottoService.cercaTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> trovaPerId(@PathVariable Long id) {
        return ResponseEntity.ok(prodottoService.trovaProdottoperId(id));
    }

    @GetMapping("/cerca")
    public ResponseEntity<List<ProdottoResponseDTO>> cercaPerNome(@RequestParam String nome) {
        return ResponseEntity.ok(prodottoService.searchByName(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> aggiornaProdotto(@PathVariable Long id,
                                                                  @Valid @RequestBody ProdottoRequestDTO requestDTO) {
        return ResponseEntity.ok(prodottoService.updateProdotto(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
        prodottoService.deleteProdotto(id);
        return ResponseEntity.noContent().build();
    }

}
