package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.SchoolStrategy.SchoolStrategyContext;
import viewModel.ClassViewModel;
import viewModel.SchoolViewModel;
import viewModel.StudentViewModel;
import viewModel.TeacherViewModel;

import java.util.Optional;

public class SchoolViewController extends ViewController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Label schoolName;
    @FXML
    private Label errorLabel;

    @FXML
    private Button scheduleButton;
    @FXML
    private Button studentListButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    @FXML
    private TableView<ClassViewModel> classesTable;
    @FXML
    private TableColumn<ClassViewModel, String> classNameColumn;

    @FXML
    private TableView<StudentViewModel> allStudentsTable;
    @FXML
    private TableColumn<StudentViewModel, String> idStudentColumn;
    @FXML
    private TableColumn<StudentViewModel, String> studentColumn;
    @FXML
    private TableColumn<StudentViewModel, String> studentClassColumn;

    @FXML
    private TableView<TeacherViewModel> allTeachersTable;
    @FXML
    private TableColumn<TeacherViewModel, String> idTeacherColumn;
    @FXML
    private TableColumn<TeacherViewModel, String> teacherColumn;
    @FXML
    private TableColumn<TeacherViewModel, String> initialsColumn;

    private SchoolViewModel viewModel;


    public SchoolViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {

        viewModel = getViewModelFactory().getSchoolViewModel();

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    viewModel.setTabSelectedProperty(newVal.getText());
                    adjustViewButtons();
                }
        );
        tabPane.getSelectionModel().selectFirst();

        schoolName.textProperty().bindBidirectional(viewModel.schoolNameProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());


        // Classes Table
        classNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().classNameProperty()
        );
        classesTable.setItems(viewModel.getAllClasses());
        classesTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        // Students Table
        idStudentColumn.setCellValueFactory(
                cellData -> cellData.getValue().idProperty()
        );
        studentColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty()
        );
        studentClassColumn.setCellValueFactory(
                cellData -> cellData.getValue().classNameProperty()
        );
        allStudentsTable.setItems(viewModel.getAllStudents());
        allStudentsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        // Teachers Table
        idTeacherColumn.setCellValueFactory(
                cellData -> cellData.getValue().idProperty()
        );
        teacherColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty()
        );
        initialsColumn.setCellValueFactory(
                cellData -> cellData.getValue().initialsProperty()
        );
        allTeachersTable.setItems(viewModel.getAllTeachers());
        allTeachersTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    private void adjustViewButtons() {
        viewModel.clear();
        addButton.setVisible(true);
        removeButton.setVisible(true);
        switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
            case "Classes":
                addButton.setVisible(true);
                removeButton.setVisible(true);
                studentListButton.setVisible(true);
                scheduleButton.setVisible(true);
                break;
            //TODO 13/05 by DENNIS student and teacher schedule button should not be visible in the final admin view, only present for testing currently
            //TODO 13/05 by Ion maybe the admin wants to see the schedule for the teacher and student in case of problems???
            case "Students":
            case "Teachers":
                scheduleButton.setVisible(true);
                studentListButton.setVisible(false);
                break;
            case "Log":
                addButton.setVisible(false);
                removeButton.setVisible(false);
                studentListButton.setVisible(false);
                scheduleButton.setVisible(false);
                break;
            case "Admins":
                scheduleButton.setVisible(false);
                studentListButton.setVisible(false);
                break;
        }

    }

    @FXML
    private void editSchoolName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("School Name");
        dialog.setHeaderText("School Name");
        dialog.setContentText("Please enter your school name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isBlank()) {
            schoolName.setText(result.get());
            viewModel.setSchoolName(result.get());
        }
    }

    @FXML
    private void add() {
        getViewHandler().openView(
                new SchoolStrategyContext(viewModel).add()
        );
    }

    @FXML
    private void remove() {
        new SchoolStrategyContext(viewModel).remove();
        clearSelection();
    }

    public void clearSelection() {
        classesTable.getSelectionModel().clearSelection();
        allStudentsTable.getSelectionModel().clearSelection();
        allTeachersTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void viewSchedule() {
        if (viewModel.viewSchedule()) {
            getViewHandler().openView(View.SCHEDULE_VIEW);
        }
    }

    @FXML
    private void viewStudentList() {
        if(viewModel.viewStudentList())
            getViewHandler().openView(View.CLASS_STUDENT_LIST_VIEW);
    }
}
