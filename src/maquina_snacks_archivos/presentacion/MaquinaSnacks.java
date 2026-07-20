package maquina_snacks_archivos.presentacion;

import maquina_snacks_archivos.dominio.Snack;
import maquina_snacks_archivos.servicio.IServicioSnacks;
import maquina_snacks_archivos.servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);

        //Creamos el objeto para obtener el servicio de Snacks (lista)
        IServicioSnacks servicioSnacks = new ServicioSnacksLista();

        //Creamos la lista de productos de tipo Snack
        List<Snack> productos = new ArrayList<>();

        System.out.println("***Maquina de Snacks***");
        servicioSnacks.mostrarSnack(); //Mostrar inventario de Snacks disponibles

        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);
            }catch(Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            finally {
                System.out.println();
            }
        }

    }

    private static int mostrarMenu(Scanner consola){
        System.out.println("""
                Menu:
                1. Comprar Snack
                2. Mostrar ticket
                3. Agregar nuevo Snack
                4. Salir
                Elige una opcion: \s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> {
                System.out.println("Hasta pronto");
                return salir = true;
            }
            default -> System.out.println("Opcion invalida: " + opcion);

        }
        return salir;
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks){
        System.out.print("Que Snack quieres comprar (ID) : ");
        var idSnack = Integer.parseInt(consola.nextLine());
        //Validar que el snack exista en la lista de Snacks
        var snackEncontrado = false;
        for(var snack : servicioSnacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                //Se agrega Snack a la lista de productos
                productos.add(snack);
                System.out.println("Ok, Snack agregado: " + snack);
                snackEncontrado = true;
                break;//Termina la iteracion del ciclo for cuando se encuentre el snack
            }
        }
        if(!snackEncontrado){
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de Venta ***";
        var total = 0.0;

        for(var producto : productos){
            ticket += "\n\t" + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnacks){
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());

        servicioSnacks.agregarSnack(new Snack(nombre, precio));

        System.out.println("Snack agregado correctamente !!");
        servicioSnacks.mostrarSnack();
    }

}
