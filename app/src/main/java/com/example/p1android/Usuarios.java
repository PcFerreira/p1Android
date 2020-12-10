package com.example.p1android;

import java.io.Serializable;

public class Usuarios implements Serializable {
    int id;
    String email;
    String addcity;

    public Usuarios(int id,  String email, String addcity) {
        this.id = id;
        this.email = email;
        this.addcity = addcity;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "EMAIL ='" + email + '\'' +
                " -  CIDADE ='" + addcity + '\'' +
                '}';
    }
}
