package tek.api.utility;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;


public class YamlPropertyReader {
    private static YamlPropertyReader readYamlFiles;

    //encapsulated property
    private HashMap propertyMap;

    //private Constructor. required file Path.
    private YamlPropertyReader(String filePath) throws FileNotFoundException {
        //File Input stream of a yaml file.
        InputStream fis = getFileInputStream(filePath);
        Yaml yaml = new Yaml();
        this.propertyMap = yaml.load(fis);
    }
    //method to return an instance of this class.
    public static YamlPropertyReader getInstance(String filePath) throws FileNotFoundException {
        if (readYamlFiles == null) {
            return new YamlPropertyReader(filePath);
        }

        return readYamlFiles;
    }

    //getter for the encapsulated property.
    public HashMap getProperty(String key) {
        return (HashMap) this.propertyMap.get(key);
    }

    private InputStream getFileInputStream(String filePath) throws FileNotFoundException {
        return getClass().getClassLoader().getResourceAsStream("env-config.yml");
    }

}
