package com.arpanet.code.repository;

import com.arpanet.code.model.VentaEntitie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface VentaRepositorio extends JpaRepository<VentaEntitie, Long> {

    @Query(value = " select * from ventas ", nativeQuery = true)
        List<Object[]> buscar();

}
