package com.ironyard.services;

import com.ironyard.data.GroceryItem;
import com.ironyard.data.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rohanayub on 1/31/17.
 */
public class GroceryItemTest {

    @Before
    public void truncateBefore(){
        System.out.println("truncateBefore");
        DbService ds = new DbService();
        ds.truncate("helloworld.grocery_item");
    }

    @After
    public void truncateAfter(){
        System.out.println("truncateAfter");
        DbService ds = new DbService();
        ds.truncate("helloworld.grocery_item");
    }
    @Test
    public void getAllItemsFromGroceryList() throws Exception {
        // TODO .. First insert stuff

        GroceryService gs = new GroceryService();

        GroceryItem item1 = new GroceryItem(2.99, "Milk", "Food", 17);
        GroceryItem item2 = new GroceryItem(3.99, "Dounut", "Food", 2 );
        //Pet pet3 = new Pet("Redford",14,"Stephen" ,"Yellow", "Bird" );

        gs.save(item1);
        gs.save(item2);

        List<GroceryItem> found = gs.getAllItemsFromGroceryList();
        System.out.println("getAllPetsFromDatabase");
        assertEquals("Found unexpected number of rows.",2, found.size());
    }

    @Test
    public void getGroceryById() throws Exception {
        // TODO .. First insert stuff

        //System.out.println("getPetByIdFromDatabase");
        GroceryService gs = new GroceryService();
        GroceryItem item1 = new GroceryItem(7,"Hotdog" ,"Food", 10);
        gs.save(item1);
        //fetch pet by name
        GroceryItem foundByItemName = gs.getGroceryItem("Hotdog");
        //fetch pet ID number
        long fetchMeAgainByThisID = foundByItemName.getId();
        //fetch the pet from the ID number saved
        GroceryItem found = gs.getGroceryById(fetchMeAgainByThisID);
        //System.out.println("getPetByIdFromDatabase");

        assertEquals("incorrect id",fetchMeAgainByThisID, found.getId());
    }

    @Test
    public void getItemName() throws Exception {
        // TODO .. Fist insert stuff
        System.out.println("getPetByNameFromDatabase");
        GroceryService gs = new GroceryService();
        gs.save (new GroceryItem(2.84,"Water","Drink",1));
        GroceryItem found = gs.getGroceryItem("Water");
        assertNotNull(found);
        assertEquals("incorrect id","Water", found.getName());
    }

    @Test
    public void saveToDatabase() throws Exception {
        System.out.println("saveToDatabase");
        GroceryService gs = new GroceryService();
        GroceryItem saveMe = new GroceryItem(7.42,"Ice Cream","Dessert",4);
        //
        gs.save(saveMe);

        // fetch by name
        GroceryItem found = gs.getGroceryItem("Ice Cream");
        assertEquals("Name 404",saveMe.getName(), found.getName());
        assertEquals("getPrice 404",saveMe.getPrice(), found.getPrice(), .0001);
        assertEquals("getCategory 404",saveMe.getCategory(), found.getCategory());
        assertEquals("getAisle 404",saveMe.getAisle(), found.getAisle());
        //assertEquals("getOwner 404",saveMe.getOwner(), found.getOwner());

    }
}