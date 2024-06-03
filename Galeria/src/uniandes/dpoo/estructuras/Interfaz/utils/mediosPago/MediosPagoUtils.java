package uniandes.dpoo.estructuras.Interfaz.utils.mediosPago;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MediosPagoUtils {
    public static void guardar(String medio, int idCompra){
        File file = new File("data/MediosPago/log/" + medio + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(idCompra + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
