package iso3.pt.model;

public class Unidad {
	
	private Integer id;
	private String titulo;
	private String acronimo;
	private String contenido;



	public Unidad(){
	}
	
	public Unidad(String titulo, String acronimo, String contenido) {
		super();
		this.titulo = titulo;
		this.acronimo = acronimo;
		this.contenido = contenido;

	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String toString(){
		return "(" + this.id + " | " + this.titulo + ")";
	}
}
