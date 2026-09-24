package com.springboot_basic_project.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdottoRequestDTO {

    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    private String nome;

    private String descrizione;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "Il prezzo non può essere negativo")
    private BigDecimal prezzo;

    @NotNull(message = "La quantità disponibile è obbligatoria")
    private Integer quantitaDisponibile;

}
