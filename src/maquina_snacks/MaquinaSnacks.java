package maquina_snacks;

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
        //Creamos la lista de productos de tipo Snack
        List<Snack> productos = new ArrayList<>();

        System.out.println("***Maquina de Snacks***");
        Snacks.mostrarSnack(); //Mostrar inventario de Snacks disponibles

        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos);
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
        return consola.nextInt();
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos);
            case 2 -> mostrarTicket(productos);

        }
        return salir;
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos){
        System.out.print("Que Snack quieres comprar (ID) : ");
        int idSnack = consola.nextInt();
        //Validar que el snack exista en la lista de Snacks
        var snackEncontrado = false;
        for(var snack : Snacks.getSnacks()){
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
}
