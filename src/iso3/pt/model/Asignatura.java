package iso3.pt.model;

import java.util.HashSet;
import java.util.Set;

public class Asignatura {
	
	private Integer id;
	private Integer codigo;
	
	private Float creditos;
	private String nombre;
	
	
	private Profesor profesor;
	
	private Set<Alumno> alumnos;
	private Set<Unidad> unidades;
	
	public Asignatura(){
	}
	
	public Asignatura(String nombre, Integer codigo, Float creditos) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		profesor = new Profesor();
		alumnos = new HashSet<Alumno>();
		unidades = new HashSet<Unidad>();

	}
	
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Set<Unidad> getUnidades() {
		return unidades;
	}

	public void setUnidades(Set<Unidad> unidades) {
		this.unidades = unidades;
	}

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getCreditos() {
		return creditos;
	}
	public void setCreditos(float creditos) {
		this.creditos = creditos;
	}
	
	public boolean estaMatriculado(Alumno a)
	{
		return this.alumnos.contains(a);	
	}
	
	public String toString(){
		return "(" + this.id + "|" + this.nombre + ")";
	}
}
