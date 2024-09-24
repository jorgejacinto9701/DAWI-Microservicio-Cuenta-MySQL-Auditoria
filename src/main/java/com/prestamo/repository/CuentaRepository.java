package com.prestamo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
	
	
	@Query("select c from Cuenta c where c.numero = ?1")
	public abstract List<Cuenta> listaCuentaPorNumeroIgual(String numero);
	
	@Query("select c from Cuenta c where c.numero like ?1")
	public abstract List<Cuenta> listaCuentaPorNumeroLike(String numero);
	
	
	@Query("select c from Cuenta c where c.numero = ?1 and c.idCuenta != ?2")
	public abstract List<Cuenta> validanumerodecuentaActualiza(String numero, int idCuenta);

	Cuenta findByNumero(String numero);
	
	
	@Query("select c from Cuenta c where c.numero like ?1 and "
			+ " ( ?2 = -1 or c.entidadFinanciera.idEntidadFinanciera = ?2 ) and "
			+ " ( ?3 = -1 or c.tipoMoneda.idDataCatalogo = ?3 ) and "
			+ " c.estado = ?4")
public abstract List<Cuenta> listaCuentaConsultaCompleja(String numero, int entidadFinanciera, int tipoMoneda, int estado);


	
}
