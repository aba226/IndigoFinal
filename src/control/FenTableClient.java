package control;

import javax.swing.JTable;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import com.mysql.jdbc.Constants;

import dialogue.ControleConnexion;
import entite.Client;

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

public class FenTableClient implements Initializable{
	private Stage dialogStage;
	private Principal fenCon;
	private HashMap<String, Client> listeClient=new Client().listeDeClient();
	
	
	/*public FenTableClient() {
		
	}*/
	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	 private Principal mainApp;
	   public void MainApp(Principal mainApp) {
	       this.mainApp = mainApp;
	       }
	   
	public void setMainApp(Principal fenConn) {
		this.fenCon = fenConn;
		
		
		tableVue.setItems(fenConn.getPersonData());
		System.out.println("AAAAAAAAAAAAAAAAAA");
		System.out.println("ooo "+tableVue.getItems().get(0).getPrenom());
		
	}
	String Abou;
	private Client unClient = new Client();

	
	private ObservableList<ObservableList> data;
	@FXML private TextField empIdText;
	@FXML private TextArea  resultArea;
	@FXML private TextField textCode;
	@FXML private TextField textNom;
	@FXML private TextField textPrenom;
	@FXML private TextField emailText;
	
	@FXML private TableView<Client> tableVue;
	@FXML private TableColumn<Client, String> colCode;
	@FXML private TableColumn<Client, String> colNom;
	@FXML private TableColumn<Client, String> colPrenom;
	
		
	@FXML
    private void ButtonAjouter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		unClient.creerCRUD(textCode.getText(), textNom.getText(),textPrenom.getText());
	}
	@FXML
    private void ButtonHandleAper(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        }
	@FXML
	private void ButtonRechercher(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			System.out.println(textCode.getText());
			Abou = "PLSY01";
			unClient.chercherCRUD(textCode.getText());
			
	        }
	@FXML
	private void ButtonModifier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		    unClient.modifierCRUD(textCode.getText(),textNom.getText(),textPrenom.getText());
		    
	        }
	@FXML
	private void ButtonSupprimer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		    unClient.supprimerCRUD(textCode.getText());
		    System.out.println("kara ismaeel" );
	        }
	//SOUCIS AVEC LE BOUTON MENU PRINCIPAL
	@FXML
    private void ButtonMenuPrinc(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		fenCon.NewFenMenuPrincipal();			
			}
	@FXML
    void ButonRetour(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
		fenCon.NewFenMenuPrincipal();
    }
	
        @Override
		public void initialize(URL location, ResourceBundle resources) {
        	colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty()); ;
    		colNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
    		colPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
    		//colCarteFidelite.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    		//colDateCreation.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());  
    		//listClient = unClient.getlesEnreg();
			 
		}
    }
   

