package dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

public class Evento {

	private String nombre;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private int duracion;
	private String ubicacion;
	private Categoria categoria;
	private Organizador organizador;
	private HashSet<Usuario> asistentes;

	public Evento(String nombre, String descripcion, LocalDate fecha, LocalTime hora,
			int duracion, String ubicacion, Categoria categoria, Organizador organizador) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
		this.duracion = duracion;
		this.ubicacion = ubicacion;
		this.categoria = categoria;
		this.organizador = organizador;
		this.asistentes = new HashSet<>();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public HashSet<Usuario> getAsistentes() {
		return asistentes;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre +
				"\n  Descripcion: " + descripcion +
				"\n  Fecha: " + fecha + " a las " + hora +
				"\n  Duracion: " + duracion + " minutos" +
				"\n  Ubicacion: " + ubicacion +
				"\n  Categoria: " + categoria.getNombre() +
				"\n  Organizador: " + organizador.getNombre() +
				"\n  Asistentes: " + asistentes.size();
	}
}
