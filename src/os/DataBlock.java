//	CS6560 		- File System Simulator Project
//	Instructor	- Professor Farzan Roohparvar
//	10/19/2017
//	Sam Portillo

package os;

/**
 *
 *  The DataBlock class is a Sector class that contains user data.
 * @author Sam Portillo
 */
public class DataBlock extends Sectors
{

    /**
     * The DataBlock constructor creates a Sector that is used for containing user data.
     * @author Sam Portillo
     * @param freeSector is an index that represents a pointer to a sector used for user data.
     */
    DataBlock( int freeSector )
    {
        //SectorType = 2;
        setSectorType( 2 );
        //id = freeSector;
        setId( freeSector );
        //USER_DATA = "";				// f.USER_DATA = f.USER_DATA.substring(0, pointer) + dataString;
        setUSER_DATA( "" );
        //free = false;
        setFree( false );
    }
}
