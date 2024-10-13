package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prestamo.entity.Cuenta;
import com.prestamo.service.CuentaService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/cuenta")
public class CuentaController {

	@Autowired
	private RestTemplate restTemplate;
    
	@Autowired
	private CuentaService cuentaService;

	@GetMapping("/validaNumeroCuenta")
	public ResponseEntity<Map<String, Boolean>> validaNumero(@RequestParam(name = "numero") String numero) {
		List<Cuenta> lstSalida = cuentaService.listaCuentaPorNumeroIgual(numero);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("valid", lstSalida.isEmpty());
		return ResponseEntity.ok(respuesta);
	}

	@GetMapping("/lista")
	public List<Cuenta> lista() {
		restTemplate.postForObject("http://localhost:8091/url/auditoria/registra/MYSQL-sistemaPrestamo-Cuenta-lista", null, String.class);
		List<Cuenta> lstSalida = cuentaService.listaCuenta();
		return lstSalida;
	}

	@GetMapping("/consultaCuentaCompleja")
	public List<Cuenta> consulta(@RequestParam String numero,
			@RequestParam int entidadFinanciera, 
			@RequestParam int tipoMoneda,
			@RequestParam int estado) {
		List<Cuenta> lstSalida = cuentaService.listaCuentaConsultaCompleja("%" + numero + "%", entidadFinanciera,
				tipoMoneda, estado);
		return lstSalida;
	}

	@GetMapping("/listaCuentaPorNumeroLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaCuentaPorNumeroLike(@PathVariable("var") String numero) {
		List<Cuenta> lstSalida = null;
		if (numero.equals("todos")) {
			lstSalida = cuentaService.listaCuenta();
		} else {
			lstSalida = cuentaService.listaCuentaPorNumeroLike(numero + "%");
		}
		return ResponseEntity.ok(lstSalida);
	}

	@PostMapping("/registraCuenta")
	@ResponseBody
	public ResponseEntity<?> registra(@RequestBody Cuenta objCuenta) {
		HashMap<String, Object> salida = new HashMap<>();
		objCuenta.setFechaRegistro(new Date());
		objCuenta.setFechaActualizacion(new Date());
		objCuenta.setEstado(AppSettings.ACTIVO);
		Cuenta objSalida = cuentaService.insertaActualizaCuenta(objCuenta);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		} else {
			salida.put("mensaje", "Registro de cuenta con el ID >>> " + objCuenta.getIdCuenta()
					+ " >>> Numero de cuenta >> " + objCuenta.getNumero());
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaCuenta")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCuenta(@RequestBody Cuenta obj) {
		Map<String, Object> salida = new HashMap<>();
		try {

			obj.setFechaActualizacion(new Date());

			Cuenta objSalida = cuentaService.insertaActualizaCuenta(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Cuenta de ID ==> " + obj.getIdCuenta() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@DeleteMapping("/eliminaCuenta/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaCuenta(@PathVariable("id") int idCuenta) {
		Map<String, Object> salida = new HashMap<>();
		try {
			cuentaService.eliminaCuenta(idCuenta);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Cuenta de ID ==> " + idCuenta + ".");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
}
