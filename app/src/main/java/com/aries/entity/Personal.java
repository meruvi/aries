package com.aries.entity;

import java.io.Serializable;

public class Personal implements Serializable {
    //Atributos
    private int codPersonal;
    private int codCargo;
    private String  nombreUsuario;
    private String  contraseniaUsuario;
    private String nombresPersonal;
    private String apPaternoPersonal;
    private String apMaternoPersonal;
    private int codAreaEmpresa;
    private String nombreAreaEmpresa;

    //Getters and Setters
    public int getCodPersonal() {
        return codPersonal;
    }

    public void setCodPersonal(int codPersonal) {
        this.codPersonal = codPersonal;
    }

    public int getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public String getNombresPersonal() {
        return nombresPersonal;
    }

    public void setNombresPersonal(String nombresPersonal) {
        this.nombresPersonal = nombresPersonal;
    }

    public String getApPaternoPersonal() {
        return apPaternoPersonal;
    }

    public void setApPaternoPersonal(String apPaternoPersonal) {
        this.apPaternoPersonal = apPaternoPersonal;
    }

    public String getApMaternoPersonal() {
        return apMaternoPersonal;
    }

    public void setApMaternoPersonal(String apMaternoPersonal) {
        this.apMaternoPersonal = apMaternoPersonal;
    }

    public int getCodAreaEmpresa() {
        return codAreaEmpresa;
    }

    public void setCodAreaEmpresa(int codAreaEmpresa) {
        this.codAreaEmpresa = codAreaEmpresa;
    }

    public String getNombreAreaEmpresa() {
        return nombreAreaEmpresa;
    }

    public void setNombreAreaEmpresa(String nombreAreaEmpresa) {
        this.nombreAreaEmpresa = nombreAreaEmpresa;
    }
}
