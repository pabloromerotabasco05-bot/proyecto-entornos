package servicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

import dominio.Categoria;
import dominio.Evento;
import dominio.Organizador;
import dominio.Usuario;
import persistencia.CategoriaDao;
import persistencia.EventoDao;
import util.Util;

public class EventoServicio implements IEventoServicio {

	private final Scanner sc;
	private EventoDao eventoDao;
	private CategoriaDao categoriaDao;

	public EventoServicio(Scanner sc) {
		this.sc = sc;
		this.eventoDao = new EventoDao();
		this.categoriaDao = new CategoriaDao();
	}

	@Override
	public void mostrarEventos() {
		HashMap<String, Evento> eventos = eventoDao.obtenerEventos();

		if (eventos.isEmpty()) {
			System.out.println("No hay eventos disponibles.");
			return;
		}

		System.out.println("\n--- Eventos disponibles ---");
		for (Evento evento : eventos.values()) {
			System.out.println(evento);
			System.out.println();
		}
	}

	@Override
	public void mostrarEventosUsuario(Usuario usuario) {
		HashMap<String, Evento> eventos = eventoDao.obtenerEventos();
		boolean encontrado = false;

		System.out.println("\n--- Eventos en los que estas inscrito ---");
		for (Evento evento : eventos.values()) {
			if (evento.getAsistentes().contains(usuario)) {
				System.out.println(evento);
				System.out.println();
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("No estas inscrito en ningun evento.");
		}
	}

	@Override
	public void inscribirUsuario(Usuario usuario) {
		HashMap<String, Evento> eventos = eventoDao.obtenerEventos();

		if (eventos.isEmpty()) {
			System.out.println("No hay eventos disponibles.");
			return;
		}

		mostrarEventos();
		System.out.println("Introduce el nombre del evento al que quieres inscribirte:");
		String nombre = sc.nextLine();

		Evento evento = eventos.get(nombre);
		if (evento == null) {
			System.out.println("No existe ningun evento con ese nombre.");
			return;
		}

		if (evento.getAsistentes().contains(usuario)) {
			System.out.println("Ya estas inscrito en este evento.");
			return;
		}

		usuario.inscribirEvento(evento);
		System.out.println("Te has inscrito correctamente en: " + evento.getNombre());
	}

	@Override
	public void cancelarInscripcion(Usuario usuario) {
		HashMap<String, Evento> eventos = eventoDao.obtenerEventos();
		boolean tieneEventos = false;

		System.out.println("\n--- Eventos en los que estas inscrito ---");
		for (Evento evento : eventos.values()) {
			if (evento.getAsistentes().contains(usuario)) {
				System.out.println("- " + evento.getNombre());
				tieneEventos = true;
			}
		}

		if (!tieneEventos) {
			System.out.println("No estas inscrito en ningun evento.");
			return;
		}

		System.out.println("Introduce el nombre del evento del que quieres cancelar la inscripcion:");
		String nombre = sc.nextLine();

		Evento evento = eventos.get(nombre);
		if (evento == null) {
			System.out.println("No existe ningun evento con ese nombre.");
			return;
		}

		if (!evento.getAsistentes().contains(usuario)) {
			System.out.println("No estas inscrito en ese evento.");
			return;
		}

		usuario.cancelarInscripcion(evento);
		System.out.println("Has cancelado tu inscripcion en: " + evento.getNombre());
	}

	@Override
	public void mostrarEventosOrganizador(Organizador organizador) {
		HashMap<String, Evento> eventos = eventoDao.obtenerEventos();
		boolean encontrado = false;

		System.out.println("\n--- Eventos que has creado ---");
		for (Evento evento : eventos.values()) {
			if (evento.getOrganizador().getNombre().equals(organizador.getNombre())) {
				System.out.println(evento);
				System.out.println();
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("No has creado ningun evento aun.");
		}
	}

	@Override
	public void crearEvento(Organizador organizador) {
		System.out.println("Nombre del evento:");
		String nombre = sc.nextLine();

		System.out.println("Descripcion:");
		String descripcion = sc.nextLine();

		LocalDate fecha = null;
		while (fecha == null) {
			System.out.println("Fecha (YYYY-MM-DD):");
			try {
				fecha = LocalDate.parse(sc.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Formato incorrecto. Use YYYY-MM-DD (ejemplo: 2026-12-31).");
			}
		}

		LocalTime hora = null;
		while (hora == null) {
			System.out.println("Hora (HH:MM):");
			try {
				hora = LocalTime.parse(sc.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Formato incorrecto. Use HH:MM (ejemplo: 20:30).");
			}
		}

		int duracion = Util.pedirNumeroEntero(sc, "Duracion en minutos:");

		System.out.println("Ubicacion:");
		String ubicacion = sc.nextLine();

		// Seleccionar categoria
		HashMap<String, Categoria> categorias = categoriaDao.obtenerCategorias();
		String[] nombresCat = categorias.keySet().toArray(new String[0]);

		StringBuilder menuCat = new StringBuilder("Selecciona una categoria:\n");
		for (int i = 0; i < nombresCat.length; i++) {
			menuCat.append((i + 1) + ".- " + nombresCat[i] + "\n");
		}

		int opcionCat = Util.pedirNumeroEntero(sc, menuCat.toString().trim());
		if (opcionCat < 1 || opcionCat > nombresCat.length) {
			System.out.println("Categoria no valida. Operacion cancelada.");
			return;
		}

		Categoria categoria = categoriaDao.obtenerCategoria(nombresCat[opcionCat - 1]);

		Evento evento = organizador.organizarEvento(nombre, descripcion, fecha, hora, duracion, ubicacion, categoria);
		boolean insertado = eventoDao.insertarEvento(evento);

		if (insertado) {
			System.out.println("Evento '" + nombre + "' creado correctamente.");
		} else {
			System.out.println("Ya existe un evento con ese nombre.");
		}
	}
}
