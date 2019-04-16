package data;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Variables {

    public static ArrayList<String> adminLogin = new ArrayList<>(), studentLogin = new ArrayList<>();
    public static Map<String, String> adminDetail = new TreeMap<>();
    public static Map<String, Student> studentDetail = new TreeMap<>();
    private static String uName, uPass;
    public static int index=0;
    public static boolean paid=false;
    public static double balance=1000;


    private static ArrayList<Student> studentArrayList = new ArrayList<>();

    public static void addStudent(Student student) {
        studentArrayList.add(student);
        studentDetail.put(student.getName(), student);
    }

    public static void setStudentArrayList(ArrayList<Student> studentArrayList) {
        Variables.studentArrayList = studentArrayList;
    }

    public static ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public static String getuName() {
        return uName;
    }

    public static String getuPass() {
        return uPass;
    }

    public static void setuName(String Name) {
        uName = Name;
    }

    public static void setuPass(String Pass) {
        uPass = Pass;
    }
}
