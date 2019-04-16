package sample;

import data.Student;
import data.Variables;
import guiElements.server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("student.arraylist"));
            Variables.setStudentArrayList((ArrayList<Student>) objIn.readObject());
            objIn = new ObjectInputStream(new FileInputStream("student.map"));
            Variables.studentDetail= (Map<String, Student>) objIn.readObject();
            System.out.println(Variables.getStudentArrayList().size());
        }catch (Exception e) {

        }

        Parent root = FXMLLoader.load(getClass().getResource("../guiElements/LogIn.fxml"));
        primaryStage.setTitle("Student System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            System.out.println("This");
            try {
                ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream( "student.arraylist"));
                objout.writeObject(Variables.getStudentArrayList());
                objout = new ObjectOutputStream(new FileOutputStream( "student.map"));
                objout.writeObject(Variables.studentDetail);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
