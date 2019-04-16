package guiElements;

import data.Student;
import data.Variables;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    @FXML
    AnchorPane createPane, editPane;


    int index=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
        editPane.setDisable(true);
    }

    @FXML
    private void createButton()
    {
        List list=createPane.getChildren();
        ArrayList<String> al = new ArrayList<>();
        for(Object o: list)
        {
            al.add(((TextField) o).getText().trim());
            if(al.get(al.size()-1).isEmpty())
            {
                al.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Field can't be empty");
                alert.showAndWait();
                return;
            }
        }

        Variables.addStudent(new Student(al.get(0),al.get(1),al.get(2),al.get(3),al.get(4),al.get(5), al.get(6)));

        al.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setHeaderText("Added");
        alert.showAndWait();

        populateTable();

    }

    @FXML
    public void edit()
    {

        List list=editPane.getChildren();
        ArrayList<String> al = new ArrayList<>();
        for(Object o: list)
        {
            al.add(((TextField) o).getText().trim());
            if(al.get(al.size()-1).isEmpty())
            {
                al.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Field can't be empty");
                alert.showAndWait();
                return;
            }
        }

        for(Student s: Variables.getStudentArrayList()) {
            if(al.get(3).equals(s.getRoll()))
                Variables.getStudentArrayList().remove(s);
        }

        Variables.addStudent(new Student(al.get(0),al.get(1),al.get(2),al.get(3),al.get(4),al.get(5), al.get(6)));
    }

    public void populateTable()
    {
        if(!Variables.getStudentArrayList().isEmpty()){
            ArrayList <String> al = Variables.getStudentArrayList().get(index).getStrings();
            for(int i=0; i<al.size(); i++)
            {
                ((javafx.scene.control.TextField)editPane.getChildren().get(i)).setText(al.get(i));
            }
        }
        System.out.println("POP " + Variables.getStudentArrayList().size());
    }

    @FXML
    public void next()
    {
        if(index<Variables.getStudentArrayList().size()-1)
            index++;
        populateTable();

    }

    @FXML
    public void pre()
    {
        if(index>0)
            index--;
        populateTable();
    }

    @FXML
    public void logOut(ActionEvent event)
    {
        System.out.println("Here");
        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("./LogIn.fxml"));
            stage.setTitle("Student System");
            stage.setScene(new Scene(root));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
