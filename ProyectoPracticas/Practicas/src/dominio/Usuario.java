package dominio;

public class Usuario {

	private String nombre;
	private String correo;
	private String constrasenia;

	public Usuario(String nombre, String correo, String constrasenia) {
		this.nombre = nombre;
		this.correo = correo;
		this.constrasenia = constrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getConstrasenia() {
		return constrasenia;
	}

	public void inscribirEvento(Evento evento) {
		evento.getAsistentes().add(this);
	}

	public void cancelarInscripcion(Evento evento) {
		evento.getAsistentes().remove(this);
	}

	@Override
	public String toString() {
		return nombre + " (" + correo + ")";
	}
}
