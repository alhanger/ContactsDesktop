package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    TextField nameBar;
    @FXML
    TextField phoneBar;
    @FXML
    TextField emailBar;
    @FXML
    ListView listView;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(contacts);
    }

    public void addContact() {
        Contact contact = new Contact(nameBar.getText(), phoneBar.getText(), emailBar.getText());
        if (!nameBar.getText().equals("") && !phoneBar.getText().equals("") && !emailBar.getText().equals("")) {
            contacts.add(contact);
            writeToJson(contacts);
        }
        nameBar.setText("");
        phoneBar.setText("");
        emailBar.setText("");
    }

    public void removeContact() {
        Contact contact = (Contact) listView.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
        writeToJson(contacts);
    }

    static String readFile (String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String (fileContent);
        } catch (Exception e) {
            return null;
        }
    }

    static void writeFile (String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {

        }
    }

    static void writeToJson (ObservableList<Contact> contacts) {
        JsonSerializer serializer = new JsonSerializer();
        String output = serializer.serialize(contacts);

        writeFile("contacts.json", output);
    }

    static void parseContacts() {
        String content = readFile("contacts.json");

        JsonParser parser = new JsonParser();
        parser.parse(content, ObservableList<Contact> contacts);
    }
}
