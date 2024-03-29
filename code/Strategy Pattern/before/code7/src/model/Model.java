package model;

import model.packages.Package;
import utility.observer.subject.LocalSubject;
import viewModel.ScheduleViewModel;
import viewModel.StudentListViewModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends LocalSubject<String, Package> {

    String getClassAndSchool(Student student);

    void setSchoolName(String name);
    String getSchoolName();

    ArrayList<Class> getAllClasses();

    ArrayList<Student> getAllStudents();
    ArrayList<String> getUnassignedStudents();

    ArrayList<Student> getStudentsByClass(String className);

    ArrayList<Teacher> getAllTeachers();

    ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date);
    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date);
    ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date);

    Class getClassWith(Student student);
    Class getClassByName(String name);

    Student getStudentBy(String id) throws IllegalArgumentException;

    Teacher getTeacherBy(String id) throws IllegalArgumentException;

    Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException;
    Lesson getLesson(String lessonID) throws IllegalArgumentException;
    Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException;

    LessonData getLessonData(Lesson lesson, Student student);

    boolean changeMotive(String studentId, String lessonID, String motive);
    boolean changeAbsence(String studentID, String lessonID, boolean absence);
    boolean changeLesson(String lessonID, String topic, String contents, String homework);

    void addClass(String className) throws IllegalArgumentException, SQLException;
    void removeClass(String className) throws IllegalAccessException, SQLException;

    void addStudent(String studentName, String studentID) throws IllegalArgumentException;
    void removeStudent(String studentID);

    void addStudentToClass(String studentID, String className) throws IllegalArgumentException;
    void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException;

    void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException;
    void removeTeacher(String studentID);
}
