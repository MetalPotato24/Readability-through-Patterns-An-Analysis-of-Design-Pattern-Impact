package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {
    private School school;
    //TODO change <Object, Object>
    private PropertyChangeHandler<Object,Object> property;

    public ModelManager() {
        school = new School();
        this.property = new PropertyChangeHandler<>(this);
        createDummy();
    }

    public void createDummy() {
        setSchoolName("DaVinci");
        StudentList studentList = school.getStudentList();
        studentList.addStudent(new Student("Ion Caus", "308234"));
        studentList.addStudent(new Student("Denis", "4338234"));
        studentList.addStudent(new Student("Max", "308415"));

        ClassList classList = school.getClassList();
        Class class1 = new Class("12 C");
        Class class2 = new Class("11 A");

        classList.addClass(class1);
        classList.addClass(class2);

        class1.getStudents().addStudent(studentList.getAllStudents().get(0));
        class1.getStudents().addStudent(studentList.getAllStudents().get(1));

        class2.getStudents().addStudent(studentList.getAllStudents().get(2));

        class1.getSchedule().addLesson(
                new Lesson(new Teacher("Steffen", "SVA", "325632"),
                        new Date(2021,4,29),
                        new Time(1,1,1),
                        new Time(2,2,2),
                        "Math",
                        "Logarithms",
                        "305A",
                        "ex. 3,4,5 pag 6."
                )
        );
    }

    @Override
    public ArrayList<Class> getAllClasses() {
        return school.getClassList().getAllClasses();
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return school.getStudentList().getAllStudents();
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() throws NullPointerException {
        return null;
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) {
        return theClass.getSchedule().getLessonBy(date);
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        return getClassWith(student).getSchedule().getLessonBy(date);
    }
    @Override
    public Class getClassWith(Student student) {
        return school.getClassList().getClassWith(student);
    }
    @Override
    public Class getClassByName(String name) {
        return school.getClassList().getClassByName(name);
    }

    @Override
    public Student getStudentBy(String id) throws IllegalArgumentException {
        return school.getStudentList().getStudentByID(id);
    }


    @Override
    public void addClass(String className) {
        school.getClassList().addClass(new Class(className));

        property.firePropertyChange("Class", null, className);
    }

    @Override
    public void removeClass(String className) {
        school.getClassList().removeClass(className);

        property.firePropertyChange("Class", null, className);
    }

    @Override
    public void addStudent(String studentId, String studentName) {
        //TODO by Ion 10/05  Add student? pass student name and id? or StudentObject
        school.getStudentList().addStudent(new Student(studentName, studentId));

        property.firePropertyChange("Student", studentId, studentName);
    }

    @Override
    public void removeStudent(String studentID) {
        school.getStudentList().removeStudent(studentID);

        property.firePropertyChange("Student", null, studentID);
    }

    @Override
    public String getClassAndSchool(Student student) {
        return getClassWith(student).getClassName() + ", " + getSchoolName();
    }

    @Override
    public void setSchoolName(String name) {
        school.setName(name);
    }

    @Override
    public String getSchoolName() {
        return school.getName();
    }



    @Override
    public boolean addListener(GeneralListener<Object, Object> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Object, Object> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }
}
