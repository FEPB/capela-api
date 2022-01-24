package com.vxtech.capela.service.impl;

import com.vxtech.capela.model.Inscricao;
import com.vxtech.capela.repository.InscricaoRepository;
import com.vxtech.capela.service.InscricaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InscricaoServiceImpl implements InscricaoService {

    private InscricaoRepository inscricaoRepository;

    @Override
    public List<Inscricao> listar() {
        return inscricaoRepository.findAll();
    }

    @Override
    public Inscricao cadastrar(Inscricao i) {
        return inscricaoRepository.save(i);
    }
}
