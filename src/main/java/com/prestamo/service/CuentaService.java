package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Cuenta;

public interface CuentaService {

	//para validacion
	public abstract List<Cuenta> listaCuentaPorNumeroIgual(String numero);
	public abstract List<Cuenta> validanumerodecuentaActualiza(String numero, int idCuenta);
	
	//para el crud
	public abstract Cuenta insertaActualizaCuenta(Cuenta obj);
	public abstract List<Cuenta> listaCuentaPorNumeroLike(String numero);
	public abstract void eliminaCuenta(int idCuenta);
	public abstract List<Cuenta> listaCuenta();
	//public abstract Cuenta obtenerCuentaPorNumero(String numero);

	//PC1
	// abstract Cuenta insertaActualizaCuenta(Cuenta obj);
	public abstract List<Cuenta> validanumerodecuenta(String numero);

	
	//Para la Consulta
	public abstract List<Cuenta> listaCuentaConsultaCompleja(String numero, int entidadFinanciera, int tipoMoneda, int estado);
		

}
