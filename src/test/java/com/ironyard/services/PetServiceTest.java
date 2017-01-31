package com.ironyard.services;

import com.ironyard.data.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 1/30/17.
 */
public class PetServiceTest {

    @Before
    public void truncateBefore(){
        System.out.println("truncateBefore");
        DbService ds = new DbService();
        ds.truncate("helloworld.pets");
    }

    @After
    public void truncateAfter(){
        System.out.println("truncateAfter");
        DbService ds = new DbService();
        ds.truncate("helloworld.pets");
    }
    @Test
    public void getAllPetsFromDatabase() throws Exception {
        // TODO .. First insert stuff

        PetService ps = new PetService();

        Pet pet = new Pet("Winston",7,"Rohan" ,"White", "Gorilla" );
        Pet pet2 = new Pet("Presley",4,"George" ,"Brown", "Dog" );
        Pet pet3 = new Pet("Redford",14,"Stephen" ,"Yellow", "Bird" );

        ps.save(pet);
        ps.save(pet2);
        ps.save(pet3);

        List<Pet> found = ps.getAllPetsFromDatabase();
        System.out.println("getAllPetsFromDatabase");
        assertEquals("Found unexpected number of rows.",3, found.size());
    }

    @Test
    public void getPetByIdFromDatabase() throws Exception {
        // TODO .. First insert stuff

        //System.out.println("getPetByIdFromDatabase");
        PetService ps = new PetService();
        Pet pet = new Pet("Winston",7,"Rohan" ,"White", "Gorilla");
        ps.save(pet);
        //fetch pet by name
        Pet foundByName = ps.getPetByName("Winston");
        //fetch pet ID number
        long fetchMeAgainByThisID = foundByName.getId();
        //fetch the pet from the ID number saved
        Pet found = ps.getPetById(fetchMeAgainByThisID);
        System.out.println("getPetByIdFromDatabase");

        assertEquals("incorrect id",fetchMeAgainByThisID, found.getId());
    }

    @Test
    public void getPetByNameFromDatabase() throws Exception {
        // TODO .. Fist insert stuff
        System.out.println("getPetByNameFromDatabase");
        PetService ps = new PetService();
        ps.save (new Pet("pepper",2,"Rohan","Brown","dog"));
        Pet found = ps.getPetByName("pepper");
        assertNotNull(found);
        assertEquals("incorrect id","pepper", found.getName());
    }

    @Test
    public void saveToDatabase() throws Exception {
        System.out.println("saveToDatabase");
        PetService ps = new PetService();
        Pet saveMe = new Pet("test",7,"skipper","brown","mouse");
        //
        ps.save(saveMe);

        // fetch by name
        Pet found = ps.getPetByName("test");
        assertEquals("Name 404",saveMe.getName(), found.getName());
        assertEquals("getColor 404",saveMe.getColor(), found.getColor());
        assertEquals("getType 404",saveMe.getType(), found.getType());
        assertEquals("getAge 404",saveMe.getAge(), found.getAge());
        assertEquals("getOwner 404",saveMe.getOwner(), found.getOwner());

    }
}