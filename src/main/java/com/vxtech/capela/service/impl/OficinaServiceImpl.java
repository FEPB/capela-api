package com.vxtech.capela.service.impl;

import com.vxtech.capela.model.Oficina;
import com.vxtech.capela.repository.OficinaRepository;
import com.vxtech.capela.service.OficinaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OficinaServiceImpl implements OficinaService {

    private OficinaRepository oficinaRepository;

    @Override
    public Optional<Oficina> buscaPorId(UUID id) {
        return oficinaRepository.findById(id);
    }

}
