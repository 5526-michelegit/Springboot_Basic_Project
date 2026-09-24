package com.springboot_basic_project.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor 
@AllArgsConstructor 

//possiamo avere solo una public class per ogni class java, tutte le altre classi devono essere denominate come class e basta
public class Prodotto {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 2000)
    private String descrizione;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "Il prezzo non può essere negativo")
    @Column(nullable = false)
    private BigDecimal prezzo;


    @NotNull(message = "La quantità disponibile è obbligatoria")
    @Column(nullable = false)
    private Integer quantitaDisponibile;


    @Column(updatable = false)
    private LocalDateTime dataCreazione;
    private LocalDateTime dataUltimaModifica;


    @PrePersist
    protected void onCreate(){
        dataCreazione = LocalDateTime.now();
        dataUltimaModifica = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        dataUltimaModifica = LocalDateTime.now();
    }


    // non serve creare anche hashmap/equals perchè questi sono già presenti dentro @Data
    // non serve generare nemmeno i costruttori perchè questi sono presenti dentro @NoArgsConstructor @AllArgsConstructor 
    

}
