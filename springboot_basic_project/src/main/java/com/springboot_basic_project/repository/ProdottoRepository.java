package com.springboot_basic_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.springboot_basic_project.entity.Prodotto;

//@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{

    List<Prodotto> findBynomeContainingIgnoreCase(String nome);
    List<Prodotto> findByquantitaDisponibileGreaterThan(Integer quantita);

}
