package iso3.pt.model;

public class Evaluacion {
	
	private Integer id;
	private Float nota;
	private String concepto;
	
	private Asignatura asignatura;
	private Alumno alumno;
	
	public Evaluacion(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public String toString(){
		return "(" + this.concepto + " | " + this.alumno + " " + nota + " AsigID: " + this.asignatura.getId() +" )";
	}
	
	public Evaluacion(Asignatura asignatura, Alumno alumno, Float nota, String concepto) {
		
		this.asignatura = asignatura;
		this.alumno = alumno;
		this.nota = nota;
		this.concepto = concepto;

	}
}
