package com.vxtech.capela;

import com.vxtech.capela.enums.SexoEnum;
import com.vxtech.capela.model.Inscricao;
import com.vxtech.capela.model.Pessoa;
import com.vxtech.capela.service.InscricaoService;
import com.vxtech.capela.tenant.TenantContext;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)
@Log4j2
class CapelaApiApplicationTests {

	private static final String TENANT = "client";

	@Autowired
	private InscricaoService inscricaoService;

	@BeforeEach
	public void beforeEach() {
		TenantContext.setCurrentTenant(TENANT);
	}

	@Test
	@Order(1)
	void cadastrarInscricao() {
		Pessoa p = new Pessoa()
				.toBuilder()
				.nome("Victor Ximenis")
				.dataNascimento(LocalDate.of(1994, 3, 31))
				.genero(SexoEnum.MASCULINO)
				.build();

		Inscricao i = new Inscricao().toBuilder().pessoa(p).build();

		Inscricao persisted = inscricaoService.cadastrar(i);

		assertTrue(Objects.nonNull(persisted));
		assertNotNull(persisted.getId());
		assertNotNull(persisted.getPessoa());
	}

	@Test
	@Order(2)
	void listarInscricoes() {
		List<Inscricao> lista = inscricaoService.listar();

		assertNotNull(lista);
		assertTrue(lista.size() > 0);
	}

}
