//	CS6560 		- File System Simulator Project
//	Instructor	- Professor Farzan Roohparvar
//	10/19/2017
//	Sam Portillo

package os;

import java.util.ArrayList;

/**
 * The DirectoryBlock class represents a directory that can
 * contain up to 32 entries.  These entries can either be a
 * directory or a file.
 * @author Sam Portillo
 *
 */
public class DirectoryBlock extends Sectors
{

    /**
     * The DirectoryBlock constructor creates a Sector that is used for containing
     * directory entries.
     * @param freeSector: int is used to id the DirectoryBlock.  The id is
     *            analogous to a pointer address in memory.
     * @author Sam Portillo
     */
    DirectoryBlock (int freeSector)
    {
        setSectorType( 1 );
        setId( freeSector );
        init();
    }


    /**
     * The init() method initializes a directory block to contain
     * 32 entries.  These entries can be either a directory name or
     * a file name.
     * @author Sam Portillo
     *
     */
    @Override
    void init()
    {
        //System.out.println("DirectoryBlock init");
        for( int x = 0; x < 32; x++)
        {
            Directory d = new Directory();
            d.setId(x);
            //dir.add( d );
            addDir( d );
        }
    }
}