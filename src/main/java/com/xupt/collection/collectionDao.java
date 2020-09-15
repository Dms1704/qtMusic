package com.xupt.collection;

import com.xupt.domain.collection;
import com.xupt.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class collectionDao {
    public ArrayList<collection> getAllinfo(){
        DBConn obj = new DBConn();
        Connection conn = obj.getConnection();
        ArrayList<collection> ccc = new ArrayList<>();
        String sql = "SELECT id,songname,author,url from collection";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            rs.last();
            rs.beforeFirst();
            while (rs.next()){
                collection col = new collection();
                col.setId(rs.getInt("id"));
                col.setSongname(rs.getString("songname"));
                col.setAuthor(rs.getString("author"));
                col.setUrl(rs.getString("url"));
                ccc.add(col);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return ccc;
    }
    public String collectionExist(String Songname){

        String songname = "";

        String sql="select songname from collcetion where songname=?";

        try {
            DBConn db = new DBConn();
            PreparedStatement pst = db.getConnection().prepareStatement(sql);

            pst.setString(1,Songname);

            ResultSet rst = pst.executeQuery();

            if(rst.next()){

                songname=rst.getString(Songname);

            }

        } catch (SQLException e)

        {

            e.printStackTrace();

        }
        return songname;

    }
}

