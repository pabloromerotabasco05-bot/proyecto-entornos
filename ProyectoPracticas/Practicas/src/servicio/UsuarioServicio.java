package servicio;

import java.util.Scanner;

import dominio.Usuario;
import persistencia.UsuarioDao;

public class UsuarioServicio implements IUsuarioServicio {

	private final Scanner sc;
	private UsuarioDao usuarioDao;

	public UsuarioServicio(Scanner sc) {
		this.sc = sc;
		this.usuarioDao = new UsuarioDao();
	}

	@Override
	public Usuario hacerLogin() {
		System.out.println("Nombre de usuario:");
		String nombre = sc.nextLine();
		System.out.println("Contraseña:");
		String contrasenia = sc.nextLine();

		Usuario usuario = usuarioDao.login(nombre, contrasenia);

		if (usuario == null) {
			System.out.println("Usuario o contraseña incorrectos.");
		}

		return usuario;
	}

	@Override
	public void registrarUsuario() {
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		System.out.println("Correo:");
		String correo = sc.nextLine();
		System.out.println("Contraseña:");
		String contrasenia = sc.nextLine();

		Usuario usuario = new Usuario(nombre, correo, contrasenia);
		boolean registrado = usuarioDao.registrar(usuario);

		if (registrado) {
			System.out.println("Usuario registrado correctamente.");
		} else {
			System.out.println("Ya existe un usuario con ese nombre.");
		}
	}
}
