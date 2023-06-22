package com.cibertec.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Empresa {

	private int idEmpresa;
	private String contacto;
	private String email;
	private int flag;
	private String razonSocial;
	private String ruc;
	private int idTipoRiesgo;
}
