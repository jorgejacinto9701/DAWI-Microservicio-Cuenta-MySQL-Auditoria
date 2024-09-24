package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.Cuenta;
import com.prestamo.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService{

	@Autowired 
	private CuentaRepository repository ;
	
	@Override
	public Cuenta insertaActualizaCuenta(Cuenta obj) {
		return repository.save(obj);
	}

	@Override
	public List<Cuenta> validanumerodecuentaActualiza(String numero, int idCuenta) {
		
		return repository.validanumerodecuentaActualiza(numero, idCuenta);
	}

	@Override
	public List<Cuenta> listaCuentaPorNumeroLike(String numero) {
		// TODO Auto-generated method stub
		return repository.listaCuentaPorNumeroLike(numero);
	}

	@Override
	public void eliminaCuenta(int idCuenta) {
		// TODO Auto-generated method stub
		repository.deleteById(idCuenta);
	}

	@Override
	public List<Cuenta> listaCuenta() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Cuenta> listaCuentaPorNumeroIgual(String numero) {
		// TODO Auto-generated method stub
		return repository.listaCuentaPorNumeroIgual(numero);
	}

	@Override
	public List<Cuenta> validanumerodecuenta(String numero) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'validanumerodecuenta'");
	}

	@Override
	public List<Cuenta> listaCuentaConsultaCompleja(String numero, int entidadFinanciera, int tipoMoneda, int estado) {
		// TODO Auto-generated method stub
		return repository.listaCuentaConsultaCompleja(numero, entidadFinanciera, tipoMoneda, estado);
	}

}
