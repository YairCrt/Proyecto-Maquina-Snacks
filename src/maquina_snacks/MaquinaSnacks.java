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
        var console = new Scanner(System.in);
        //Creamos la lista de productos de tipo Snack
        List<Snack> productos = new ArrayList<>();

        System.out.println("***Maquina de Snacks***");
        Snacks.mostrarSnack(); //Mostrar inventario de Snacks disponibles

    }
}
