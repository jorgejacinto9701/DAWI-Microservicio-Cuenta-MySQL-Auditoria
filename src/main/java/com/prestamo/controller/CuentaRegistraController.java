package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prestamo.entity.Cuenta;
import com.prestamo.service.CuentaService;
import com.prestamo.util.AppSettings;

@Controller
@RequestMapping("/url/cuenta")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class CuentaRegistraController {

	@Autowired
	private CuentaService cuentaService;
	

	
	@PostMapping
	public ResponseEntity<?> registra(@RequestBody Cuenta objCuenta){
		HashMap<String, Object> salida = new HashMap<>();
		objCuenta.setFechaRegistro(new Date());
		objCuenta.setFechaActualizacion(new Date());
		objCuenta.setEstado(AppSettings.ACTIVO);
		Cuenta objSalida = cuentaService.insertaActualizaCuenta(objCuenta);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de cuenta con el ID >>> " + objCuenta.getIdCuenta() + 
										" >>> Numero de cuenta >> "+ objCuenta.getNumero());
		}
		return ResponseEntity.ok(salida);
	}


	@GetMapping("/validaNumeroCuenta")
	public ResponseEntity<Map<String, Boolean>> validaNumero(@RequestParam(name = "numero") String numero) {
	    List<Cuenta> lstSalida = cuentaService.listaCuentaPorNumeroIgual(numero);
	    Map<String, Boolean> respuesta = new HashMap<>();
	    respuesta.put("valid", lstSalida.isEmpty());
	    return ResponseEntity.ok(respuesta);
	}
	
}
