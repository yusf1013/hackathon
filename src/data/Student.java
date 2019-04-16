package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private String name, fatherName, motherName, roll, number, nid, pass;

    public Student(String name, String fatherName, String motherName, String roll, String number, String nid, String pass) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.roll = roll;
        this.number = number;
        this.nid = nid;
        this.pass=pass;
        //Variables.studentDetail.put(name, this);

    }

    public ArrayList<String> getStrings()
    {
        ArrayList<String> al = new ArrayList<>();
        al.add(name);
        al.add(roll);
        al.add(fatherName);
        al.add(motherName);
        al.add(number);
        al.add(nid);
        return al;
    }

    public String getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getNumber() {
        return number;
    }

    public String getNid() {
        return nid;
    }

    public String getPass() {
        return pass;
    }
}
