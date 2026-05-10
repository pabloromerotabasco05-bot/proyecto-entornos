package servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dominio.Categoria;
import persistencia.CategoriaDao;
import util.Util;

public class CategoriaServicio implements ICategoriaServicio {

    private final Scanner sc;
    private CategoriaDao categoriaDao;

    public CategoriaServicio(Scanner sc) {
        this.sc = sc;
        this.categoriaDao = new CategoriaDao();
    }

    @Override
    public Categoria buscarCategoria() {
        HashMap<String, Categoria> categorias = categoriaDao.obtenerCategorias();

        ArrayList<Categoria> lista = new ArrayList<>(categorias.values());

        System.out.println("Categorias disponibles:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ".- " + lista.get(i).getNombre());
        }

        int opcion = Util.pedirNumeroEntero(sc, "Introduce el numero de la categoria:");

        if (opcion < 1 || opcion > lista.size()) {
            System.out.println("Opcion no valida.");
            return null;
        }

        return lista.get(opcion - 1);
    }
}