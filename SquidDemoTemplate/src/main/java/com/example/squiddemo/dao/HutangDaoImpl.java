/**
 * @author : 1972008 Adrian Octavius
 */

package com.example.squiddemo.dao;

import com.example.squiddemo.entity.Hutang;
import com.example.squiddemo.entity.Player;
import com.example.squiddemo.util.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HutangDaoImpl implements DaoService<Hutang>{
    @Override
    public List<Hutang> fetchAllList() throws SQLException, ClassNotFoundException {
        List<Hutang> hutangs = new ArrayList<>();
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "SELECT h.id AS id, h.PemberiUtang AS PemberiUtang, h.Jumlah AS Jumlah, p.id AS Player_id FROM hutang h INNER JOIN player p ON h.Player_id = p.id ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Player player = new Player();
                        player.setId(rs.getInt("Player_id"));

                        Hutang hutang= new Hutang();
                        hutang.setId(rs.getInt("id"));
                        hutang.setPemberiUtang(rs.getString("PemberiUtang"));
                        hutang.setJumlah(rs.getInt("Jumlah"));
                        hutang.setPlayer_id(player);
                        hutangs.add(hutang);
                    }
                }
            }
        }
        return hutangs;
    }

    @Override
    public int addData(Hutang hutang) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "INSERT INTO hutang(PemberiUtang,Jumlah,Player_id) VALUES(?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, hutang.getPemberiUtang());
                ps.setDouble(2, hutang.getJumlah());
                ps.setInt(3, hutang.getPlayer_id().getId());
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

    @Override
    public int deleteData(Hutang hutang) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySqlConnection.createConnection()) {
            String query = "DELETE FROM hutang WHERE  id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, hutang.getId());
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

    @Override
    public int updateData(Hutang hutang) throws ClassNotFoundException, SQLException {
        return 0;
    }
}
