package it.unibo.oop.lab.mvcio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 
 */
public class Controller {
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
    private static  File file = new File(PATH + "output.txt");

    public static File getFile() {
        return file;
    }


    public static void setFile(final File filen) {
        file = filen;
    }



    public static String getPath() {
        return file.getAbsolutePath();
        //return this.PATH;
    }
    public static void writeOnFile(final String str) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            w.write(str);
        } catch (final IOException e) {
            System.out.println("Eccezione");
        }
    }




    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}
