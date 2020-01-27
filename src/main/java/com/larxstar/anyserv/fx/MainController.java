package com.larxstar.anyserv.fx;

import com.larxstar.anyserv.strats.impl.GUIStrat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML Label                 lblBankDesc;

    @FXML
    public void initialize() {
        System.out.println("controller initialized");
    }

    @FXML void onAction(ActionEvent e) {
        GUIStrat.INSTANCE.frame.dispose();
    }

    public void onClose() {
        GUIStrat.INSTANCE.root.postGuiSetup(
                new UIConfig(
                )
        );
    }

    public static class UIConfig {
        public boolean test;

        public UIConfig() {
            GUIStrat.INSTANCE.root.args = "test "+test;
        }

    }

}
