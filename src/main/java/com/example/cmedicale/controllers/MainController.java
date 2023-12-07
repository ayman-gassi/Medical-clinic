package com.example.cmedicale.controllers;

import com.example.cmedicale.entity.client;
import com.example.cmedicale.entity.creneau;
import com.example.cmedicale.entity.medecin;
import com.example.cmedicale.entity.rv;
import com.example.cmedicale.models.client.ImpClient;
import com.example.cmedicale.models.creneau.ImpCreneau;
import com.example.cmedicale.models.medecin.ImpMedecin;
import com.example.cmedicale.models.rv.ImpRv;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private HBox homeContent;
    @FXML
    private HBox RvContent;
    @FXML
    private HBox MedecinContent;
    @FXML
    private VBox Content;
    @FXML
    private TextField Prenom;

    @FXML
    private TextField Nom;
    @FXML
    private TextField DocNom;
    @FXML
    private TextField DocPrenom;
    @FXML
    private TextField Hdebut;
    @FXML
    private TextField Mdebut;
    @FXML
    private TextField Hfin;
    @FXML
    private TextField Mfin;

    @FXML
    private DatePicker RvDate;

    @FXML
    private ComboBox AvailableDoc;
    @FXML
    private Label alert;
    @FXML
    private Label DocAlert;
    @FXML
    private Label CltAlert;
    @FXML
    private ListView DocTable;
    @FXML
    private ListView ClientTable;
    private void DocDataView(){
        DocTable.getItems().clear();
        ImpMedecin med = new ImpMedecin();
        ImpCreneau Crn = new ImpCreneau();
        ArrayList<creneau> AllCreneaux = (ArrayList<creneau>) new ImpCreneau().getCreneaux("database");
        ArrayList<String> allDoc = new ArrayList<>();
        for (creneau m : AllCreneaux) {
            medecin currectMedecin = med.getMedecin("database",m.getId_medecin());
            allDoc.add(currectMedecin.getNom() + " " + currectMedecin.getPrenom() +"  |  " + m.getHdebut() + ":" + m.getMfin() + "   " + m.getHfin() + ":" + m.getMfin() );
        }
        for (String dat : allDoc) {
            Label label = new Label(dat);
            Image Delete = new Image("file:src/main/resources/com/example/cmedicale/img/delete.png");
            ImageView DeleteIcon = new ImageView(Delete);
            DeleteIcon.setFitWidth(16);
            DeleteIcon.setFitHeight(16);
            Button Deletebutton = new Button("Delete",DeleteIcon);
            Deletebutton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
            Image Update = new Image("file:src/main/resources/com/example/cmedicale/img/refresh.png");
            ImageView UpdateIcon = new ImageView(Update);
            UpdateIcon.setFitWidth(16);
            UpdateIcon.setFitHeight(16);
            Button Updatebutton = new Button("Update",UpdateIcon);
            Updatebutton.setStyle("-fx-background-color: #38c038; -fx-text-fill: white; -fx-font-weight: bold;");
            HBox hbox = new HBox(label, Deletebutton , Updatebutton);
            hbox.setSpacing(10);
            Deletebutton.setOnAction(event -> {
                String[] item = dat.split(" ");
                System.out.println("Button Detete clicked for: " + item[0]);
                medecin selectedMed = med.getMedecinByName("database",item[0],item[1]);
                med.supprimerMedecin("database",selectedMed.getId());
                DocAlert.setVisible(true);
                DocAlert.setText(selectedMed.getNom() + " " + selectedMed.getPrenom() + " a etait supprimer");
                DocAlert.setStyle("-fx-background-color: #38c038;");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> DocAlert.setVisible(false)));
                timeline.setCycleCount(1);
                timeline.play();
                DocDataView();
                DocComboBox(AvailableDoc);
            });
            Updatebutton.setOnAction(event -> {
                System.out.println("Button Update clicked for: " + dat);
                String[] item = dat.split(" ");
                medecin selectedMed = med.getMedecinByName("database",item[0],item[1]);
                creneau updateedCrn = Crn.getCreneau("database",Crn.getIdCreneauByDoc("database", selectedMed.getId()));
                Stage popupStage = new Stage();
                TextField[] textFields = new TextField[6];
                for (int i = 0; i < textFields.length; i++) {
                    textFields[i] = new TextField();
                }

                Image updt = new Image("file:src/main/resources/com/example/cmedicale/img/refresh.png");
                ImageView updateIcon = new ImageView(updt);
                updateIcon.setFitWidth(16);
                updateIcon.setFitHeight(16);
                Button Updateitem = new Button("Update",updateIcon);
                Updateitem.setStyle("-fx-background-color: #38c038; -fx-text-fill: white; -fx-font-weight: bold;");
                textFields[0].setPromptText("Nom");
                textFields[1].setPromptText("Prenom");
                textFields[2].setPromptText("Heur Debut");
                textFields[3].setPromptText("Minute Debut");
                textFields[4].setPromptText("Heur Fin");
                textFields[5].setPromptText("Minute Fin");
                HBox box1 = new HBox(textFields[2],  textFields[3]);
                HBox box2 = new HBox(textFields[4],  textFields[5]);
                VBox vbox = new VBox( textFields[0], textFields[1], box1, box2, Updateitem);
                vbox.setPadding(new Insets(10));
                Scene scene = new Scene(vbox, 300, 200);
                popupStage.setScene(scene);
                popupStage.setTitle("Update Information");
                Image iconImage = new Image(getClass().getResourceAsStream("/com/example/cmedicale/img/heart-rate.png"));
                popupStage.getIcons().add(iconImage);
                popupStage.setResizable(false);
                popupStage.show();
                textFields[0].setText(selectedMed.getNom());
                textFields[1].setText(selectedMed.getPrenom());
                textFields[2].setText(String.valueOf(updateedCrn.getHdebut()));
                textFields[3].setText(String.valueOf(updateedCrn.getMdebut()));
                textFields[4].setText(String.valueOf(updateedCrn.getHfin()));
                textFields[5].setText(String.valueOf(updateedCrn.getMfin()));
                Updateitem.setOnAction(e -> {
                    for (int i = 0; i < 6; i++) {
                        if (textFields[i].getText().isEmpty()){
                            DocAlert.setVisible(true);
                            DocAlert.setText("Fill All the Blanks");
                            DocAlert.setStyle("-fx-background-color: #ff4d4d;");
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> DocAlert.setVisible(false)));
                            timeline.setCycleCount(1);
                            timeline.play();
                        }
                        else {
                            selectedMed.setNom(textFields[0].getText());
                            selectedMed.setPrenom(textFields[1].getText());
                            med.modifierMedecin("database",selectedMed);
                            updateedCrn.setHdebut(Integer.parseInt(textFields[2].getText()));
                            updateedCrn.setMdebut(Integer.parseInt(textFields[3].getText()));
                            updateedCrn.setHfin(Integer.parseInt(textFields[4].getText()));
                            updateedCrn.setMfin(Integer.parseInt(textFields[5].getText()));
                            Crn.modifierCreneau("database",updateedCrn);
                            popupStage.close();
                            DocAlert.setVisible(true);
                            DocAlert.setText(item[0] + " " + item[1] + " a etait modifier");
                            DocAlert.setStyle("-fx-background-color: #38c038;");
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> DocAlert.setVisible(false)));
                            timeline.setCycleCount(1);
                            timeline.play();
                            DocDataView();
                            DocComboBox(AvailableDoc);

                        }
                    }

                });
            });
            DocTable.getItems().add(hbox);
        }
    }
    private void ClientTable(){
        ImpClient impClient = new ImpClient();
        ImpRv impRdv = new ImpRv();
        ImpMedecin impMdc = new ImpMedecin();
        ImpCreneau impCrn = new ImpCreneau();
        ClientTable.getItems().clear();
        ArrayList<rv> AllRv = (ArrayList<rv>) impRdv.getRVs("database");
        ArrayList<String> Clients = new ArrayList<>();
        for (rv m : AllRv) {
            client clt = impClient.getClient("database",m.getId_client());
            medecin mdc = impMdc.getMedecin("database",impCrn.getCreneau("database",m.getId_creaneau()).getId_medecin());
            Clients.add( clt.getNom()+ " " + clt.getPrenom() + " | " + m.getJour() +  " | Dr." + mdc.getNom() + mdc.getPrenom());
        }
        for (String dat : Clients) {
            Label label = new Label(dat);
            Image Delete = new Image("file:src/main/resources/com/example/cmedicale/img/delete.png");
            ImageView DeleteIcon = new ImageView(Delete);
            DeleteIcon.setFitWidth(16);
            DeleteIcon.setFitHeight(16);
            Button Deletebutton = new Button("Delete", DeleteIcon);
            Deletebutton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
            HBox hbox = new HBox(label, Deletebutton);
            hbox.setSpacing(10);
            ClientTable.getItems().add(hbox);
            Deletebutton.setOnAction(event -> {
                String[] item = dat.split(" ");
                System.out.println("Button Detete clicked for: " + item[0]);
                client selectedClt = impClient.getClientByName("database",item[0],item[1]);
                impClient.supprimerClient("database",selectedClt.getId());
                CltAlert.setVisible(true);
                CltAlert.setText("Rendez-Vous de ;"+ selectedClt.getNom() + " " + selectedClt.getPrenom() + " a etait supprimer");
                CltAlert.setStyle("-fx-background-color: #38c038;");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> CltAlert.setVisible(false)));
                timeline.setCycleCount(1);
                timeline.play();
                ClientTable();
            });
        }


    }
    private void DocComboBox(ComboBox Available){
        Available.getItems().clear();
        ImpMedecin med = new ImpMedecin();
        ArrayList<creneau> AllCreneaux = (ArrayList<creneau>) new ImpCreneau().getCreneaux("database");
        ArrayList<String> data = new ArrayList<>();
        for (creneau m : AllCreneaux) {
            medecin currectMedecin = med.getMedecin("database",m.getId_medecin());
            data.add(currectMedecin.getNom() + " " + currectMedecin.getPrenom() +" de " + m.getHdebut() + ":" + m.getMfin() + " a " + m.getHfin() + ":" + m.getMfin() );
        }
        Available.getItems().addAll(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DocComboBox(AvailableDoc);
        DocDataView();
        ClientTable();
        RvDate.setChronology(LocalDate.now().getChronology());
        RvDate.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate lastAllowedDate = LocalDate.now();
                        setDisable(date.isBefore(lastAllowedDate));
                    }
                });
        RvContent.setVisible(false);
        RvContent.setManaged(false);
        MedecinContent.setVisible(false);
        MedecinContent.setManaged(false);
    }
    @FXML
    private void GoToHome(ActionEvent event) {
        RvDate.setChronology(LocalDate.now().getChronology());
        RvDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate lastAllowedDate = LocalDate.now();
                setDisable(date.isBefore(lastAllowedDate));
            }
        });
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
    private void GoToMedecin(ActionEvent event) {
        Content.getChildren().clear();
        MedecinContent.setVisible(true);
        MedecinContent.setManaged(true);
        Content.getChildren().add(MedecinContent);
    }
    @FXML
    private void AddRdv(ActionEvent event) {
        if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || RvDate.getValue() == null || AvailableDoc.getSelectionModel().isEmpty()){
            alert.setVisible(true);
            alert.setText("Fill All the Blank");
            alert.setStyle("-fx-background-color: #ff4242;");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> alert.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else{
            LocalDate selectedDate = RvDate.getValue();
            Date rvdate = java.sql.Date.valueOf(selectedDate);
            client clt = new client(0,Nom.getText(),Prenom.getText());
            ImpClient ImpClient = new ImpClient();
            ImpMedecin ImpMedecin = new ImpMedecin();
            ImpClient.ajouterClient("database",clt);
            client recentClient  = ImpClient.getClientByName("database",Nom.getText(),Prenom.getText());
            String selectedItem = (String) AvailableDoc.getSelectionModel().getSelectedItem();
            String[] items = selectedItem.split(" ");
            medecin recentMedecin = ImpMedecin.getMedecinByName("database",items[0],items[1]);
            ImpRv ImpRndv = new ImpRv();
            ImpCreneau ImpCrn = new ImpCreneau();
            rv newRv = new rv(rvdate,recentClient.getId(),ImpCrn.getIdCreneauByDoc("database",recentMedecin.getId()));
            ImpRndv.ajouterRv("database",newRv);
            ClientTable();
            Nom.setText("");
            Prenom.setText("");
            RvDate.setValue(null);
            AvailableDoc.setValue(null);
            alert.setVisible(true);
            alert.setText("Rendez-Vous de "+ clt.getNom() + " " + clt.getPrenom() + " a etait creer");
            alert.setStyle("-fx-background-color: #38c038;");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> alert.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
    @FXML
    private void AddDoc(ActionEvent event) {
        if (DocNom.getText().isEmpty() || DocPrenom.getText().isEmpty()|| Hdebut.getText().isEmpty()|| Mdebut.getText().isEmpty()|| Hfin.getText().isEmpty()|| Mfin.getText().isEmpty()){
            DocAlert.setVisible(true);
            DocAlert.setText("Fill All the Blank");
            DocAlert.setStyle("-fx-background-color: #ff4242;");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> DocAlert.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else {
            medecin newMedecin = new medecin(0,DocNom.getText(),DocPrenom.getText());
            ImpMedecin impMedecin = new ImpMedecin();
            impMedecin.ajouterMedecin("database",newMedecin);
            medecin recentMedecin = impMedecin.getMedecinByName("database",DocNom.getText(),DocPrenom.getText());
            ImpCreneau ImpCreneau =  new ImpCreneau();
            creneau newCreneau = new creneau(0,Integer.parseInt(Hdebut.getText().trim()),Integer.parseInt(Mdebut.getText().trim()),Integer.parseInt(Hfin.getText().trim()),Integer.parseInt(Mfin.getText().trim()),recentMedecin.getId());
            ImpCreneau.ajouterCreneau("database",newCreneau);
            DocDataView();
            DocComboBox(AvailableDoc);
            DocNom.setText("");
            DocPrenom.setText("");
            Hdebut.setText("");
            Mdebut.setText("");
            Hfin.setText("");
            Mfin.setText("");
            DocAlert.setVisible(true);
            DocAlert.setText(newMedecin.getNom() + " " + newMedecin.getPrenom() + " a etait creer");
            DocAlert.setStyle("-fx-background-color: #38c038;");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(8), evt -> DocAlert.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
}