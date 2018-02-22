package control;

import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JTable;

import com.mysql.jdbc.Constants;

import dialogue.ControleConnexion;
import entite.Article;
import entite.Client;
import entite.Commande;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.ModlCli;

import javax.swing.JOptionPane;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FenFichierDesCommandes implements Initializable{
	private Stage dialogStage;
	private Principal fenCon;
	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	 private Principal mainApp;
	   public void MainApp(Principal mainApp) {
	       this.mainApp = mainApp;
	       }
	   public void setMainApp(Principal fenConn) {
			this.fenCon = fenConn;
			
		//	tableVue.setItems(Principal.getArticleData());
			System.out.println("AAAAAAAAAAAAAAAAAA");
			//System.out.println("ooo "+tableVue.getItems().get(0).getCode());
		}
	    private Commande uneCommande = new Commande();
		private ArrayList<Commande> listCommande = new ArrayList<Commande>();
		
		private ObservableList<Commande> commandeData = FXCollections.observableArrayList();
		private ObservableList<Commande> commandeListOb = FXCollections.observableArrayList(listCommande);
		 public ObservableList<Commande> getPersonData() {
			 return commandeListOb;
		    }
		
		private ObservableList<ObservableList> data;
		@FXML private TextField empIdText;
		@FXML private TextArea  resultArea;
		@FXML private TextField textCode;
		@FXML private TextField textNom;
		@FXML private TextField textPrenom;
		@FXML private TextField emailText;
		
		@FXML private TableView<Commande> tableVue;
		@FXML private TableColumn<Commande, String> colCode;
		@FXML private TableColumn<Commande, String> colClient;
		@FXML private TableColumn<Commande, String> colModeDePaiment;
		
		
	   @FXML
	    private void ButtonFichierDesCommandes(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			mainApp.NewFenFichierDesCommandes();
	        }
		@FXML
		private void ButtonAjouter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
				
				
		        }
		@FXML
		private void ButtonModifier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			    
		        }
		@FXML
		private void ButtonSupprimer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			    
		        }
		//SOUCIS AVEC LE BOUTON MENU PRINCIPAL
		@FXML
	    private void ButtonMenuPrinc(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			mainApp.NewFenMenuPrincipal();
				}
		@FXML
		private void ButonRetour(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			mainApp.NewFenTableCommande();
		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			commandeListOb = FXCollections.observableArrayList((new Commande()).getLesEnreg());
			
			//tableVue.setItems(getPersonData());
			tableVue.setItems(commandeListOb);
			//colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty()); ;
			//colClient.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
			//colModeDePaiment.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
    		//colCarteFidelite.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    		//colDateCreation.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());  
    		//listClient = unClient.getlesEnreg();
			 
		}

}
