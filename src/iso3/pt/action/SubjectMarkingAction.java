package iso3.pt.action;

import java.util.ArrayList;
import java.util.List;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.service.PtDaoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SubjectMarkingAction  extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;

	private String concept = null;
	private String mark = null;
	private int subjectId;
	private int studentDni;
	private Alumno selectedStudent = null;
	private Asignatura subject = null;
	private List<Evaluacion> evaluationList = null;

	//Getter & Setters
	public List<Evaluacion> getEvaluationList() {
		return evaluationList;
	}

	public void setEvaluationList(List<Evaluacion> evaluationList) {
		this.evaluationList = evaluationList;
	}

	public Alumno getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Alumno selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getStudentDni() {
		return studentDni;
	}

	public void setStudentDni(int studentDni) {
		this.studentDni = studentDni;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public Asignatura getSubject() {
		return subject;
	}

	public void setSubject(Asignatura subject) {
		this.subject = subject;
	}

	
	//DO's
	public String doSubmitMark() {
		Object obj = ActionContext.getContext().getSession().get("logged");
		if ((obj instanceof Profesor) && obj != null) {
			PtDaoService pt =  new PtDaoService();
			Float m = Float.parseFloat(this.mark);
			pt.addEvaluacion(concept, m, subjectId, studentDni);
			
			evaluationList = new ArrayList<Evaluacion>();
			List<Evaluacion> aux = pt.getEvaluacionesAsignatura(subjectId);
			for(Evaluacion eva: aux)
				if(eva.getAlumno().getDni() == studentDni)
					evaluationList.add(eva);
			return "submitmark";
		}
		else
			return ERROR;
	}
	
	public String doEnter() throws Exception {
		Object obj = ActionContext.getContext().getSession().get("logged");
		if ((obj instanceof Profesor) && obj != null)
			return SUCCESS;
		else
			return ERROR;
			
	}

	@Override
	public void prepare() throws Exception {
		Object obj = ActionContext.getContext().getSession().get("logged");
		if ((obj instanceof Profesor) && obj != null) {
			PtDaoService pt =  new PtDaoService();
			selectedStudent = pt.getAlumno(studentDni);
			subject = pt.getAsignatura(subjectId);
		}
	}
	
}