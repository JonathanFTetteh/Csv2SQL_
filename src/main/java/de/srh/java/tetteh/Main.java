package de.srh.java.tetteh;

import de.srh.java.tetteh.database.MySQLConnection;
import de.srh.java.tetteh.fileReader.CSVReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    private static Properties properties = new Properties();
    public static void main(String[] args) {


        CSVReader.readCsv();
        List<Locations> locations = CSVReader.getListLocation();
        propertyReader();
        MySQLConnection mySQLConnection = new MySQLConnection(properties);
        mySQLConnection.getConnection();
        mySQLConnection.createDB();
        mySQLConnection.createTables();
        mySQLConnection.addCountries(locations);
        mySQLConnection.addCities(locations);

    }
                public static void propertyReader(){
                    final String PATH = "src/main/resources/config.properties";
                    System.out.println(PATH);
                    try {
                        InputStream input = new FileInputStream(PATH);


                        properties.load(input);
                        System.out.println(properties.get("host"));


                    }
                    catch (Exception exception){
                        System.out.println("Error: Cannot read properties!\n" + exception.getMessage());
                        System.exit(-1);


                    }
                }
}

