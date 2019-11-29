//	CS6560 		- File System Simulator Project
//	Instructor	- Professor Farzan Roohparvar
//	10/19/2017
//	Sam Portillo

package os;

import java.io.Serializable;

/**
 *  The Directory class implements Serializable in order to save its state.
 *  The Directory class represents one entry in a directory,
 *  that can be used to point to another directory (block) or a data file.
 *  @author Sam Portillo
 */
public class Directory implements Serializable
{
    private int id;
    private char TYPE;			// File type        →   F = Free, D = Directory, U = User Data
    private String NAME;
    private int LINK;
    private int SIZE;
    private int TOTALSIZE;
    private int state;			// Current state    →   0 = closed, 1 = create, 2 = open

    /**
     * The Directory constructor creates an directory entry that will be used to point to
     * either another directory or a data block.
     * @author Sam Portillo
     */
    Directory()
    {
        state = 0;
        LINK = 0;
        TYPE = 'F';
        NAME = "";
        SIZE = 0;
    }

    //  14

    /**
     * @author Sam Portillo
     * @return int → id of directory entry
     */
    public int getId() {
        return id;
    }

    /**
     * @author Sam Portillo
     * @param id → int is used to set the directory entry id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @author Sam Portillo
     * @return char → returns the file type.  U → user data file or D → directory
     */
    public char getTYPE() {
        return TYPE;
    }

    /**
     * @author Sam Portillo
     * @param TYPE → char is to set the file type.
     */
    public void setTYPE(char TYPE) {
        this.TYPE = TYPE;
    }

    /**
     * @author Sam Portillo
     * @return String → get name of directory entry.
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * @author Sam Portillo
     * @param NAME → String sets the name of the directory entry.
     */
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    /**
     * @author Sam Portillo
     * @return int → is the pointer of the sector.
     */
    public int getLINK() {
        return LINK;
    }

    /**
     * @author Sam Portillo
     * @param LINK → int sets the pointer of the sector.
     */
    public void setLINK(int LINK) {
        this.LINK = LINK;
    }

    /**
     * @author Sam Portillo
     * @return int → gets the size of the file.
     */
    public int getSIZE() {
        return SIZE;
    }


    /**
     * @author Sam Portillo
     * @param SIZE → sets the size of the file.
     */
    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    /**
     * @author Sam Portillo
     * @return int → get the total size of a file by summing
     * all linked sectors.
     */
    public int getTOTALSIZE() {
        return TOTALSIZE;
    }

    /**
     * @author Sam Portillo
     * @param TOTALSIZE → int sets the total size.
     */
    public void setTOTALSIZE(int TOTALSIZE) {
        this.TOTALSIZE = TOTALSIZE;
    }

    /**
     * @author Sam Portillo
     * @return int → returns the state of the sector.
     */
    public int getState() {
        return state;
    }

    /**
     * @author Sam Portillo
     * @param state int → sets the state of the sector.
     */
    public void setState(int state) {
        this.state = state;
    }
}

//  42