package com.example.cmedicale.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private HBox homeContent;
    @FXML
    private HBox ClientContent;
    @FXML
    private HBox RvContent;
    @FXML
    private HBox MedecinContent;
    @FXML
    private VBox Content;

    @FXML
    private Button rv;

    @FXML
    private Button client;

    @FXML
    private Button medecin;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private Button saveToDBButton;

    @FXML
    private Button saveToLocalButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RvContent.setVisible(false);
        RvContent.setManaged(false);
        ClientContent.setVisible(false);
        ClientContent.setManaged(false);
        MedecinContent.setVisible(false);
        MedecinContent.setManaged(false);

    }
    @FXML
    private void GoToHome(ActionEvent event) {
        Content.getChildren().clear();
        Content.getChildren().add(homeContent);
    }
    @FXML
    private void GoToRv(ActionEvent event) {
        Content.getChildren().clear();
        RvContent.setVisible(true);
        RvContent.setManaged(true);
        Content.getChildren().add(RvContent);
    }
    @FXML
    private void GoToClient(ActionEvent event) {
        Content.getChildren().clear();
        ClientContent.setVisible(true);
        ClientContent.setManaged(true);
        Content.getChildren().add(ClientContent);
    }
    @FXML
    private void GoToMedecin(ActionEvent event) {
        Content.getChildren().clear();
        MedecinContent.setVisible(true);
        MedecinContent.setManaged(true);
        Content.getChildren().add(MedecinContent);
    }
}