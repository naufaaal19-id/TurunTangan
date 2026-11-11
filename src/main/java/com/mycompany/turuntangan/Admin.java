package com.mycompany.turuntangan;

public class Admin extends Pengguna {
    public Admin(String nama, String email) {
        super(nama, email);
    }

    @Override
    public String getPeran() { 
        return "Admin"; 
    }
}