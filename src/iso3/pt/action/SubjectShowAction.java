package iso3.pt.action;

import iso3.pt.model.Asignatura;
import iso3.pt.model.Unidad;
import iso3.pt.service.PtDaoService;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class SubjectShowAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int subjectId;
	private Asignatura subject = null;
	private Set<Unidad> unitList = null;
		
	//Getters & Setters
	public int getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	public void setUnitList(Set<Unidad> unitList) {
		this.unitList = unitList;
	}

	public Set<Unidad> getUnitList() {
		return unitList;
	}

	public void setSubject(Asignatura subject) {
		this.subject = subject;
	}

	public Asignatura getSubject() {
		return subject;
	}
	
	//DO's
	public String doUnitList()
	{
		if (ActionContext.getContext().getSession().get("logged") != null) {
			PtDaoService pt =  new PtDaoService();
			subject = pt.getAsignatura(subjectId);
			unitList = pt.getUnidades(subjectId);
			return "unitlist";
		}
		else
			return ERROR;
	}
}
