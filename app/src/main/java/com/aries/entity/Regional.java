package com.aries.entity;

public class Regional {
    private String nombreRegional;
    private int codRegional;

    public Regional(String nombreRegional, int codRegional){
        this.nombreRegional = nombreRegional;
        this.codRegional = codRegional;
    }

    public String getNombreRegional() {
        return nombreRegional;
    }

    public void setNombreRegional(String nombreRegional) {
        this.nombreRegional = nombreRegional;
    }

    public int getCodRegional() {
        return codRegional;
    }

    public void setCodRegional(int codRegional) {
        this.codRegional = codRegional;
    }
}
