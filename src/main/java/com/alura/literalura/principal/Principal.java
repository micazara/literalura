package com.alura.literalura.principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosAutor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.DatosRespuesta;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.servicio.AutorService;
import com.alura.literalura.servicio.ConsumoAPI;
import com.alura.literalura.servicio.Deserializador;
import com.alura.literalura.servicio.LibroService;

public class Principal {

    private Scanner teclado;
    private ConsumoAPI consumoApi;
    private final String URL_BASE;
    private final String URL_SEARCH;
    private Deserializador deserializador;
    private final String URL_SORT;
    private AutorService autorService;
    private LibroService libroService;

    public Principal(LibroService _libroService, AutorService _autorService) {
        this.teclado = new Scanner(System.in);
        this.consumoApi = new ConsumoAPI();
        this.URL_BASE = "https://gutendex.com/books/";
        this.URL_SEARCH = "?search=";
        this.deserializador = new Deserializador();
        this.URL_SORT = "&sort=popular";
        this.libroService = _libroService;
        this.autorService = _autorService;

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
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                mostrarMensaje("Ingresaste un carácter inválido");
                teclado.nextLine();
                continue;
            } catch (Exception e) {
                mostrarMensaje("Ocurrió un error inesperado");
                teclado.nextLine();
                continue;
            }
            try {
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
                        try {
                            listarAutoresVivosEnUnDeterminadoAnio();
                        } catch (InputMismatchException e) {
                            mostrarMensaje("Ingresaste un carácter inválido");
                        }
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
            } catch (Exception e) {
                mostrarMensaje("Ocurrió un error inesperado");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        mostrarMensaje("Ingrese el nombre del libro que quiere buscar: ");
        String nombre = teclado.nextLine();
        DatosLibro datosLibro = obtenerDatosLibro(nombre);

        if (datosLibro == null) {
            mostrarMensaje("No se encontró ningún libro con el título: " + nombre);
        } else {
            Libro guardado = guardarLibroConAutores(datosLibro);
            mostrarDatosDelLibroGuardado(guardado);
        }
    }

    private void listarLibrosRegistrados() {
        this.libroService.obtenerTodosLosLibros()
                .forEach(this::mostrarDatosDelLibroGuardado);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = this.autorService.obtenerTodosLosAutores();
        if (autores.isEmpty()) {
            mostrarMensaje("No se encontraron autores");
            return;
        }
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        mostrarMensaje("Ingrese el año vivo de autor(es) que desea buscar");
        int anio = teclado.nextInt();
        List<Autor> autores = this.autorService.obtenerAutoresVivosEnUnDeterminadoAnio(anio);
        if (autores.isEmpty()) {
            mostrarMensaje("No se encontraron autores");
            return;
        }
        autores.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        mostrarMensaje("Ingrese el idioma para buscar los libros: ");
        mostrarMensaje("""
                es- español
                en- inglés
                fr- francés
                pt- portugués
                """);
        String idioma = teclado.nextLine();
        List<Libro> librosFiltrados = this.libroService.obtenerLIbrosPorIdioma(idioma);
        if (librosFiltrados.isEmpty()) {
            mostrarMensaje("No se encontraron libros para ese idioma");
        }
        librosFiltrados.forEach(this::mostrarDatosDelLibroGuardado);
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private Libro guardarLibroConAutores(DatosLibro datosLibro) {
        Libro libro = new Libro(datosLibro);
        DatosAutor datosAutor = datosLibro.obtenerPrimerAutor();
        Optional<Autor> autorExistente = this.autorService.findByNombre(datosAutor.nombre());
        Autor autor;
        if (autorExistente.isPresent()) {
            autor = autorExistente.get();
        } else {
            autor = new Autor();
            autor.setNombre(datosAutor.nombre());
            autor.setFechaNacimiento(datosAutor.fechaNacimiento());
            autor.setFechaFallecimiento(datosAutor.fechaFallecimiento());
            autor = this.autorService.save(autor);
        }
        libro.setAutor(autor);
        return this.libroService.save(libro);
    }

    private void mostrarDatosDelLibroGuardado(Libro libro) {
        mostrarMensaje("----------LIBRO----------");
        mostrarMensaje("Título: " + libro.getTitulo());
        mostrarMensaje("Autor: " + libro.getAutor().getNombre());
        mostrarMensaje("Idioma: " + libro.getIdioma());
        mostrarMensaje("Número de descargas: " + libro.getCantidadDescargas());
        mostrarMensaje("-------------------------");
    }

    private DatosLibro obtenerDatosLibro(String nombre) {
        String json = this.consumoApi
                .obtenerDatos(this.URL_BASE + this.URL_SEARCH + nombre.replace(" ", "+") + this.URL_SORT);
        DatosRespuesta respuesta = this.deserializador.obtenerDatos(json, DatosRespuesta.class);
        if (respuesta.libros() == null || respuesta.libros().isEmpty()) {
            return null;
        }
        return respuesta.libros().get(0);
    }

}
