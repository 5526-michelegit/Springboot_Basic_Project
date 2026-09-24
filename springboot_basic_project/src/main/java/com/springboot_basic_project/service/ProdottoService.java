package com.springboot_basic_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot_basic_project.dto.ProdottoRequestDTO;
import com.springboot_basic_project.dto.ProdottoResponseDTO;
import com.springboot_basic_project.entity.Prodotto;
import com.springboot_basic_project.repository.ProdottoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;



@Service 
@RequiredArgsConstructor

public class ProdottoService {

    private final ProdottoRepository prodottoRepository; // qui non serve nemmeno creare il costruttore perchè lo fa in automatico @RequiredArgsConstructor, inoltre questa riga di codice serve per fare l'injection della repository



    // metodi di supporto

    private ProdottoResponseDTO mapToResponseDTO(Prodotto prodotto){
        ProdottoResponseDTO prodottoResponseDTO = new ProdottoResponseDTO();
        prodottoResponseDTO.setId(prodotto.getId());
        prodottoResponseDTO.setNome(prodotto.getNome());
        prodottoResponseDTO.setDescrizione(prodotto.getDescrizione());
        prodottoResponseDTO.setPrezzo(prodotto.getPrezzo());
        prodottoResponseDTO.setQuantitaDisponibile(prodotto.getQuantitaDisponibile());
        prodottoResponseDTO.setDataCreazione(prodotto.getDataCreazione());
        prodottoResponseDTO.setDataUltimaModifica(prodotto.getDataUltimaModifica());

        return prodottoResponseDTO;
    }









    //CREATE
    @Transactional //operazione atomica, o va tutto a buon fine oppure avviene un rollback
    public ProdottoResponseDTO creaProdotto (ProdottoRequestDTO requestDTO){
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(requestDTO.getNome());
        prodotto.setDescrizione(requestDTO.getDescrizione());
        prodotto.setPrezzo(requestDTO.getPrezzo());
        prodotto.setQuantitaDisponibile(requestDTO.getQuantitaDisponibile());

        Prodotto prodottoSalvato = prodottoRepository.save(prodotto);
        
        return mapToResponseDTO(prodottoSalvato);
    }




    //READ ALL
    @Transactional
    public List<ProdottoResponseDTO> cercaTutti(){

        List<Prodotto> listaProdotti = prodottoRepository.findAll();
        List<ProdottoResponseDTO> listaProdottoResponse = new ArrayList<>();

        for(Prodotto prodotto : listaProdotti){
            ProdottoResponseDTO dto = mapToResponseDTO(prodotto);
            listaProdottoResponse.add(dto);
        }

        return listaProdottoResponse;
    }



    //READ BY ID
    @Transactional 
    public ProdottoResponseDTO trovaProdottoperId(Long id){
        Prodotto prodotto = prodottoRepository.findById(id).orElseThrow(); // aggiungere anche il tipo di eccezione
        return mapToResponseDTO(prodotto);
    }



    //UPDATE
    @Transactional 
    public ProdottoResponseDTO updateProdotto(Long id, ProdottoRequestDTO requestDTO){
        Prodotto prodotto = prodottoRepository.findById(id).orElseThrow();
        prodotto.setNome(requestDTO.getNome());
        prodotto.setDescrizione(requestDTO.getDescrizione());
        prodotto.setPrezzo(requestDTO.getPrezzo());
        prodotto.setQuantitaDisponibile(requestDTO.getQuantitaDisponibile());

        Prodotto updatedProdotto = prodottoRepository.save(prodotto);
        return mapToResponseDTO(updatedProdotto);

    }




    //DELETE
    @Transactional 
    public void deleteProdotto(Long id){
        Prodotto prodotto = prodottoRepository.findById(id).orElseThrow();
        prodottoRepository.delete(prodotto);
    }




    //RICERCA PER NOME
    @Transactional 
    public List<ProdottoResponseDTO> searchByName(String nome){

        List<Prodotto> listaProdotti = prodottoRepository.findBynomeContainingIgnoreCase(nome);
        List<ProdottoResponseDTO> listaProdottoResponse = new ArrayList<>();

        for(Prodotto prodotto : listaProdotti){
            ProdottoResponseDTO dto = mapToResponseDTO(prodotto);
            listaProdottoResponse.add(dto);
        }

        return listaProdottoResponse;

    }

}
