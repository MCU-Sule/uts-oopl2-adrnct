/**
 * @author : 1972008 Adrian Octavius
 */

package com.example.squiddemo.entity;

public class Player {
    private int id;
    private String Nama;
    private int Umur;
    private String Keahlian;

    public Player() {
    }

    public Player(int id, String nama, int umur, String keahlian) {
        this.id = id;
        Nama = nama;
        Umur = umur;
        Keahlian = keahlian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getUmur() {
        return Umur;
    }

    public void setUmur(int umur) {
        Umur = umur;
    }

    public String getKeahlian() {
        return Keahlian;
    }

    public void setKeahlian(String keahlian) {
        Keahlian = keahlian;
    }

    @Override
    public String toString() {
        return getNama();
    }
}
