package com.Etrial;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encriptar {
    public static void main(String[] args) {
        var codigo = new BCryptPasswordEncoder();
        System.out.println("sofia (123): " + codigo.encode("123"));
        System.out.println("roda (456): " + codigo.encode("456"));
        System.out.println("aaron (789): " + codigo.encode("789"));
    }
}
