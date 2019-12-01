//	CS6560 		- File System Simulator Project
//	Instructor	- Professor Farzan Roohparvar
//	10/19/2017
//	Sam Portillo


package os;

import os.Directory;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The Sectors class serves as a parent class to DirectoryBlock and DataBlock.
 *  Each Sector will either contain a directory block or a data block.
 *  @author Sam Portillo
 *
 */
public class Sectors implements Serializable
{
    private int SectorType;             // 0 = Free, 1 = Directory Block, 2 = Data Block
    private int id;	                    // id = index within Array
    private static int count = 0;
    private int BACK;
    private int FRWD;
    private int FREE;
    private char FILLER;
    private ArrayList<Directory> dir = new ArrayList<Directory>();


    // Data Block
    private boolean free = false;
    private String USER_DATA;           // Change to char 504

    /**
     * The Sectors constructor creates a sector that will contain
     * either a directory block or data block.
     * @author Sam Portillo
     */
    Sectors()
    {
        count++;
    }

    /**
     * @author Sam Portillo
     * Used as an abstract method.
     */
    void init()
    {

    }

    //  14

    /**
     * @author Sam Portillo
     * @return int: get sector type.
     */
    public int getSectorType() {
        return SectorType;
    }

    /**
     * @author Sam Portillo
     * @param sectorType int: sets the sector type.
     */
    public void setSectorType(int sectorType) {
        SectorType = sectorType;
    }

    /**
     * @author Sam Portillo
     * @return int: gets the id.
     */
    public int getId() {
        return id;
    }

    /**
     * @author Sam Portillo
     * @param id int: sets the id of the sector.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @author Sam Portillo
     * @return int: get the count of sectors currently in use.
     */
    public static int getCount() {
        return count;
    }

    /**
     * @author Sam Portillo
     * @param count int: reset the count to 0.
     */
    public static void setCount(int count) {
        Sectors.count = count;
    }

    /**
     * @author Sam Portillo
     * @return int: get the previous sector pointer.
     */
    public int getBACK() {
        return BACK;
    }

    /**
     * @author Sam Portillo
     * @param BACK int: set the previous sector pointer.
     */
    public void setBACK(int BACK) {
        this.BACK = BACK;
    }

    /**
     * @author Sam Portillo
     * @return int: return the next index.
     */
    public int getFRWD() {
        return FRWD;
    }

    /**
     * @author Sam Portillo
     * @param FRWD int: sets the next pointers index.
     */
    public void setFRWD(int FRWD) {
        this.FRWD = FRWD;
    }

    /**
     * @author Sam Portillo
     * @return get int: the number of free sectors.
     */
    public int getFREE() {
        return FREE;
    }

    /**
     * @author Sam Portillo
     * @param FREE int: sets the number of free sectors.
     */
    public void setFREE(int FREE) {
        this.FREE = FREE;
    }

    /**
     * @author Sam Portillo
     * @return char: get the character used to fill unused allocated user data space.
     */
    public char getFILLER() {
        return FILLER;
    }

    /**
     * @author Sam Portillo
     * @param FILLER char: to fill unused allocated user data space.
     */
    public void setFILLER(char FILLER) {
        this.FILLER = FILLER;
    }

    /**
     * @author Sam Portillo
     * @return ArrayList of Directory.
     */
    public ArrayList<Directory> getDir() {
        return dir;
    }


    /**
     * @author Sam Portillo
     * @param d Directory: to add to current list of directories.
     */
    public void addDir(Directory d)
    {
        this.dir.add( d );
    }

    /**
     * @author Sam Portillo
     * @return boolean: to check if a sector is free.
     */
    public boolean isFree() {
        return free;
    }

    /**
     * @author Sam Portillo
     * @param free boolean: sets the status of the sector to free.
     */
    public void setFree(boolean free) {
        this.free = free;
    }

    /**
     * @author Sam Portillo
     * @return String: get user data.
     */
    public String getUSER_DATA() {
        return USER_DATA;
    }

    /**
     * @author Sam Portillo
     * @param USER_DATA String: sets the user data file.
     */
    public void setUSER_DATA(String USER_DATA) {
        this.USER_DATA = USER_DATA;
    }
}


//  54