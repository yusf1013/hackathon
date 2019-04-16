package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    @FXML
    RadioButton studentRadioButton, adminRadioButton;
    @FXML
    TextField username;
    @FXML
    PasswordField passwordField;

    ArrayList<String> adminLogin = new ArrayList<>(), studentLogin = new ArrayList<>();
    Map<String, String> adminDetail = new TreeMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminLogin.add("admin");
        adminDetail.put("admin", "admin123");
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
    private void loginButtonAction()
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
            alert.setTitle("Field cannot be empty!!");
            alert.showAndWait();
        }
        if(!(adminRadioButton.isSelected() || studentRadioButton.isSelected()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select Account Type");
            alert.showAndWait();
        }

        if(adminRadioButton.isSelected() && adminLogin(uName, password))
        {
            Stage stage = (Stage) adminRadioButton.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                stage.setTitle("Student System");
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public boolean findAdmin(String uName)
    {
        for(String s:adminLogin) {
            if(s.equals(uName))
                return true;
        }
        return false;
    }

    public boolean userAdmin(String uName)
    {
        for(String s:adminLogin) {
            if(s.equals(uName))
                return true;
        }
        return false;
    }

    @FXML
    private void exitButtonAction()
    {
        System.exit(0);
    }

    private boolean adminLogin(String uName, String password)
    {
        boolean loginSuccessful=false;

        if(findAdmin(uName))
        {
            if(adminDetail.get(uName).equals(password))
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
}
