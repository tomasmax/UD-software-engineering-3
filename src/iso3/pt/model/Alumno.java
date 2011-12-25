package iso3.pt.model;

import java.util.HashSet;
import java.util.Set;

public class Alumno {
	
	private int dni;
	private String password;
	private String nombre;
	private String telefono;
	
	private Set<Asignatura> asignaturas;
	private Set<Evaluacion> evaluaciones;
		
	public Alumno() {
		super();
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
	public int getDni() {
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
	public Alumno(int dni, String password, String nombre, String telefono) {
		super();
		this.dni = dni;
		this.password = password;
		this.nombre = nombre;
		this.telefono = telefono;
		this.asignaturas = new HashSet<Asignatura>();
		this.evaluaciones = new HashSet<Evaluacion>();
	}
	
	
}
