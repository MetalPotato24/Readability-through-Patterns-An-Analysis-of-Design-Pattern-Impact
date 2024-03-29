package model;

import java.util.ArrayList;

public class ClassList {
    private ArrayList<Class> classes;

    public ClassList() {
        this.classes = new ArrayList<>();
    }

    public void addClass(Class aClass) {
        classes.add(aClass);
    }

    public void removeClass(Class aClass) {
        classes.remove(aClass);
    }

    public void removeClass(String className) {
        this.classes.removeIf(aClass -> aClass.getClassName().equals(className));
    }

    public ArrayList<Class> getAllClasses() {
        return classes;
    }

    public Class getClassWith(Student student) {
        for (Class aClass: classes) {
            for (Student aStudent: aClass.getStudents().getAllStudents()) {
                if (aStudent.equals(student)) {
                    return aClass;
                }
            }
        }
        throw new IllegalArgumentException("This student is part of no classes");
    }

    public Class getClassByName(String name) {
        for (Class aClass: classes) {
            if (aClass.getClassName().equals(name)) {
                return aClass;
            }
        }
        throw new IllegalArgumentException("No such class with the name: "+ name);
    }
}
