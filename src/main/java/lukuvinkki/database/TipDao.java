package lukuvinkki.database;

import lukuvinkki.domain.Tip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipDao implements Dao<Tip, Integer> {

    private String databaseAddress;

    public TipDao(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }


    public void addTip(Tip tip) throws SQLException {
        //if (title.equals("") || title.trim().length() == 0 ) {
          //  return;}
        //Tip tip = new Tip(title, author, url, description);
        try ( Connection conn = DriverManager.getConnection(databaseAddress);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Tip(title, author, url, description) VALUES ( ?, ?, ?, ? )") ) {
            
            stmt.setString(1, tip.getTitle());
            stmt.setString(2, tip.getAuthor());
            stmt.setString(3, tip.getUrl());
            stmt.setString(4, tip.getDescription());
            stmt.executeUpdate();
        }
    }

    @Override
    public Tip findOne(Integer id) throws SQLException {
        Tip tip = null;
        try ( Connection conn = DriverManager.getConnection(databaseAddress);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tip WHERE Tip.id = ?") ) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tip = new Tip(rs.getString("title"), rs.getString("author"), rs.getString("url"), rs.getString("description"));
                    tip.setId(id);
                }
            }
            
        }
        return tip;
    }

    @Override
    public List<Tip> findAll() throws SQLException {
        List<Tip> tips = new ArrayList<>();
        try ( Connection conn = DriverManager.getConnection(databaseAddress);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Tip");
            ResultSet rs = stmt.executeQuery() ) {
            
            while (rs.next()) {
                Tip tip = new Tip(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("url"), rs.getString("description"));
                tips.add(tip);
            }
            
        }
        return tips;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
