package com.back.backaio.Repository;

import com.back.backaio.Models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionesRepository  extends JpaRepository<Clientes,Integer> {
}
