package iso3.pt.model;

public class Unidad {

	private int id;
	private String acronimo;
	private String titulo;
	private String contenido;
	
	
	public Unidad() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Unidad(String acronimo, String titulo, String contenido) {
		super();
		
		this.acronimo = acronimo;
		this.titulo = titulo;
		this.contenido = contenido;
	}
	
	
}
