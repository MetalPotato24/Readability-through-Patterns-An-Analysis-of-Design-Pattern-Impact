package view;

public enum View {
    LOGIN_VIEW("LoginView.fxml"),
    SCHOOL_VIEW("SchoolView.fxml"),
    SCHEDULE_VIEW("ScheduleView.fxml"),
    CLASS_VIEW("ClassView.fxml"),
    STUDENT_VIEW("StudentView.fxml");


    private final String fxmlFile;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

}
