package com.vxtech.capela.service;

import com.vxtech.capela.model.Inscricao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscricaoService {

    List<Inscricao> listar();

    Inscricao cadastrar(Inscricao i);

    Inscricao buscarPorId(UUID id);

    Inscricao atualizar(Inscricao i);

    void deletar(UUID id);

}
