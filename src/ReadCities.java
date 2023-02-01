import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class ReadCities {

    private static JFrame myFrame = new JFrame("States in comboBox");
    private static JComboBox states;
    private static JComboBox cities;
    private static JButton addCities;

    static HashMap <String,LinkedHashSet<String>> stateslist;

    public static void main(String[] args) {

        stateslist = new HashMap<String, LinkedHashSet<String>>();
        readFile();
        getCities();


        String[] state = stateslist.keySet().toArray(new String[0]);
        Arrays.sort(state);
        states = new JComboBox(state);


        cities = new JComboBox();
        addCities=new JButton("Load for cities");

        addCities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cities.removeAllItems();

                for (String city:
                        stateslist.get((String) states.getSelectedItem())) {
                    cities.addItem(city);
                }
            }
        });

        addCities.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });

        cities = new JComboBox();

        myFrame.add(states);
        myFrame.add(addCities);
        myFrame.add(cities);
        myFrame.setSize(250,450);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new FlowLayout());
        myFrame.setVisible(true);

    }

    private static void getCities(){
        try {
            FileInputStream reader = new FileInputStream("src/cities.txt");
            String city;
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/cities.txt"));
            while ((city = bufferedReader.readLine()) != null ) {
                String [] data = city.split("[|]");
                stateslist.get(data[2]).add(data[0]);
            }

            reader.close();

        } catch (Exception b) {
            JOptionPane.showMessageDialog(myFrame,"An error has occured while reading the file.");
        }
    }

    private static void readFile() {

        try {
            FileInputStream reader = new FileInputStream("src/states.txt");
            String state;
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/states.txt"));
            while ((state = bufferedReader.readLine()) != null ) {
                stateslist.put(state,new LinkedHashSet<String>());

            }

            reader.close();




        } catch (Exception e) {
            JOptionPane.showMessageDialog(myFrame,"An error has occured while reading the file.");
        }
    }


}