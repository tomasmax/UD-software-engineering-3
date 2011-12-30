package iso3.pt.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.service.PtDaoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("unchecked")
public class SubjectEnrollingAction  extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;

	private int subjectId;
	private Alumno selectedStudent = null;
	private List<Asignatura> subjectList = null;
	
	//Getters & Setters
	public List<Asignatura> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Asignatura> subjectList) {
		this.subjectList = subjectList;
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
	
	//DO's	
	public String doEnroll() throws Exception {
		if (selectedStudent != null) {
			PtDaoService pt =  new PtDaoService();
			pt.matricular((int)selectedStudent.getDni(), subjectId);
			subjectList = new ArrayList<Asignatura>();
			Set<Asignatura> aux = pt.getAsignaturas((int)selectedStudent.getDni());
			for(Asignatura asig: aux)
			{
				subjectList.add(asig);
			}
			return "enroll";
		}
		else
			return ERROR;
	}
	
	public String doUnenroll() throws Exception {
		if (selectedStudent != null) {
			PtDaoService pt =  new PtDaoService();
			pt.desmatricular((int)selectedStudent.getDni(), subjectId);
			
			subjectList = new ArrayList<Asignatura>();
			Set<Asignatura> aux = pt.getAsignaturas((int)selectedStudent.getDni());
			for(Asignatura asig: aux)
			{
				subjectList.add(asig);
			}
			return "unenroll";
		}
		else
			return ERROR;
	}
	
	public String doEnter() throws Exception {
		Map<Object, Object> session = ActionContext.getContext().getSession();
		Object logged = session.get("logged");
		if (logged != null)
			return SUCCESS;
		else
			return ERROR;
	}

	@Override
	public void prepare() throws Exception {
		Object obj = ActionContext.getContext().getSession().get("logged");
		if ((obj instanceof Alumno) && obj != null) {
			selectedStudent = (Alumno)obj;
			PtDaoService pt =  new PtDaoService();
			subjectList = new ArrayList<Asignatura>();
			for(Asignatura asig: pt.getAsignaturas())
				if (!pt.getAsignaturas((int)selectedStudent.getDni()).contains(asig))
					subjectList.add(asig);
		}
	}
	
}