
         package com.ironyard.services;

         import com.ironyard.data.GroceryItem;


         import java.sql.*;
         import java.util.ArrayList;
         import java.util.List;

/**
 * Created by rohanayub on 1/31/17.
 */
public class GroceryService {
    /**
     * Get Pet that matches the given id
     * @param id
     * @return
     */
    public GroceryItem getGroceryById(long id){
        GroceryItem found = null;
        Connection con = null;
        DbService dbService = new DbService();
        try {
            con = dbService.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM helloworld.grocery_item where gro_id= "+id);
            while (rs.next()) {
                found = getGroceryItem(rs);
            }
        }catch(Throwable t){
            t.printStackTrace();
        } finally {
            if(con !=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return found;
    }

    public GroceryItem save(GroceryItem aGroceryToSave){
        Connection con = null;
        try {
            DbService dbService = new DbService();
            con = dbService.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO helloworld.grocery_item " +
                            "(gro_name, gro_aisle, gro_price, gro_cat)  " +
                            "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            // set values
            ps.setString(1,aGroceryToSave.getName());
            ps.setInt(2, aGroceryToSave.getAisle());
            ps.setDouble(3,aGroceryToSave.getPrice());
            ps.setString(4,aGroceryToSave.getCategory());
            ps.execute();

            // populate id that was generated from DB
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                aGroceryToSave.setId(rs.getLong("gro_id"));
            }
            //
            rs.close();
            ps.close();
        }catch(Throwable t){
            t.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return aGroceryToSave;
    }

    /**
     * Get Pet By the name given from database
     * @return
     */
    public GroceryItem getGroceryItem(String aItemName){
        GroceryItem found = null;
        try {
            DbService dbService = new DbService();
            Connection con = dbService.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM helloworld.grocery_item where gro_name= ?");
            ps.setString(1,aItemName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                found = getGroceryItem(rs);
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
        return found;
    }

    public List<GroceryItem> getAllItemsFromGroceryList(){
        List<GroceryItem> foundGroceryItem = new ArrayList<GroceryItem>();
        try {
            DbService dbService = new DbService();
            Connection con = dbService.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM helloworld.grocery_item");

            while (rs.next()) {
                foundGroceryItem.add(getGroceryItem(rs));
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
        return foundGroceryItem;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    private GroceryItem getGroceryItem(ResultSet rs) throws SQLException {
        long id = rs.getLong("gro_id");
        double price = rs.getDouble("gro_price");
        String name = rs.getString("gro_name");
        String cat = rs.getString("gro_cat");
        int aisle = rs.getInt("gro_aisle");
        return new GroceryItem(id,price,name,cat, aisle);
    }
}
