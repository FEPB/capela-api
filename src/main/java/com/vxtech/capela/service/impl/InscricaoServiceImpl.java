package com.vxtech.capela.service.impl;

import com.vxtech.capela.model.Inscricao;
import com.vxtech.capela.model.Oficina;
import com.vxtech.capela.repository.InscricaoRepository;
import com.vxtech.capela.service.InscricaoService;
import com.vxtech.capela.service.OficinaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.vxtech.capela.exceptions.ExceptionEnum.*;
import static com.vxtech.capela.exceptions.ExceptionGeneric.checkThrow;

@Service
@AllArgsConstructor
public class InscricaoServiceImpl implements InscricaoService {

    private InscricaoRepository inscricaoRepository;
    private OficinaService oficinaService;

    @Override
    public List<Inscricao> listar() {
        return inscricaoRepository.findAll();
    }

    @Override
    public Inscricao cadastrar(Inscricao i) {
        if (Objects.nonNull(i.getOficina())) {
            Optional<Oficina> oficina = oficinaService.buscaPorId(i.getOficina().getId());
            checkThrow(!oficina.isPresent(), OFICINA_NAO_ENCONTRADA, i.getOficina().getId());
            i.setOficina(oficina.get());
        }
        return inscricaoRepository.save(i);
    }

    @Override
    public Inscricao buscarPorId(UUID id) {
        Optional<Inscricao> op = inscricaoRepository.findById(id);
        checkThrow(!op.isPresent(), INSCRICAO_NAO_ENCONTRADA);
        checkThrow(Objects.isNull(op.get().getPessoa()), GLOBAL_INCONSISTENCIA_CADASTRAL_ERROR);

        return op.get();
    }

    @Override
    public Inscricao atualizar(Inscricao update) {
        checkThrow(Objects.isNull(update.getId()), INSCRICAO_SEM_ID);

        Optional<Inscricao> op = inscricaoRepository.findById(update.getId());
        checkThrow(!op.isPresent(), INSCRICAO_NAO_ENCONTRADA);
        checkThrow(Objects.isNull(op.get().getPessoa()), GLOBAL_INCONSISTENCIA_CADASTRAL_ERROR);

        Inscricao i = op.get();
        i.setIsConfirmada(update.getIsConfirmada());
        i.setIsTrabalhador(update.getIsTrabalhador());
        i.setTelefone(update.getTelefone());
        i.setInstituicao(update.getInstituicao());
        i.setEmail(update.getEmail());
        i.setNomeCoordenador(update.getNomeCoordenador());
        i.setEmailCoordenador(update.getEmailCoordenador());
        i.setRestricaoAlimentar(update.getRestricaoAlimentar());

        i.getPessoa().setNome(update.getPessoa().getNome());
        i.getPessoa().setNomeCracha(update.getPessoa().getNomeCracha());
        i.getPessoa().setTipoSanguineo(update.getPessoa().getTipoSanguineo());
        i.getPessoa().setDataNascimento(update.getPessoa().getDataNascimento());
        i.getPessoa().setGenero(update.getPessoa().getGenero());

        if (Objects.nonNull(update.getOficina())) {
            Optional<Oficina> oficina = oficinaService.buscaPorId(update.getOficina().getId());
            checkThrow(!oficina.isPresent(), OFICINA_NAO_ENCONTRADA, update.getOficina().getId());
            i.setOficina(oficina.get());
        }

        return inscricaoRepository.save(i);
    }

    @Override
    public void deletar(UUID id) {
        Optional<Inscricao> inscricao = inscricaoRepository.findById(id);
        checkThrow(!inscricao.isPresent(), INSCRICAO_NAO_ENCONTRADA);
        inscricaoRepository.deleteById(id);
    }
}
