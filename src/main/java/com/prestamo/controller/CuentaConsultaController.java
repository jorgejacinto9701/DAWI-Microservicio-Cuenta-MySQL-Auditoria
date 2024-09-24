package com.prestamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Cuenta;
import com.prestamo.service.CuentaService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/consultaCuenta")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class CuentaConsultaController {
	
	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping("/lista")
	public List<Cuenta> lista() {
		List<Cuenta> lstSalida = cuentaService.listaCuenta();
		return lstSalida;
	}
	
	@GetMapping("/consultaCuentaCompleja")
	public List<Cuenta> consulta(  @RequestParam("numero") String numero, 
									@RequestParam("entidadFinanciera") int entidadFinanciera, 
									@RequestParam("tipoMoneda") int tipoMoneda, 
									@RequestParam("estado") int estado) {
		List<Cuenta> lstSalida = cuentaService.listaCuentaConsultaCompleja("%"+numero+"%", entidadFinanciera, tipoMoneda, estado);
		return lstSalida;
	}

}
