package probandoSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionContenido extends DefaultHandler {

    private boolean autor;
    private boolean titulo;
    private boolean formato;
    private boolean localizacion;
    private Musica musica = new Musica();
    private Connection conexion = null;

    public Connection crearConexion(){
        String sourceURL = "jdbc:sqlserver://localhost;DatabaseName=Musica";
        //String sourceURL = "jdbc:sqlserver://DESKTOP-VEDOCS8;DatabaseName=CasaDeApuestas";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(sourceURL, "victor", "victor");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexion;
    }

    public GestionContenido() {
        super();
    }
    @Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }
    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes att){
        if(qName.equals("autor")){
            autor = true;
        }
        if(qName.equals("titulo")){
            titulo = true;
        }
        if(qName.equals("localizacion")){
            localizacion = true;
        }
        if(qName.equals("formato")){
            formato = true;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName){
        if(qName.equals("album")){
            PreparedStatement sentencia = null;
            String miOrden = "";
            conexion = crearConexion();
            try{
                miOrden = "INSERT INTO Discos VALUES(?,?,?,?)";
                sentencia = conexion.prepareStatement(miOrden);
                sentencia.setString(1,musica.getAutor());
                sentencia.setString(2,musica.getTitulo());
                sentencia.setString(3,musica.getFormato());
                sentencia.setString(4,musica.getLocalizacion());
                sentencia.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();;
            }
        }

    }
    @Override
    public void characters (char[] ch, int inicio, int longitud)
            throws SAXException{
        if(autor){
            musica.setAutor(new String(ch, inicio, longitud));
            autor = false;
        }
        if(titulo){
            musica.setTitulo(new String(ch, inicio, longitud));
            titulo = false;
        }
        if(formato){
            musica.setFormato(new String(ch, inicio, longitud));
            formato = false;
        }
        if(localizacion){
            musica.setLocalizacion(new String(ch, inicio, longitud));
            localizacion = false;
        }


    }
}
// FIN GestionContenido
