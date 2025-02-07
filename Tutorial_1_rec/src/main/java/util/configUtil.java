//data-driven framework.

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configUtil {
    //In this "ConfigUtils" file class, we will add a function that will read our property class.

    static public Properties getProperties(String filename){

        Properties prop=new Properties(); //This Property class object will read the required file,
        // save the required properties (eg; input data) from that file, and into this "prop" object,
        // and then return those properties from this function, to wherever we require it.

        try{
            File propertyfile=new File("src/test/resources/"+filename+".properties");

            if (propertyfile.exists()){

                prop.load(new FileInputStream(propertyfile));
            }
            else {

                System.out.println("File "+filename+" not found.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}
