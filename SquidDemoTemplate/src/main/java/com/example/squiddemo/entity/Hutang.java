/**
 * @author : 1972008 Adrian Octavius
 */

package com.example.squiddemo.entity;

public class Hutang {
    private int id;
    private String PemberiUtang;
    private double Jumlah;
    private Player Player_id;

    public Hutang() {
    }

    public Hutang(int id, String pemberiUtang, double jumlah, Player player_id) {
        this.id = id;
        PemberiUtang = pemberiUtang;
        Jumlah = jumlah;
        Player_id = player_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPemberiUtang() {
        return PemberiUtang;
    }

    public void setPemberiUtang(String pemberiUtang) {
        PemberiUtang = pemberiUtang;
    }

    public double getJumlah() {
        return Jumlah;
    }

    public void setJumlah(double jumlah) {
        Jumlah = jumlah;
    }

    public Player getPlayer_id() {
        return Player_id;
    }

    public void setPlayer_id(Player player_id) {
        Player_id = player_id;
    }



}
