package clase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class ManejadorCorazoncitos {
    Corazoncitos listaPersonas;
    Corazoncitos listaPersonas2;

    public void abrirListaCorazoncitosJAXB (File archivoXML,File archivoXML2){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(Corazoncitos.class);
            Unmarshaller u = contexto.createUnmarshaller();
            listaPersonas = (Corazoncitos) u.unmarshal(archivoXML);
            listaPersonas2 = (Corazoncitos) u.unmarshal(archivoXML2);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void crearListaFinal(){

        for(TipoPersona unaPersona:listaPersonas2.getPersona()){
            listaPersonas.getPersona().add(unaPersona);
        }

    }



    public void ordenar(){
        Collections.sort(listaPersonas.getPersona(), new ComparatorRoll());

    }

    public void guardarListaPersonas(File archivoXML){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(Corazoncitos.class);
            Marshaller marshalero = contexto.createMarshaller();
            marshalero.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter escribiente = new StringWriter();
            marshalero.marshal(listaPersonas, archivoXML);
            // ahora lo marshaleamos a un stream para visualizarlo
            marshalero.marshal(listaPersonas, escribiente);
            System.out.println("-----------------");
            System.out.println("Object2XML:");
            System.out.println(escribiente.toString());
            System.out.println("-----------------");
        } catch (JAXBException ex) {
            Logger.getLogger(ManejadorCorazoncitos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

