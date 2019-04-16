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

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StudentController implements Initializable {

    @FXML
    AnchorPane editPane;
    @FXML
    Button payMoney, getCert;

    int index=-1;
    DataOutputStream dout = null;
    DataInputStream din = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        index=Variables.index;
        populateTable();
        getCert.setDisable(true);
        System.out.println("Welcome to client. Write stop for close");

        Socket s= null;
        try {
            s = new Socket("localhost",3333);
             dout=new DataOutputStream(s.getOutputStream());
             din = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


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
        Variables.index=Variables.getStudentArrayList().size()-1;
    }

    public void populateTable()
    {
        if(!Variables.getStudentArrayList().isEmpty()){
            ArrayList<String> al = Variables.getStudentArrayList().get(index).getStrings();
            for(int i=0; i<al.size(); i++)
            {
                ((javafx.scene.control.TextField)editPane.getChildren().get(i)).setText(al.get(i));
            }
        }
    }

    @FXML
    public void payMoney() throws IOException {
        //getCert.setDisable(false);

        /*new ThreadRead(din,s).start();
        new ThreadWrite(dout,s).start();*/
        dout.writeUTF("YES");
        dout.writeUTF("YES");
        String s=din.readUTF();

        System.out.println("In here " + s);
        if(s.equals("YES")){
            Variables.paid=true;
            Variables.balance-=100;
            getCert.setDisable(false);
        }
    }

    @FXML
    public void getCert()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Certificate")));
            Student s=Variables.getStudentArrayList().get(index);
            writer.write("This is to certify that "+s.getName()+"(Roll: " + s.getRoll() +")"+ " " + ", child of Mr."+s.getFatherName()+" and Mrs."+s.getMotherName()
                    +" is a student of Institute of Information Technology, DU.\n");
            writer.write("I have known him personally and have found him to have good moral character. I wish him every success in life.\n" +
                    "\nSigned:\n\n\n\nDirector,\nInstitute of Information Technology, DU.\n");
            writer.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setHeaderText("File has been created");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logOut(ActionEvent event)
    {
        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
            stage.setTitle("Student System");
            stage.setScene(new Scene(root));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        }
    }

}

class ThreadRead extends Thread{
    DataInputStream din;
    Socket socket;
    public ThreadRead(DataInputStream din, Socket s)
    {
        this.din=din;
        socket=s;
    }
    public void run()
    {
        while(true){
            try {
                this.sleep(100);
                String response = din.readUTF();
                System.out.println(response);
            } catch (IOException e) {
                System.out.println("Connection has been closed!");
                break;
                //e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadWrite extends Thread{
    DataOutputStream dout;
    Socket socket;
    public ThreadWrite(DataOutputStream dout, Socket s)
    {
        this.dout=dout;
        socket=s;
    }
    public void run()
    {
        while(true){
            try {
                this.sleep(100);
                Scanner in = new Scanner(System.in);
                dout.writeUTF(in.nextLine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Connection has been closed!");
                break;
            }
        }
    }
}
