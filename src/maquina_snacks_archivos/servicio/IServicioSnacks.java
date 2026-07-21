package maquina_snacks_archivos.servicio;

import maquina_snacks_archivos.dominio.Snack;

import java.util.List;

public interface IServicioSnacks {

    void agregarSnack(Snack snack);
    void mostrarSnack();
    List<Snack> getSnacks();

}
