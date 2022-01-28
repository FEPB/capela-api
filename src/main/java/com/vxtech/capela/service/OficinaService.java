package com.vxtech.capela.service;

import com.vxtech.capela.model.Oficina;

import java.util.Optional;
import java.util.UUID;

public interface OficinaService {

    Optional<Oficina> buscaPorId(UUID id);

}
