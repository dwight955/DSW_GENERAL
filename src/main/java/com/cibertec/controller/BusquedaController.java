package com.cibertec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cibertec.entity.Empresa;
import com.cibertec.entity.TipoRiesgo;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@RequestMapping("/rest/busqueda")
@CommonsLog
public class BusquedaController {

	//PostgreSQL
	String URL_EMPRESA = "http://localhost:8091/rest/Empresa/porTipoRiesgo";
	
	//MongoDB
	String URL_TIPO_RIESGO = "http://localhost:8093/rest/TipoRiesgo";
	
	//MySQL
	//String URL_MATRICULA = "http://localhost:8092/rest/matricula/porCurso";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/Empresas")
	public ResponseEntity<?> lista(){
		List<Empresa> lstSalida = new ArrayList<Empresa>();
		
		ResponseEntity<List<TipoRiesgo>> responseEntity = restTemplate.exchange(URL_TIPO_RIESGO, HttpMethod.GET, null, new ParameterizedTypeReference<List<TipoRiesgo>>(){});
		List<TipoRiesgo> lstTipoRiesgo = responseEntity.getBody();
		log.info(">>> lstCategoria " + lstTipoRiesgo);
		
		for (TipoRiesgo tipoRiesgo : lstTipoRiesgo) {
			ResponseEntity<List<Empresa>> responseEntity2 = restTemplate.exchange(URL_EMPRESA+"/"+tipoRiesgo.getIdTipoRiesgo(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Empresa>>(){});
			List<Empresa> lstEmpresa = responseEntity2.getBody();
			lstSalida.addAll(lstEmpresa);
		}
		return ResponseEntity.ok(lstSalida);
	}
	/*
	@GetMapping("/matriculas")
	public ResponseEntity<?> lista() {
		List<Matricula> lstSalida = new ArrayList<Matricula>(); 
		
		ResponseEntity<List<Categoria>> responseEntity = 	restTemplate.exchange(URL_CATEGORIA, HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>(){});
		List<Categoria> lstCategoria = responseEntity.getBody();
		log.info(">>> lstCategoria " + lstCategoria);
		
		for(Categoria objCategoria:lstCategoria) {
			ResponseEntity<List<Curso>> responseEntity2 = 	restTemplate.exchange(URL_CURSO+"/"+ objCategoria.getIdCategoria(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Curso>>(){});
			List<Curso> lstCurso = responseEntity2.getBody();
			
			log.info(">>> " + lstCategoria.indexOf(objCategoria) + " >>> lstCurso " + lstCurso);
			
			for(Curso objCurso:lstCurso) {
				ResponseEntity<List<Matricula>> responseEntity3 = 	restTemplate.exchange(URL_MATRICULA+"/"+ objCurso.getIdCurso(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Matricula>>(){});
				List<Matricula> lstMatricula = responseEntity3.getBody();
				log.info(">>> " + lstCurso.indexOf(objCurso) + " >>> lstMatricula " + lstMatricula);
				
				lstSalida.addAll(lstMatricula);
			}
			
		}
		return ResponseEntity.ok(lstSalida);
	}
	*/

}
