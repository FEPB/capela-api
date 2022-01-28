package com.vxtech.capela.web.rest;

import com.vxtech.capela.model.Inscricao;
import com.vxtech.capela.service.InscricaoService;
import com.vxtech.capela.web.rest.dto.InscricaoDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inscricoes")
@AllArgsConstructor
public class InscricaoResource {

    private ModelMapper modelMapper;

    private InscricaoService inscricaoService;

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO> buscarPorId(@PathVariable("id") UUID id) {
        InscricaoDTO dto = modelMapper.map(inscricaoService.buscarPorId(id), InscricaoDTO.class);
        return new ResponseEntity<InscricaoDTO>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Inscricao> inscricoes = inscricaoService.listar();
        return new ResponseEntity<List<Inscricao>>(inscricoes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid InscricaoDTO inscricaoDTO) {
        Inscricao i = modelMapper.map(inscricaoDTO, Inscricao.class);
        Inscricao persisted = inscricaoService.cadastrar(i);
        return new ResponseEntity<InscricaoDTO>(modelMapper.map(persisted, InscricaoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> atualizar(@RequestBody @Valid InscricaoDTO inscricaoDTO) {

        Inscricao i = modelMapper.map(inscricaoDTO, Inscricao.class);
        Inscricao persisted = inscricaoService.atualizar(i);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@RequestBody InscricaoDTO inscricaoDTO) {
        inscricaoService.deletar(inscricaoDTO.getId());
    }

}
