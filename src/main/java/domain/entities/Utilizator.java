package domain.entities;

import domain.Rol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UTILIZATOR")
public class Utilizator {

    @Id
    @Column(name = "UUID", unique = true, nullable = false)
    private String uuid;
    @Column(name = "ROL")
    private Rol rol;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PAROLA")
    private String parola;

    public Utilizator(String uuid, Rol rol, String username, String parola) {
        this.uuid = uuid;
        this.rol = rol;
        this.username = username;
        this.parola = parola;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
