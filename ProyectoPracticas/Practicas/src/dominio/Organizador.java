package dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Organizador extends Usuario {

	private String telefono;

	public Organizador(String nombre, String correo, String constrasenia, String telefono) {
		super(nombre, correo, constrasenia);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public Evento organizarEvento(String nombre, String descripcion, LocalDate fecha,
			LocalTime hora, int duracion, String ubicacion, Categoria categoria) {
		return new Evento(nombre, descripcion, fecha, hora, duracion, ubicacion, categoria, null);
	}

	@Override
	public String toString() {
		return getNombre() + " (" + getCorreo() + ") - Tel: " + telefono;
	}
}
