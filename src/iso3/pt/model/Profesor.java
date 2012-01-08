package iso3.pt.model;

public class Profesor {
	private Integer id;
	private String nombre;
	private Integer dni;
	private String password;
	private Integer telefono;
	private String email;
	private String despacho;
	
	protected Profesor(){
	}
	
	public Profesor(String nombre) {
		super();
		this.nombre = nombre;

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
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

	public String toString(){
		return "(" + this.id + " | " + this.nombre + ")";
	}
}
