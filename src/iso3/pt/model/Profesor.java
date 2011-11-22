package iso3.pt.model;

public class Profesor {
		
	private int id;
	private String nombre;
	private int dni;
	private String password;
	private int telefono;
	private String email;
	private String despacho;
	
	
	public Profesor(int id, String nombre, int dni, String password,
			int telefono, String email, String despacho) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.password = password;
		this.telefono = telefono;
		this.email = email;
		this.despacho = despacho;
	}

	public Profesor() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}
	
	

}
