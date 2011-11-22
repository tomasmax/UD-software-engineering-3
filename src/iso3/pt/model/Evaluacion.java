package iso3.pt.model;

public class Evaluacion {
	
	private int id;
	private String concepto;
	private float nota;
	
	private Alumno alumno;
	private Asignatura asignatura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Evaluacion( String concepto, float nota, Alumno alumno,
			Asignatura asignatura) {
		super();
		
		this.concepto = concepto;
		this.nota = nota;
		this.alumno = alumno;
		this.asignatura = asignatura;
	}
	

}
