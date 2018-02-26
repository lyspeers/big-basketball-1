package net.danielallison.FileRead;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class easyFR {
    private Scanner scan1;
    private ArrayList<String> linedData;
    private ArrayList<String[]> splitData;
    private ArrayList<ArrayList> fieldedData;
    private String splitRegex;

    public easyFR(String filePath) throws IOException {
        scan1 = new Scanner(new File(filePath));
        init();
    }

    public easyFR(File file) throws IOException {
        scan1 = new Scanner(file);
        splitRegex = "\t";
        init();
    }

    public easyFR(File file, String splitRegex) throws IOException {
        scan1 = new Scanner(file);
        this.splitRegex = splitRegex;
        init();
    }

    /**
     * This method is used to account for different constructors of the easyFR class.
     * Its function is to initialize all of the state variables of the easyFR class.
     */
    private void init() {
        linedData = new ArrayList<>(); //Initializes state variable linedData as ArrayList
        splitData = new ArrayList<>(); //Initializes state variable splitData as Arraylist
        fieldedData = new ArrayList<>(); //Initializes state variable fieldedData as Arraylist
        if(splitRegex==null) {
            splitRegex="\t";
        }

        while (scan1.hasNextLine()) {
            linedData.add(scan1.nextLine()); //Adds each line of input from file to ArrayList linedData
        }

        //System.out.println(linedData.get(2)); //DEBUG LINE

        ListIterator lDataIterator = linedData.listIterator();
        while (lDataIterator.hasNext()) {
            String tempString = (String)lDataIterator.next();
            //System.out.println(tempString);

            splitData.add(tempString.split(splitRegex)); //Adds a new String[] to ArrayList splitData. This new array contains a string for each data column.
        }

        /*
        This for-loop generates the contents of fieldedData from the contents of splitData.
        This creates a two-dimensional ArrayList fieldedData, which contains the imported data in the form of dataField objects.
        The usage of dataField objects is done to allow for potentially easier sorting of data in the future.
         */
        for(int i = 0; i < splitData.size(); i++) {
            addFieldedLine(splitData.get(i));
        }

    }

    /**
     * This method generates a dataField object and attempts to determine the type of the dataField object.
     *
     * @param item String input that is used to generate a dataField object.
     * @return A dataField object generated from the String parameter item.
     */
    private dataField processEntry(String item) {
        if(typeDetector.isInteger(item)) {
            return new dataField(Integer.parseInt(item));
        } else if(typeDetector.isInteger(item)) {
            return new dataField(Double.parseDouble(item));
        } else {
            return new dataField(item);
        }

    }

    /**
     * This method creates an ArrayList in fieldedData using data from splitData.
     *
     * @param strings The contents of this parameter are used to fill fieldedData.
     */
    private void addFieldedLine(String[] strings) {
        fieldedData.add(new ArrayList());
        for(String obj : strings) {
            fieldedData.get(fieldedData.size()-1).add(processEntry(obj));
        }
    }

    /**
     * Used to extract data from an easyFR object.
     *
     * @param index1 Line number
     * @param index2 Column number
     * @return dataField object from indices provided
     * @throws IndexOutOfBoundsException
     */
    public dataField read(int index1, int index2) throws IndexOutOfBoundsException {
        return (dataField)fieldedData.get(index1).get(index2);
    }

    /**
     * Used to extract a line of data from an easyFR object.
     *
     * @param index1 Line number
     * @return ArrayList containing dataField objects from the line selected
     */
    public ArrayList<ArrayList> readLine(int index1) {
        return fieldedData.get(index1);
    }
}

class typeDetector
{
    /**
     * Checks if a String can be converted to type Integer.
     *
     * @param item Input string to be tested.
     * @return True if item can be converted, False if item cannot be converted.
     */
    protected static boolean isInteger(String item) {
        try {
            Integer.parseInt(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if a String can be converted to type Double.
     *
     * @param item Input string to be tested.
     * @return True if item can be converted, False if item cannot be converted.
     */
    protected static boolean isDouble(String item) {
        try {
            Double.parseDouble(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}