package servicio;

import java.util.Scanner;

import dominio.Organizador;
import persistencia.OrganizadorDao;

public class OrganizadorServicio implements IOrganizadorServicio {

	private final Scanner sc;
	private OrganizadorDao organizadorDao;

	public OrganizadorServicio(Scanner sc) {
		this.sc = sc;
		this.organizadorDao = new OrganizadorDao();
	}

	@Override
	public Organizador hacerLogin() {
		System.out.println("Nombre de organizador:");
		String nombre = sc.nextLine();
		System.out.println("Contraseña:");
		String contrasenia = sc.nextLine();

		Organizador organizador = organizadorDao.login(nombre, contrasenia);

		if (organizador == null) {
			System.out.println("Organizador o contraseña incorrectos.");
		}

		return organizador;
	}

	@Override
	public void registrarOrganizador() {
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		System.out.println("Correo:");
		String correo = sc.nextLine();
		System.out.println("Contraseña:");
		String contrasenia = sc.nextLine();
		System.out.println("Telefono:");
		String telefono = sc.nextLine();

		Organizador organizador = new Organizador(nombre, correo, contrasenia, telefono);
		boolean registrado = organizadorDao.registrar(organizador);

		if (registrado) {
			System.out.println("Organizador registrado correctamente.");
		} else {
			System.out.println("Ya existe un organizador con ese nombre.");
		}
	}
}
