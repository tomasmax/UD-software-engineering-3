package iso3.pt.action;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Profesor;
import iso3.pt.service.PtDaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


public class LecturerShowAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	
	private int subjectId;
	private List<Alumno> studentList = null;
	private Set<Asignatura> listaAsig = null;
	private Profesor profesor = null;
	private Asignatura asignatura = null;	
		
	//Getters & Setters
	public Set<Asignatura> getSubjectList() {
		return listaAsig;
	}
	
	public void setSubjectList(Set<Asignatura> subjectList) {
		this.listaAsig = subjectList;
	}

	public int getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public List<Alumno> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Alumno> studentList) {
		this.studentList = studentList;
	}
	
	public Profesor getLecturer() {
		return profesor;
	}

	public void setLecturer(Profesor lecturer) {
		this.profesor = lecturer;
	}

	public Asignatura getSubject() {
		return asignatura;
	}

	public void setSubject(Asignatura subject) {
		this.asignatura = subject;
	}
	
	public String doLecturerSubjectList()
	{
		if (profesor != null) {
			PtDaoService pt =  new PtDaoService();
			listaAsig = pt.getAsignaturasProfesor(profesor.getId());
			return SUCCESS;
		}
		else
			return ERROR;
	}
	
	public String doStudentList()
	{
		if (profesor != null) {
			PtDaoService pt =  new PtDaoService();
			studentList = new ArrayList<Alumno>();
			Set<Alumno> aux = pt.getAlumnos(subjectId);
			asignatura = pt.getAsignatura(subjectId);
			for(Alumno a: aux)
				studentList.add(a);
			return "studentlist";
		}
		else
			return ERROR;
	}

	@Override
	public void prepare() throws Exception {
		Object obj = ActionContext.getContext().getSession().get("logged");
		if ((obj instanceof Profesor) && obj!=null)
			profesor = (Profesor)obj;
	}
}
