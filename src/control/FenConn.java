package control;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import dialogue.ControleConnexion;
import entite.Article;
import entite.Utilisateur;


/**
 *
 * @author aba
 */
public class FenConn implements Initializable {
	//UNE HASHMAP POUR RECUPERER LE CONTENU DE LA TABLE UTILISATEUR
			private HashMap<String, Utilisateur>listeUtilisateur = new Utilisateur().listeUtil();
			
    boolean verif = false;
	@FXML
	private Label label;

	@FXML
	private Label Dowl;

	@FXML
	private TextField textNameUser;
 
    @FXML
    private PasswordField textPassWord ;
    ///////////////////////////////////////////////
    public PasswordField getPassword()
    {
        return this.textPassWord ;
    }
    public FenConn() {
	 
    }
    public void setPwd(PasswordField textPassWord)
    {
        this.textPassWord = textPassWord ;
    }
 
    public TextField getNameUser()
    {
       return this.textNameUser ;
    }
 
    public void setPseudo (TextField NameUser)
    {
       this.textNameUser = NameUser ;
    }
    private Principal mainApp;
   
    public void setMainApp(Principal mainApp) {
       this.mainApp = mainApp;
    }
   
    
	@FXML
	private void ButtonAnnuler(ActionEvent event)throws IOException {
		//System.out.println("Connexion!");
				Dowl.setText(textNameUser.getText());
				textNameUser.setText("Connexion Reussie!");
				try {
				ControleConnexion.transfertDonnees();
			    }catch(SQLException e) {
			    	e.printStackTrace();
			    }
		
	}
	@FXML
	private void ButtonValider(ActionEvent event) throws IOException{
		controleConnexion_Appel();
		
	}
		
		//FONCTION POUR LE CONTROLE D LA CONNECTION
	
	private void controleConnexion_Appel() throws IOException {
		String leNom = textNameUser.getText();
		String leMotDePasse = textPassWord.getText();
		if (ControleConnexion.controle(leNom, leMotDePasse)) {
			System.out.println("Connexion!=================================================================yyyyyyyyyy");
			mainApp.administrateur();
		}
	 else {
		  for(Entry<String, Utilisateur> element : listeUtilisateur.entrySet()) {
				System.out.println("Code: " + element.getValue().getCode());
				System.out.println("Nom utilisateur: " + element.getValue().getNomUtilisateur());
				
				if ( element.getValue().getCode().equals(leMotDePasse)  && element.getValue().getNomUtilisateur().equals(leNom)) {
					mainApp.NewFenMenuPrincipal();
					verif = true;
					break; 
				} 
         }
		  if (verif == false) {
			  Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(mainApp.getPrimaryStage());
		        alert.setTitle("Echec de la Connection");
		        alert.setHeaderText("Verifier votre Saisie SVP");
		        alert.setContentText("Les parametres entrées sont incorrectes "
		        		+ "la base de données doivent etres vérifiés "
		        		+ "Veuillez Contactez l'administrateur.");

		        alert.showAndWait();
		  }
				
			
		  
	  }
		// System.exit(0); }*/
		  
			 textNameUser.clear();
			 textPassWord.clear();
	} 

	   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
