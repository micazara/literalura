package com.alura.literalura.principal;

import java.util.Scanner;

public class Principal {

    private Scanner teclado;

    public Principal() {
        this.teclado = new Scanner(System.in);
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
    }

    private void listarLibrosRegistrados() {
        mostrarMensaje("Listar libros registrado");
    }

    private void listarAutoresRegistrados() {
        mostrarMensaje("Listar atuores registrado");
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
