package control;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dialogue.ControleConnexion;
import entite.Article;
import entite.Client;
import entite.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FenTableAdministrateur implements Initializable{
	private Stage dialogStage;
	private Principal fenCon;
	String leNomUtilisateur ;
	String leNom;
	String lePrenom;
	String leCode;
	String requete;
	
	
	
	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	 private Principal mainApp;
	 public void MainApp(Principal mainApp) {
	       this.mainApp = mainApp;
	       }
	   
	  
	   public void setMainApp(Principal fenConn) {
			this.fenCon = fenConn;
			//tableVue.setItems(Principal.getUtilisateurData());
			
			
		}
	   //CREATION D'UNE INSTANCE D'UTILISATEUR
	    private Utilisateur unUtilisateur = new Utilisateur();
	    
	    // UNE ARRALISTE QUI STOCKE LA LISTE DES UTLISATEURS
		private ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		private ObservableList<Utilisateur> utilisateurData = FXCollections.observableArrayList(listUtilisateur);
		
		private ObservableList<ObservableList> data;
		@FXML private TextArea  resultArea;
		@FXML private TextField textCode;
		@FXML private TextField textNomUtilisateur;
		@FXML private TextField textNom;
		@FXML private TextField textPrenom;
		
		
		
		
		@FXML private TableView<Utilisateur> tableVue;
		@FXML private TableColumn<Utilisateur, String> colCode;
		@FXML private TableColumn<Utilisateur, String> colNomUtilisateur;
		@FXML private TableColumn<Utilisateur, String> colNom;
		@FXML private TableColumn<Utilisateur, String> colPrenom;
		@FXML private TableColumn<Utilisateur, String> colMotDePasse;
		
		

	   
	   @FXML
		private void ButtonAjouter(ActionEvent event)throws IOException {
		    leNomUtilisateur = textNomUtilisateur.getText();
		    leNom = textNom.getText();
		    lePrenom = textPrenom.getText();
		    leCode = textCode.getText();
		    System.out.println(leCode);
		   
		   unUtilisateur.creerCRUD(leNomUtilisateur, leNom,lePrenom);
		   
		}
	   @FXML
		private void ButtonModifier(ActionEvent event)throws IOException {
		    leNomUtilisateur = textNomUtilisateur.getText();
		    leNom = textNom.getText();
		    lePrenom = textPrenom.getText();
		    leCode = textCode.getText();
		    System.out.println(leCode);
		   
		   unUtilisateur.modifCRUD(leCode,leNomUtilisateur, leNom,lePrenom);
		}
	   @FXML
		private void ButtonSupprimer(ActionEvent event)throws IOException {
		    leNomUtilisateur = textNomUtilisateur.getText();
		    leNom = textNom.getText();
		    lePrenom = textPrenom.getText();
		    leCode = textCode.getText();
		    System.out.println(leCode);
		   unUtilisateur.supprimeerCRUD(leCode);
		}
	   @FXML
		private void ButtonRechercher(ActionEvent event)throws IOException {
		   requete = textCode.getText();
		  
		   Utilisateur utilisateur = new Utilisateur();
		   listUtilisateur = utilisateur.chercherCRUD(requete);
		   utilisateurData = FXCollections.observableArrayList(listUtilisateur);
		   for (int i = 0; i <= 5; i++) {
		  //System.out.println(unUtilisateur.getLesEnreg().get(i).getCode());
		  //System.out.println(unUtilisateur.getLesEnreg().get(i).getNom());
		  //System.out.println(unUtilisateur.getLesEnreg().get(i).getPrenom());
		  }
		   tableVue.setItems(utilisateurData);
		    colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty()); ;
			colNomUtilisateur.setCellValueFactory(cellData -> cellData.getValue().NomUtilisateurProperty());
			colNom.setCellValueFactory(cellData -> cellData.getValue().NomProperty());
			colPrenom.setCellValueFactory(cellData -> cellData.getValue().PrenomProperty());
		}
	   @FXML
		private void ButtonImprimer(ActionEvent event)throws IOException {
			
		}
	   @FXML
		private void ButtonMenuPrincipal(ActionEvent event)throws IOException {
		   fenCon.NewFenMenuPrincipal();
		}
	   private HashMap<String ,Article> listeArticleD = new HashMap(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Principal.getUtilisateurData().isEmpty()) {
  		  System.out.println("Bassalor------------------------------------");
  	  }	else {
  		  System.out.println("Degage hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
  	  }
		/*colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty()); ;
		colNomUtilisateur.setCellValueFactory(cellData -> cellData.getValue().NomUtilisateurProperty());
		colNom.setCellValueFactory(cellData -> cellData.getValue().NomProperty());
		colPrenom.setCellValueFactory(cellData -> cellData.getValue().PrenomProperty());
		*/
	}

}
