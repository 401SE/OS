//
//	CS6560 		- File System Simulator Project
//	Instructor	- Professor Farzan Roohparvar
//	10/19/2017
//	Sam Portillo
//	~ 11 (16 hour) days to develop
//	Developed with Eclipse Oxygen EE

//	100 Sectors available to be used for a combination
//	of directory blocks or data blocks
//	Blocks are created dynamically
//	Only 1 folder or 1 file is created at a time
//	Data is written with String class
//	Only 1 open file at a time.
//	Character storage is 1 character / byte.
//	Utilizes Linked List of Free Sectors.
//  Save & load modules
//	Command Line Terminal option
//	Terminal mode -> Do not use quotes for writing
//	Project is set for Testing
//	int dataBlockLength = 10;		// Testing size

//	Required Commands:
//	create, open, seek, read, write, close, dir

//	Additional commands:
//	save	Save file structure & free sector list
//	load	Load file structure & free sector list
//	free	peek head of Free Sector List
//	exit

//	Terminal Mode Example:
//	create D Directory1
//	create D Directory1/Directory2
//	create U Directory1/Directory2/One					// Create filename = One
//	write 25 Be careful with what you hear, you may go blind	// Write first 25 character/bytes to file
//	close												// Close opened file
//  11/01/2019 Revisions:
//      Modified main to create an instance of itself.
//      Multi-threaded terminal.


package os;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 *  The FileSystem class has a main where it initializes a file system
 *  then starts a user command line interface, shell to simulate an
 *  Operating System.
 *  The FileSystem class has a Sectors array to store all memory.
 *  The FileSystem class has a Directory Stack to keep track of open files.
 *  @author Sam Portillo
 */
public class FileSystem
{
    static String sss;          //  Socket Server String
    static ArrayList<Sectors> sectors = new ArrayList<Sectors>();
    static Stack<Directory> openFiles = new Stack<Directory>();
    static Queue<Integer> freeSectors = new LinkedList<Integer>();
    static char cr = '▼';

    static char openCreate; // 'O' or 'C'
    static char openMode;
    static int pointer = 0;
    static int base;
    static int offset;
    static String fileString;
    static int id;
    static int gBlock;
    static int x;
    static boolean feedback = true;
    static int dataBlockLength = 10;			// Set for testing, Standard 504


    /**
     * The FileSystem constructor is run within a multi threaded
     * environment.
     * @author Sam Portillo
     */
    public FileSystem()
    {
        init();
    }

    /**
     *  The main method calls init() and terminal().
     *  @author Sam Portillo
     * @param args is not used.
     */
    public static void main(String[] args)
    {
        FileSystem fs = new FileSystem();
        fs.terminal();
    }


    /**
     *  The init() method initializes the file system.
     *  Sets the FileSystem's first sector ( block 0 )
     *  as a directory block.
     *  @author Sam Portillo
     */
    private static void init()
    {
        for (int x = 0; x < 100; x++)
            freeSectors.add(x);

        // Set Sector 0 to the Root Directory
        Sectors block0 = new DirectoryBlock(freeSectors.poll());
        block0.setBACK( 0 );
        block0.setFRWD(0);
        block0.setFREE(1);
        sectors.add(block0);
    }


    /**
     * The newDirectoryBlock() method gets the next free sector id
     * from the freeSectors array.
     * Creates a new sector as a Directory Block.
     * @author Sam Portillo
     * @return  Returns the id of this sector.
     */
    private static int newDirectoryBlock()
    {
        Sectors sector = new DirectoryBlock(freeSectors.poll());
        sectors.add(sector);
        return sector.getId();
    }

    /**
     * The newDirectory method searches in a Directory block for
     * the first free entry.
     * @author Sam Portillo
     * @param block is the selected Directory Block to start searching.
     *              If this block is full & has a forwarding block then
     *              searching will be rereferenced in this new block.
     * @return The first free entry within a directory.
     */
    private static Directory newDirectory(int block)
    {
        do
            {
                for (Directory d : sectors.get(block).getDir() )
                {
                if (d.getTYPE() == 'F')
                    return d;
            }

            if (sectors.get(block).getFRWD() > 0)
                block = sectors.get(block).getFRWD();
            else {

                sectors.get(block).setFRWD( newDirectoryBlock() );
                sectors.get(sectors.get(block).getFRWD()).setBACK(block);
                block = sectors.get(block).getFRWD();
            }
        } while (block > 0); // while true
        return null; // Out of memory?
    }
    //  43


    /**
     * The create method is invoked with the create command.
     * It is used to create either a directory file or a user data file.
     * create a Directory file  →   create D name
     * create a User data file  →   create U name
     * Quotes are not used.
     * @author Sam Portillo
     * @param type is either D for Directory file or U for User data file.
     * @param name is the name of the file to be allocated.
     * @return int is the index of a Directory Block.
     */
    private static int create(char type, String name)
    {
        feedback = false;
        delete(name);
        feedback = true;

        int block = traverseFolders(name);
        if (block < 0)
            return -1;

        Directory d = newDirectory(block);

        String a[] = name.split("/");

        d.setTYPE( type );
        d.setNAME( a[a.length - 1] );
        if (type == 'D')
        {
            d.setLINK( newDirectoryBlock() );
            return d.getLINK();
        } else {
            openMode = 'O';
            openCreate = 'C';
            base = -1; // May not use this.
            offset = 0;
            pointer = 0;

            Sectors db = new DataBlock(freeSectors.poll());
            db.setFree(false); // May not need because delete block to free
            db.setSectorType(2); // 0 = Free, 1 = Directory Block, 2 = Data Block
            sectors.add(db);
            d.setLINK( db.getId() );
            openFiles.push(d);
            id = d.getLINK(); // Will be used later in other functions -- read, write
            return id;
        }
    }

        //  71

    /**
     * The traverseFolders method is a nonrecursive method used to search for a given directory name.
     * Starts searching from root directory, drilling down to subfolders
     * until folder not found (short circuit evaluation) or traversed to last subfolder.
     * @author Sam Portillo
     * @param name is the search String to find.
     * @return  int is the id of the sector where a match is found or
     *          if folder is not found then return -1.
     */
    private static int traverseFolders(String name)
    {
        int block = 0; // root = 0;
        boolean found = false;
        String a[] = name.split("/");
        for (int x = 0; x < a.length - 1; x++)
        {
            do {
                for (Directory d : sectors.get(block).getDir() )
                {
                    if (a[x].equals(d.getNAME() ) && d.getTYPE() == 'D')
                    {
                        found = true;
                        block = d.getLINK();
                        break;
                    }
                }

                if (!found && sectors.get(block).getFRWD() == 0) {
                    System.out.println("Folder Not Found");
                    return -1;
                }

                if (!found && sectors.get(block).getFRWD() > 0)
                    block = sectors.get(block).getFRWD();
            } while (!found); // Next Block

            found = false;
        }

        return block;
    }

    // 90

    /**
     * The open method has three modes.
     *      Input mode means that only READ and SEEK commands are permitted.
     *      Output mode means only WRITE commands are permitted.
     *      Update mode allows READ, WRITE, and SEEK commands.
     *      Associated with each open file is a pointer to the next byte to be
     *      read or written.  Opening a file for input or update places the
     *      pointer at the first byte of the file, while opening a file for output
     *      places the pointer at the byte immediately after the last byte of the file.
     * @author Sam Portillo
     * @param mode is [I, O, U] for Input, Output or Update.
     * @param name is the name of the User data file to be processed.
     * @return int gives a status of the operation. failure = -1, success = 1.
     */

    private static int open(char mode, String name) {
        // -1 = Error, 1 = Success
        int block = traverseFolders(name);
        if (block < 0)
            return -1;

        Directory d = findName(block, name);
        if (d == null)
            return -1;
        block = gBlock;

        id = d.getLINK(); // Will be used later in other functions -- read, write
        d.setState(2); // May need to delete block because it will be created dynamically
        openFiles.push(d);
        openMode = mode;
        // System.out.println("Set openMode " + openMode);
        openCreate = 'O';
        offset = 0;
        if (mode == 'I' || mode == 'U')
            base = -1;

        if (mode == 'O')
            base = 1;

        fileString = sectors.get(d.getLINK() ).getUSER_DATA(); // fileString is global needed for seek
        setPointer(base, offset, fileString);
        return 1;
    }

    // 111


    /**
     * The read method is invoked when the read command is used.
     * This command may only be used between an OPEN (in input or update mode) and
     * the corresponding CLOSE.  If possible, 'n' bytes of data should be read and
     * displayed.  If fewer than 'n' bytes remain before the end of file, then those
     * bytes should be read and displayed with a message indicating that the end
     * of file was reached.
     * @author Sam Portillo
     * @param n represents the number of bytes to be read.
     * @return String relays screen output to the socket server.
     */
    private static void read(int n)
    {
        if (openCreate != 'O')
        {
            sss = "File needs to be open to read.";
            System.out.println(sss);
            return;
        }

        if (openMode != 'I' && openMode != 'U')
        {
            sss = "Mode is not set to 'I' or 'U'.  Can not read file.";
            System.out.println(sss);
            return;
        }

        Directory d = openFiles.peek();
        Sectors db = sectors.get(d.getLINK() );

        int c = 0;			// print cursor
        String s = "";
        int i = 0;

        int x = 0;
        do {
            if (x++ > 0 )			// Circumvent priming
                db = sectors.get(db.getFRWD());

            if ( pointer < c + db.getUSER_DATA().length() )
            {
                if ( pointer - c > 0)
                    s = db.getUSER_DATA().substring(pointer - c);
                else
                    s = db.getUSER_DATA();

                i = n - c + pointer;

                if ( i > dataBlockLength)
                    i = dataBlockLength;

                if (i > s.length())
                    i = s.length();

                s = s.substring(0, i);
                //System.out.printf("  %d %d %s %d", db.id, db.SectorType, db.USER_DATA, d.SIZE);   // ?
                //System.out.printf("%d %d %s %n", db.id, db.SectorType, s);            //  09/30/2019
                System.out.printf("%s \n", s);
                sss += String.format("%s %c", s, cr);
            }

            c += dataBlockLength;

        } while (db.getFRWD() > 0  && n + pointer > c );

        if ( db.getFRWD() == 0 && n + pointer > c + s.length() - dataBlockLength )
        {
            sss += "<End of file>";
            System.out.println("<End of file>");
        }

        System.out.println("");
    }

    // 143

    /**
     * The setPointer method sets the file pointer to a new position
     * in a file.
     * @author Sam Portillo
     * @param base is the starting point from where an offset may
     *             be applied to a file pointer.
     *
     *              Possible values are { -1, 0, or 1 }:
     *            -1    →   Indicating the beginning of the file.
     *             0    →   The current position in the file.
     *             1    →   Indicates the end of the file.
     * @param offset is a signed integer indicating the number of bytes
     *               from the 'base' that the file pointer should be moved.
     * @param s is used to determine end of string.
     * @return String relays screen output to the socket server.
     */
    private static String setPointer(int base, int offset, String s) {
        System.out.println("offset " + offset );
        System.out.println("s → " + s );
        System.out.println("s.length" + s.length() );

        if (base == -1)
            pointer = offset;
        if (base == 0)
            pointer += offset;
        if (base == 1){
            pointer = s.length() + offset;
            System.out.println("pointer → " + pointer);
        }


        if (pointer < 0) {
            System.out.println("Negative pointer.  Out of bounds.");
        }

        if (pointer >= s.length() && openMode == 'I') {
            System.out.println("End of String");
        }

        if (pointer > s.length() && openMode == 'O') // ********** write
        {
            sss = "End of String";
            System.out.println("End of String");
        }

        return sss;
    }

    // 156


    /**
     * The write method is invoked by the write command.
     * The first 'n' data bytes of the 'dataString' is written to a file.
     * If the 'dataString' is less than 'n' bytes then
     * a filler character is used, such as a period.
     * If the disk is full and not possible to write 'n' bytes then the user
     * will receive a message.
     * @author Sam Portillo
     * @param n is the number of bytes to write.
     * @param dataString is the user data to write.
     * @return int Failure = -1, otherwise it will return the number of bytes
     * written.
     *
     */
    private static int write(int n, String dataString) {
        if (openMode != 'O' && openMode != 'U') {
            System.out.println("Output mode is not set.");
            return -1;
        }

        if (n < dataString.length())
            dataString = dataString.substring(0, n);

        if (n > dataString.length())
            dataString = appendBlanks(dataString, n);

        Sectors db1 = sectors.get(id); // Set from create or open
        String s = db1.getUSER_DATA().substring(0, pointer) + dataString;

        while (s.length() > dataBlockLength)
        {
            db1.setUSER_DATA( s.substring(0, dataBlockLength) );
            s = s.substring(dataBlockLength);
            Sectors db2 = new DataBlock(freeSectors.poll());
            db2.setSectorType(2); // 0 = Free, 1 = Directory Block, 2 = Data Block
            db2.setBACK( db1.getId() );
            sectors.add(db2);
            db1.setFRWD( db2.getId() );
            db1 = db2;
        }

        db1.setUSER_DATA( s );
        Directory d = openFiles.peek();
        d.setSIZE( s.length() );
        d.setTOTALSIZE( dataString.length() );
        return s.length();
    }


    //  180

    /**
     * The appendBlanks method is called from the write method to append
     * blanks at the end of the string in order to write n bytes.
     * @author Sam Portillo
     * @param s is the String that will have blanks appended.
     * @param n is the length of s after appending blanks.
     * @return String is the result of the given String after appending blanks.
     */

    private static String appendBlanks(String s, int n) {
        int y = n - s.length();
        for (int x = 0; x < y; x++)
            s += ".";

        return s;
    }

    // 185

    /**
     * The seek method is invoked by the shell.
     * Performs input validation.
     * Calls the setPointer method to set the file pointer
     * to a new position in a file.
     * @author Sam Portillo
     * @param base is the starting point from where an offset may
     *             be applied to a file pointer.
     *
     *              Possible values are { -1, 0, or 1 }:
     *            -1    →   Indicating the beginning of the file.
     *             0    →   The current position in the file.
     *             1    →   Indicates the end of the file.
     * @param offset is a signed integer indicating the number of bytes
     *               from the 'base' that the file pointer should be moved.
     */
    private static void seek(int base, int offset)
    {
        if (openCreate != 'O')
            System.out.println("File is not open --> Can not seek.");
        if (openMode != 'I' && openMode != 'U') {
            System.out.println("Mode is not set to 'I' or 'U'.  Can not seek file.");
            return;
        }

        setPointer(base, offset, fileString);
    }

    // 192

    /**
     * The close method is invoke by the shell.
     * It pops the last open file off the stack.
     * Resets the state of the file to close.
     * @author Sam Portillo
     */
    private static void close()
    {
        Directory d = openFiles.pop();
        d.setState( 0 );

        pointer = 0;
        openMode = ' ';
        openCreate = ' ';
        id = 0;
        gBlock = 0;
    }
    //  200


    /**
     * The delete method is invoked by the shell.
     * It traverses the path of folders.
     * Searches for & deletes the last folder or file.
     * @author Sam Portillo
     * @param name is the absolute path & folder or
     *             file to delete.
     * @return  returns -1 → failure
     *                   1 → success
     */
    private static int delete(String name) {
        int block = traverseFolders(name);
        if (block < 0)
            return -1;

        Directory d = findName(block, name);
        if (d == null)
            return -1;

        block = gBlock;

        do {
            d.setTYPE('F');
            freeSectors.add(d.getId() );
            block = sectors.get(block).getFRWD();
        } while (block > 0);

        return 1;
    }

    //  214


    /**
     * findName is a private method used to search
     * for a name in a directory block.
     * @author Sam Portillo
     * @param block the given directory block to search in.
     * @param name the given search term to match to a Directory
     *             entry.
     * @return Directory is the found directory entry that matched
     *          the search criteria otherwise returns null.
     */
    private static Directory findName(int block, String name)
    {
        // null = Not found, Directory = found
        String a[] = name.split("/");

        do {
            for (Directory d : sectors.get(block).getDir() ) {
                if (a[a.length - 1].equals(d.getNAME())) {
                    gBlock = block;
                    return d;
                }
            }
            block = sectors.get(block).getFRWD();

        } while (block > 0);

        if (feedback)
            System.out.println("Name not found");
        return null;
    }
    //  226


    /**
     * The dir method is invoked by the shell.
     * It lists the contents of the entire file system.
     * @author Sam Portillo
     * @return String relays screen output to the socket server.
     */
    private static String dir()
    {
        //free();           9/30/2019
        sss = "";
        x = 0;
        //System.out.println("block, level, SectorType, BACK, FRWD, FREE");
        return recursiveDir(0, 0);
    }

    //  229


    /**
     * The private method recursiveDir is called from dir.
     * It is a recursive method.
     * @author Sam Portillo
     * @param block is the current Directory block.
     * @param level is the depth of the path.
     */
    private static String recursiveDir(int block, int level)
    {
        //System.out.printf("%d %d %d %d %d %d %n", block, level, sectors.get(block).SectorType, sectors.get(block).BACK, sectors.get(block).FRWD, sectors.get(block).FREE);

        Sectors db; // data block
        int y = 0;
        int i = 0;
        int z = 0;

        do {

            for (Directory d : sectors.get(block).getDir() )
            {
                if (d.getTYPE() != 'F')
                {
                    z++;
                    for (int x = 0; x < level; x++)
                    {
                        System.out.print("     ");
                        sss += ".";
                    }

                    //System.out.printf(" %d %d %d %c %s %d %n", x, y++, d.state, d.TYPE, d.NAME, d.LINK);
                    sss += String.format("%s %c", d.getNAME(), cr );
                    System.out.printf("%s \n", d.getNAME() );
                }


                if (d.getTYPE() == 'D')
                {
                    recursiveDir(d.getLINK(), level + 1);
                }

         }

            block = sectors.get(block).getFRWD();
        } while (block > 0);

        x++;

        return sss;
    } // End recursivePrint

    //  249

    /**
     * The save method is invoked by the shell.
     * It saves the current state of the file system
     * as a serialized file.
     * @author Sam Portillo
     * @return void
     */
    private static void save()
    {
        Sectors sec = new Sectors();

        try {
            FileOutputStream fileOut = new FileOutputStream("data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sectors);
            out.writeObject(freeSectors);
            out.close();
            fileOut.close();
            System.out.println("Saved File Structure");
            sss = "Saved File Structure";

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //  261


    /**
     * The load() method is invoked by the shell.
     * It loads a serialized file to reset the file structure
     * to its last saved state.
     * @author Sam Portillo
     */
    private static void load() {
        try {
            FileInputStream fileIn = new FileInputStream("data.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sectors = (ArrayList<Sectors>) in.readObject();
            // static Queue<Integer> freeSectors = new LinkedList<Integer>();
            freeSectors = (LinkedList<Integer>) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("File Structure Loaded");
        sss = "File Structure Loaded";
    }
    //  272


    /**
     * The free method is invoked by the shell.
     * It prints the number of free sectors.
     * @author Sam Portillo
     */
    private static void free()
    {
        // Test free Sector List
        System.out.println( "Head = " + freeSectors.peek() );
        System.out.println("Number of free sectors " + freeSectors.size() );

        sss = "Head = " + freeSectors.peek()  + cr;
        sss += "Number of free sectors " + freeSectors.size();
    }

    //  275


    /**
     * The terminal method is invoked by the main() method.
     * It simulates a shell for single user.
     * @author Sam Portillo
     */
    public static void terminal()
    {
        String s;

        do
            {
            System.out.print("> ");

            Scanner scanner = new Scanner(System.in);
            s = scanner.nextLine();
            // System.out.println(s);
            String a[] = s.split(" ");

            switch ( a[0] )
            {
                case "create":
                    create(a[1].toCharArray()[0], a[2]);
                    break;

                case "open":
                    open(a[1].toCharArray()[0], a[2]);
                    break;

                case "seek":
                    seek(Integer.parseInt(a[1]), Integer.parseInt(a[2]));
                    break;

                case "read":
                    read(Integer.parseInt(a[1]));
                    break;

                case "write":
                    int i = s.indexOf(" ", 7);
                    write(Integer.parseInt(a[1]), s.substring(i + 1, s.length()));
                    break;

                case "close":
                    close();
                    break;

                case "delete":
                    delete(a[1]);
                    break;

                case "dir":
                    dir();
                break;

                case "save":
                    save();
                    break;

                case "load":
                    load();
                    break;

                case "free":
                    free();
                    break;

                case "help":
                    Help h = new Help();
                    h.print(a[1]);
                    break;


                case "exit":
                    System.out.println("Logged Out");
                    break;

                default:
                    System.out.println("Command not found");

            }
            } while (!s.equals("exit"));
    }






//  Socket Server Terminal

    /**
     * The terminal method is invoked by the main() method.
     * It simulates a shell for multi-user.
     * @author Sam Portillo
     * @param String s is passed from the Terminal class.
     */
    public static String socketTerminal(String s)
    {
        sss = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();

        String a[] = s.split(" ");

            switch ( a[0] )
            {
                case "create":
                    create(a[1].toCharArray()[0], a[2]);
                    break;

                case "open":
                    open(a[1].toCharArray()[0], a[2]);
                    break;

                case "seek":
                    seek(Integer.parseInt(a[1]), Integer.parseInt(a[2]));
                    break;

                case "read":
                    read(Integer.parseInt(a[1]));
                    break;

                case "write":
                    int i = s.indexOf(" ", 7);
                    write(Integer.parseInt(a[1]), s.substring(i + 1, s.length()));
                    break;

                case "close":
                    close();
                    break;

                case "delete":
                    delete(a[1]);
                    break;

                case "dir":
                    dir();
                    break;

                case "save":
                    save();
                    break;

                case "load":
                    load();
                    break;

                case "free":
                    free();
                    break;

                case "help":
                    Help h = new Help();
                    sss = h.print(a[1]);
                    break;

                case "Date" :
                    String date_String = dateFormat.format(date);
                    System.out.println(date_String);
                    sss = date_String;
                    break;

                case "Time" :
                    String time_String = timeFormat.format(date);
                    System.out.println(time_String);
                    sss = time_String;
                    break;

                case "exit":
                    System.out.println("<Logged Out>.");
                    sss = "<Logged Out.>";
                    break;

                default:
                    System.out.println("Command not found");
                    sss = "Command not found";
            }
//        } while (!s.equals("exit"));
        return sss + cr + ">";
    }

}


//  323