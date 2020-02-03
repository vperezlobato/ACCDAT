package probandoSAX;

public class Musica {
    private String autor;
    private String titulo;
    private String formato;
    private String localizacion;

    public Musica(){
        autor = "";
        titulo = "";
        formato = "";
        localizacion = "";
    }

    public Musica(String autor,String titulo,String formato,String localizacion){
        this.autor = autor;
        this.titulo = titulo;
        this.localizacion = localizacion;
        this.formato = formato;
    }

    public String getAutor() {
        return autor;
    }

    public String getFormato() {
        return formato;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
