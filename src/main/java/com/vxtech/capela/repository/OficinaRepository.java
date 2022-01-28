package com.vxtech.capela.repository;

import com.vxtech.capela.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, UUID> {
}
