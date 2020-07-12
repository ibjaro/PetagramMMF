package mx.unam.petagrammmf.pojo;

import java.io.Serializable;

public class Mascota implements Serializable {
    private int foto;
    private String nombre_mascota;
    private int cantidad_like;

    public Mascota(int foto, String nombre_mascota, int cantidad_like)
    {
        this.foto = foto;
        this.nombre_mascota = nombre_mascota;
        this.cantidad_like = cantidad_like;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public int getCantidad_like() {
        return cantidad_like;
    }

    public void setCantidad_like(int cantidad_like) {
        this.cantidad_like = cantidad_like;
    }
}
