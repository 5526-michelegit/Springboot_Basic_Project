package com.springboot_basic_project.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 

public class ProdottoResponseDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private BigDecimal prezzo;
    private Integer quantitaDisponibile;
    private LocalDateTime dataCreazione;
    private LocalDateTime dataUltimaModifica;
}
