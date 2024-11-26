package com.alura.literalura.principal;

import java.util.Scanner;

import com.alura.literalura.model.DatosAutor;
import com.alura.literalura.model.DatosRespuesta;
import com.alura.literalura.servicio.ConsumoAPI;
import com.alura.literalura.servicio.Deserializador;

public class Principal {

    private Scanner teclado;
    private ConsumoAPI consumoApi;
    private final String URL_BASE;
    private final String URL_SEARCH;
    private Deserializador deserializador;
    private final String URL_SORT;

    public Principal() {
        this.teclado = new Scanner(System.in);
        this.consumoApi = new ConsumoAPI();
        this.URL_BASE = "https://gutendex.com/books/";
        this.URL_SEARCH = "?search=";
        this.deserializador = new Deserializador();
        this.URL_SORT = "&sort=popular";
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            mostrarMensaje("""
                    Elija la opción a través de su número:
                    1- buscar libro por título
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    0- salir
                    """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    listarLibrosRegistrados();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listarAutoresVivosEnUnDeterminadoAnio();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;

                case 0:
                    mostrarMensaje("Saliste");
                    break;

                default:
                    mostrarMensaje("Opcion inexistente");
                    break;
            }
        }
    }

    private void buscarLibroPorTitulo() {
        mostrarMensaje("Ingrese el nombre del libro que quiere buscar: ");
        String nombre = teclado.nextLine();
        String json = this.consumoApi.obtenerDatos(this.URL_BASE + this.URL_SEARCH + nombre.replace(" ", "+") + this.URL_SORT);
        var respuesta = this.deserializador.obtenerDatos(json, DatosRespuesta.class);
        mostrarMensaje("----------LIBRO----------");
        mostrarMensaje("Título: " + respuesta.libros().get(0).titulo());
        for (DatosAutor autor : respuesta.libros().get(0).autores()) {
            mostrarMensaje("Autor: " +  autor.nombre());
        }
        mostrarMensaje("Idioma: " +  respuesta.libros().get(0).obtenerPrimerIdioma());
        mostrarMensaje("Número de descargas: " +  respuesta.libros().get(0).cantidadDescargas());
        mostrarMensaje("-------------------------");
    }

    private void listarLibrosRegistrados() {
        mostrarMensaje("Listar libros registrado");
    }

    private void listarAutoresRegistrados() {
        mostrarMensaje("Listar autores registrado");
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        mostrarMensaje("listarAutoresVivosEnUnDeterminadoAnio");
    }

    private void listarLibrosPorIdioma() {
        mostrarMensaje("listarLibrosPorIdioma");
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}
