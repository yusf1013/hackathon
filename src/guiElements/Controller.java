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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    RadioButton studentRadioButton, adminRadioButton;
    @FXML
    TextField username;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton, nigga;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Variables.adminLogin.add("admin");
        Variables.adminDetail.put("admin", "admin123");
        try {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("student.arraylist"));
            Variables.setStudentArrayList((ArrayList<Student>) objIn.readObject());
            objIn = new ObjectInputStream(new FileInputStream("student.map"));
            Variables.studentDetail= (Map<String, Student>) objIn.readObject();
            //System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("No data available 1");
        } catch (IOException e) {
            System.out.println("No data available 1");
        } catch (ClassNotFoundException e) {
            System.out.println("No data available 1");
        }
    }

    @FXML
    private void studentRadioButtonAction()
    {
        if(adminRadioButton.isSelected())
            adminRadioButton.setSelected(false);
    }

    @FXML
    private void adminRadioButtonAction()
    {
        if(studentRadioButton.isSelected())
            studentRadioButton.setSelected(false);
    }

    @FXML
    private void loginButtonAction(ActionEvent event)
    {
        String uName = username.getText().trim();
        String password = passwordField.getText();
        /*Pattern namePattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");
        Matcher matcher = namePattern.matcher(password);

        if(!matcher.matches() || uName.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setHeaderText("Passoword is too weak!");
            alert.setContentText("Password must:\n" +
                    " At least 8 chars and at most 15 chars\n" +
                    " Contains at least one digit\n" +
                    " Contains at least one lower alpha char and one upper alpha char\n" +
                    " Contains at least one char within a set of special chars (@#%$^ etc.)\n" +
                    " Does not contain space, tab, etc.\n");
            alert.showAndWait();
            return;
        }*/
        if(uName.isEmpty() || password.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Field cannot be empty!!");
            alert.showAndWait();
            return;
        }
        if(!(adminRadioButton.isSelected() || studentRadioButton.isSelected()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Select Account Type");
            alert.showAndWait();
            return;
        }

        if(adminRadioButton.isSelected() && adminLogin(uName, password))
        {


            try {
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
                stage.setTitle("Student System");
                stage.setScene(new Scene(root));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            }

        }

        if (studentRadioButton.isSelected() && studentLogin(uName, password))
        {
            try {
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                stage.setTitle("Student System");
                stage.setScene(new Scene(root));
                Variables.setuName(uName);
                Variables.setuPass(password);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }



    }

    public boolean findAdmin(String uName)
    {
        Variables.index=-1;
        for(String s:Variables.adminLogin) {
            Variables.index++;
            if(s.equals(uName))
                return true;
        }
        Variables.index=-1;
        return false;
    }

    public boolean userAdmin(String uName)
    {
        System.out.println("Useradmin " + Variables.studentDetail.size());

        for(Map.Entry e: Variables.studentDetail.entrySet()) {
            System.out.println(((Student)e.getValue()).getName());
            System.out.println(((Student)e.getValue()).getPass());
            if(((Student)e.getValue()).getName().equals(uName)) {
                System.out.println("This is True");
                return true;
            }
        }

        /*for(Student s:Variables.getStudentArrayList()) {
            System.out.println(s.getName());
            if(s.getName().equals(uName))
                return true;
        }*/
        System.out.println("WRong false");
        return false;
    }

    @FXML
    private void exitButtonAction(ActionEvent event)
    {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    private boolean adminLogin(String uName, String password)
    {
        boolean loginSuccessful=false;

        if(findAdmin(uName))
        {
            if(Variables.adminDetail.get(uName).equals(password))
                loginSuccessful=true;
        }

        if(!loginSuccessful) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No such data!!");
            alert.setHeaderText("Either username or password is wrong");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    private boolean studentLogin(String uName, String password)
    {
        boolean loginSuccessful=false;

        if(userAdmin(uName))
        {
            if(Variables.studentDetail.get(uName).getPass().equals(password))
                loginSuccessful=true;
        }

        System.out.println("Returning " + loginSuccessful);

        if(!loginSuccessful) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No such data!!");
            alert.setHeaderText("Either username or password is wrong");
            alert.showAndWait();
            return false;
        }

        System.out.println("Returning " + loginSuccessful);
        return true;
    }



}
