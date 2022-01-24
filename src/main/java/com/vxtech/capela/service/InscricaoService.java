package com.vxtech.capela.service;

import com.vxtech.capela.model.Inscricao;

import java.util.List;

public interface InscricaoService {

    List<Inscricao> listar();

    Inscricao cadastrar(Inscricao i);

}
