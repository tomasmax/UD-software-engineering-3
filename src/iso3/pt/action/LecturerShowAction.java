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
	private Set<Asignatura> subjectList = null;
	private Profesor lecturer = null;
	private Asignatura subject = null;	
		
	//Getters & Setters
	public Set<Asignatura> getSubjectList() {
		return subjectList;
	}
	
	public void setSubjectList(Set<Asignatura> subjectList) {
		this.subjectList = subjectList;
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
		return lecturer;
	}

	public void setLecturer(Profesor lecturer) {
		this.lecturer = lecturer;
	}

	public Asignatura getSubject() {
		return subject;
	}

	public void setSubject(Asignatura subject) {
		this.subject = subject;
	}
	
	
	//DO's
	public String doLecturerSubjectList()
	{
		if (lecturer != null) {
			PtDaoService pt =  new PtDaoService();
			subjectList = pt.getAsignaturasProfesor(lecturer.getId());
			return SUCCESS;
		}
		else
			return ERROR;
	}
	
	public String doStudentList()
	{
		if (lecturer != null) {
			PtDaoService pt =  new PtDaoService();
			studentList = new ArrayList<Alumno>();
			Set<Alumno> aux = pt.getAlumnos(subjectId);
			subject = pt.getAsignatura(subjectId);
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
			lecturer = (Profesor)obj;
	}
}
