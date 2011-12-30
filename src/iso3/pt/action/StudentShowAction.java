package iso3.pt.action;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.service.PtDaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class StudentShowAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	
	private int subjectId;
	private int studentDni;
	private Set<Asignatura> subjectList = null;
	private List<Evaluacion> evaluationList = null;
	private Alumno selectedStudent = null;
	private Asignatura subject = null;	
		
	//Getters & Setters
	public Set<Asignatura> getSubjectList() {
		return subjectList;
	}
	
	public void setSubjectList(Set<Asignatura> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Evaluacion> getEvaluationList() {
		return evaluationList;
	}

	public void setEvaluationList(List<Evaluacion> evaluationList) {
		this.evaluationList = evaluationList;
	}

	public int getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	public Alumno getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Alumno selectedStudent) {
		this.selectedStudent = selectedStudent;
	}
	
	public int getStudentDni() {
		return studentDni;
	}

	public void setStudentDni(int studentDni) {
		this.studentDni = studentDni;
	}
	
	
	public Asignatura getSubject() {
		return subject;
	}

	public void setSubject(Asignatura subject) {
		this.subject = subject;
	}
	
	
	//DO's	
	public String doStudentSubjectList()
	{
		if (selectedStudent != null) {
			PtDaoService pt =  new PtDaoService();
			subjectList = pt.getAsignaturas(selectedStudent.getDni());
			return SUCCESS;
		}
		else
			return ERROR;
	}
	
	public String doMarkList()
	{
		Object obj = ActionContext.getContext().getSession().get("logged");
		//En este caso el IF no se hace con selectedStudent porque a este metodo tambien lo llaman los profesores
		if (obj != null) {
			PtDaoService pt =  new PtDaoService();
			if (!(obj instanceof Alumno))
				selectedStudent = pt.getAlumno(studentDni);
			subjectList = pt.getAsignaturas(studentDni);
			subject = pt.getAsignatura(subjectId);
			evaluationList = new ArrayList<Evaluacion>();
			List<Evaluacion> aux2 = pt.getEvaluacionesAsignatura(subjectId);
			for(Evaluacion eva: aux2)
				if(eva.getAlumno().getDni() == studentDni)
					evaluationList.add(eva);
			return "showmark";
		}
		else
			return ERROR;
	}
	
	public String doListAllMarks() throws Exception {
		if (selectedStudent != null) {
			PtDaoService pt =  new PtDaoService();
			evaluationList = new ArrayList<Evaluacion>();
			List<Evaluacion> aux2 = pt.getEvaluacionesOrderedByAsignatura(studentDni);
			for(Evaluacion eva: aux2)
				if(eva.getAlumno().getDni() == studentDni)
					evaluationList.add(eva);
			return "showallmarks";
		}
		else
			return ERROR;
	}

	@Override
	public void prepare() throws Exception {
		Object obj = ActionContext.getContext().getSession().get("logged");
		if ((obj instanceof Alumno) && obj != null) {
			selectedStudent = (Alumno)obj;
			studentDni = (int)selectedStudent.getDni();
		}
	}
}