package iso3.pt.action;

import iso3.pt.model.Alumno;
import iso3.pt.model.Profesor;
import iso3.pt.service.PtDaoService;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("unchecked")
public class LoginAction  extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;

	private String selectedRole = null;
	private String username = null;
	private String password = null;
	Profesor lecturer = null;
	Alumno student = null;
	private List<String> roleList = null;
	

	public Alumno getSelectedStudent() {
		return student;
	}

	public void setSelectedStudent(Alumno selectedStudent) {
		this.student = selectedStudent;
	}

	public Profesor getLecturer() {
		return lecturer;
	}

	public void setLecturer(Profesor lecturer) {
		this.lecturer = lecturer;
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	
	public String doLogin() {
		PtDaoService pt =  new PtDaoService();
		if (!this.getUsername().equals("") && !this.getPassword().equals("")) {
			if (this.selectedRole.equals(getText("login.role.student"))) {
				try {
					student = pt.loginAlumno(Integer.parseInt(this.getUsername()), this.getPassword());
					Map<Object, Object> session = ActionContext.getContext().getSession();
					session.put("logged", student);
					return "loginstudent";
				} catch (Exception e) {
					addActionError(getText("login.loginerror"));
				}
			}
			else {
				try {
					lecturer = pt.loginProfesor(Integer.parseInt(this.getUsername()), this.getPassword());
					Map<Object, Object> session = ActionContext.getContext().getSession();
					session.put("logged", lecturer);
					return "loginlecturer";
				} catch (Exception e) {
					addActionError(getText("login.loginerror"));
				}			
			}	
		}
		else if (this.getUsername().equals("") && this.getPassword().equals("")){
			this.addFieldError("username", getText("login.missingerror"));
			this.addFieldError("password", getText("login.missingerror"));
		}
		else if (this.getUsername().equals(""))
			this.addFieldError("username", getText("login.missingerror"));
		else
			this.addFieldError("password", getText("login.missingerror"));
		return INPUT;
			
	}

	public String doEnter() throws Exception {
			return SUCCESS;
	}
	
	public String doLogout() {
		Map<Object, Object> session = ActionContext.getContext().getSession();
		Object logged = session.get("logged");
		if (logged != null) {
			session.remove("logged");
			return SUCCESS;
		}
		else
			return ERROR;
	}
	
	@Override
	public void prepare() throws Exception {
		this.roleList = new java.util.ArrayList<String>();
		this.roleList.add(getText("login.role.student"));
		this.roleList.add(getText("login.role.lecturer"));
	}
}