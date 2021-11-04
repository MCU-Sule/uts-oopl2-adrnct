/**
 * @author : 1972008 Adrian Octavius
 */

package com.example.squiddemo.dao;

import com.example.squiddemo.entity.Player;
import com.example.squiddemo.util.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl implements DaoService<Player>{
    @Override
    public List<Player> fetchAllList() throws SQLException, ClassNotFoundException {
        List<Player> players = new ArrayList<>();
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "SELECT * from player ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Player player= new Player();
                        player.setId(rs.getInt("id"));
                        player.setNama(rs.getString("Nama"));
                        player.setUmur(rs.getInt("Umur"));
                        player.setKeahlian(rs.getString("Keahlian"));
                        players.add(player);
                    }
                }
            }
        }
        return players;
    }

    @Override
    public int addData(Player player) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "INSERT INTO player(Nama, Umur, Keahlian) VALUES(?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, player.getNama());
                ps.setInt(2, player.getUmur());
                ps.setString(3, player.getKeahlian());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(Player player) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "DELETE FROM player WHERE  id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, player.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Player player) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "UPDATE player SET Nama=?, Umur =?, Keahlian =? WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, player.getNama());
                ps.setInt(2, player.getUmur());
                ps.setString(3, player.getKeahlian());
                ps.setInt(4,player.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;

    }
}
