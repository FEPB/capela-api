package com.vxtech.capela.web.rest;

import com.vxtech.capela.model.Inscricao;
import com.vxtech.capela.service.InscricaoService;
import com.vxtech.capela.web.rest.dto.InscricaoDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
@AllArgsConstructor
public class InscricaoResource {

    @Autowired
    private ModelMapper modelMapper;

    private InscricaoService inscricaoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Inscricao> inscricoes = inscricaoService.listar();
        return new ResponseEntity<List<Inscricao>>(inscricoes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody InscricaoDTO inscricaoDTO) {
        Inscricao i = modelMapper.map(inscricaoDTO, Inscricao.class);
        Inscricao persisted = inscricaoService.cadastrar(i);
        return new ResponseEntity<InscricaoDTO>(modelMapper.map(persisted, InscricaoDTO.class), HttpStatus.OK);
    }

}
