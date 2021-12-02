package com.jpro.hellojpro;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jpro.webapi.JProApplication;
import com.jpro.webapi.WebAPI;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * Created by TB on 25.02.16.
 */
public class HelloJProFXMLController implements Initializable
{
    public Label platformLabel;
    @FXML
    protected StackPane root;

    @FXML
    protected Node logo;

    @FXML
    protected JFXComboBox cbxCountry;

    @FXML
    private FlowPane flowPane;

    @FXML
    private JFXTextField txtProgramingLanguage;

    protected JProApplication jProApplication;

    protected ParallelTransition pt;

    public Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        platformLabel.setText(String.format("Platform: %s", WebAPI.isBrowser() ? "Browser" : "Desktop"));
        cbxCountry.getItems().addAll("Pakistan", "Australia", "Bangladesh", "India", "Turkey");
    }

    protected void initLogoAnimation(Node logo)
    {
        ScaleTransition st = new ScaleTransition(Duration.millis(1000), logo);
        st.setByX(-0.5);
        st.setByY(-0.5);
        st.setAutoReverse(true);
        st.setCycleCount(Animation.INDEFINITE);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), logo);
        ft.setToValue(0.5);
        ft.setAutoReverse(true);
        ft.setCycleCount(Animation.INDEFINITE);
        logo.setOpacity(1);

        pt = new ParallelTransition(st, ft);
        pt.play();

        if(WebAPI.isBrowser()) {
            jProApplication.getWebAPI().addInstanceCloseListener(() -> {
                pt.stop();
            });
        }
    }


    public void init(JProApplication jProApplication)
    {
        this.jProApplication = jProApplication;
        initLogoAnimation(this.logo);

        programingLanguageAutoList();

        flowPane.prefWrapLengthProperty().bind(stage.widthProperty());
    }

    private void programingLanguageAutoList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("C");
        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("C#");
        list.add("Visual Basic");
        list.add("JavaScript");
        list.add("PHP");
        list.add("SQL");
        list.add("Groovy ");

        TextFields.bindAutoCompletion(txtProgramingLanguage, list);
    }
}
