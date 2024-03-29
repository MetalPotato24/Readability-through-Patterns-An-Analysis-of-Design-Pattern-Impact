package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.View;
import viewModel.SchoolViewModel;

import java.util.HashMap;
import java.util.Optional;

public class SchoolStrategyContext {
    private HashMap<String, SchoolStrategy> strategies;
    private String key;

    //TODO maybe add controller if it is needed
    public SchoolStrategyContext(SchoolViewModel viewModel) {
        strategies = new HashMap<>();
        key = viewModel.getTabSelectedProperty();

        SchoolStrategy classStrategy = new ClassStrategy(viewModel);
        SchoolStrategy studentStrategy = new StudentStrategy(viewModel);


        strategies.put("Classes", classStrategy);
        strategies.put("Students", studentStrategy);
    }

    public View add() {
        return strategies.get(key).add();
    }

    public void remove() {
        strategies.get(key).remove();
    }
}
