package com.cibertec.entity;

import java.util.Date;

import lombok.Getter;
import lombok.	Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Alumno {

	private int idAlumno;
	private String nombre;
	private String dni;
	private Date fechaNacimiento;
	private Date fechaRegistro;
	
	
}



