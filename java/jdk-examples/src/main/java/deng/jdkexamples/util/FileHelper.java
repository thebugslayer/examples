//FileHelper
package deng.jdkexamples.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileHelper {
    public static interface LineAction {
        public void onLine(String line);
    }
    public static void eachLine(File file, LineAction action) {
        try {
            eachLine(new FileInputStream(file), action);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to read from file " + file, e);
        }
    }
    public static void eachLine(InputStream ins, LineAction action) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(ins));
            String line = null;
            while((line = reader.readLine()) != null) {
                action.onLine(line);
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to read from input stream.", e);
        } finally {
            if(reader != null)
                try {
                    reader.close();
                } catch(Exception e) {
                    throw new RuntimeException("Failed to close the reader.", e);
                } 
        }
    }
    
    public static interface PrinterWriterAction {
        public void onPrintWriter(PrintWriter writer);
    }    
    public static void withPrintWriter(File file, PrinterWriterAction action) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            action.onPrintWriter(writer);
            writer.flush();
        } catch(Exception e) {
            throw new RuntimeException("Failed to write to file " + file, e);
        } finally {
            if(writer != null)
                writer.close();
        }
    }
    
}
 