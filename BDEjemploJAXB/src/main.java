import clase.ManejadorCorazoncitos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args){
        ManejadorCorazoncitos ma = new ManejadorCorazoncitos();

        File origen1 = new File("src\\corazonesSolitarios.xml");
        File origen2 = new File("src\\masCorazones.xml");
        File destino = new File("src\\Corazones.xml");
        // Cargamos el XML mediante unmarshaling
        ma.abrirListaCorazoncitosJAXB(origen1,origen2);

        ma.crearListaFinal();

        ma.ordenar();

        // Y generamso un nuevo XML mediante marshaling
        ma.guardarListaPersonas(destino);

    }
}
