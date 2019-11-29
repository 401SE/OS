package os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * The Help class provides syntax & semantics
 * for a given command.
 * @author Sam Portillo
 */
public class Help
{
    String help;
    static char cr = '▼';           //  Alt 31

    /**
     * It opens a help file & returns a filtered help result.
     * @author Sam Portillo
     * @param help is the user term that filters results for
     *             a specific command.
     *
     */
    public static String print( String help )
    {
        String sss = "";
        File f = new File("src/os/Help.txt");

        if ( !f.exists() )
        {
            System.out.println("File not found.");
            return "File not found.";
        }

        Scanner scan = null;
        try{
            scan = new Scanner( f );
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }


        int i = 0;
        while (scan.hasNext() )
        {
            String record = scan.nextLine();
            String s [] = record.split("\t");
            if ( !s[0].equals( help ) )
                continue;

            switch( i++ )
            {
                case 0:
                    System.out.printf("%d %20s %s\n", i, "Command Name:", s[1]);
                    sss = String.format("%d %20s %s▼", i, "Command Name:", s[1]);
                    break;

                case 1:
                    System.out.printf   ("%d %20s %s\n", i, "Syntax:", s[1]);
                    sss += String.format("%d %20s %s▼", i, "Syntax:", s[1]);
                    break;

                case 2:
                    System.out.printf   ("%d %20s\n",   i, "Help Description:");
                    sss += String.format("%d %20s ▼", i, "Help Description:");

                default:
                    System.out.printf(   "%22s %s\n", " ", s[1] );
                    sss += String.format("%22s %s▼", " ", s[1] );
            }
        }
        if (i == 0)
        {
            System.out.println("Search term not found.");
            sss = "Search term not found.";
        }

        scan.close();
        return sss;
    }
}

    //  32