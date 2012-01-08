package iso3.pt.model;

import java.util.HashSet;
import java.util.Set;

public class Alumno {
	
	private Integer dni;
	private String nombre;
	private String password;
	private String telefono;
	
	private Set<Asignatura> asignaturas;
	private Set<Evaluacion> evaluaciones;
	
	protected Alumno(){
	}
	
	public Alumno(Integer dni, String nombre, String pass) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		password = pass;
		asignaturas = new HashSet<Asignatura>();
		evaluaciones = new HashSet<Evaluacion>();

	}
	
	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(Set<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	public Set<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}
	public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String toString(){
		return "(" + this.dni + " | " + this.nombre + ")";
	}
}
