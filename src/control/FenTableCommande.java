package control;

import javax.swing.JTable;

import com.mysql.jdbc.Constants;

import dialogue.ControleConnexion;
import entite.Article;
import entite.Client;
import entite.Commande;
import entite.Mode_Reglements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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


public class FenTableCommande implements Initializable {
	private Stage dialogStage;
	private Principal fenCon;
	 private LinkedHashMap <String ,Article> panier = new LinkedHashMap<>();
	 private LinkedHashMap <String ,Commande> listeDeTouteLesCommmandes = new LinkedHashMap<>();
	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	 private Principal mainApp;
	   public void MainApp(Principal mainApp) {
	       this.mainApp = mainApp;
	       }
	    private Commande uneCommande = new Commande();
	    //CHARGE LES DIFFERRENTS ARICLES,CLIENT, MODE_REGMLEMENT
		private ArrayList<Commande> listCommmande = new ArrayList<Commande>();
		private HashMap<String, Article>listeArticleUse = new Article().listeDe();	
		private HashMap<String ,Client> listeClientMap = new Client().listeDeClient();
		
		private HashMap<String ,Mode_Reglements> listeModeReglement = new HashMap<>();
		 
	    private ObservableList<Article> articleData = FXCollections.observableArrayList();
		@FXML private TextArea  resultArea;
		@FXML private TextField textCode;
		@FXML private TextField textCodeCateg;
		@FXML private TextField textCodeDuClient;
		@FXML private TextField textQuantite;
		@FXML private TextField textPrixUnitaire;
		@FXML private TextField quantite;
		@FXML private ComboBox  comboBox;
		@FXML private TableView<Article> tableVue;
		@FXML private TableColumn<Article, String> colCode;
		@FXML private TableColumn<Article, String> colCodeCategorie;
		@FXML private TableColumn<Article, String> colDesignation;
		@FXML private TableColumn<Article, String> ColQuantite;
		@FXML private TableColumn<Article, String> ColPrixUnitaire;
		 
		@FXML 
		private void BouttonAjouterCommande(){
			
		}
		@FXML
	    private void ButtonAjouter () {
			/*uneCommande.creerCRUD(textCode.getText(), textCodeCateg.getText()
			,Integer.parseInt(textQuantite.getText()),Double.parseDouble(textPrixUnitaire.getText()));*/
		}
		
	  
	   @FXML
	    private void ButtonAjouterAuPanier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
		  try {
			  
			  
			  /*for(Entry<String, Article> element : listeArticleUse.entrySet()) {
				  System.out.println(element.getValue().getPrix_unitaire());
			  }*/
	  
			  if ( listeArticleUse.containsKey(textCode.getText())) {  
				  Article article = new Article();
				  
				  if(Integer.parseInt(textQuantite.getText())<= listeArticleUse.get(textCode.getText()).getQuantite()) {
						
					if(panier.containsKey(textCode.getText()))  {
						article.setQuantite(listeArticleUse.get(textCode.getText()).getQuantite() + Integer.parseInt(textQuantite.getText())); 	
					}else {
						article.setCode(textCode.getText());
						article.setCodeCategorie(listeArticleUse.get(textCode.getText()).getCodeCategorie());
						article.setDesignation(listeArticleUse.get(textCode.getText()).getDesignation());
						article.setPrix_unitaire(listeArticleUse.get(textCode.getText()).getPrix_unitaire());
						article.setQuantite(Integer.parseInt(textQuantite.getText()));
					}
					panier.put(textCode.getText(), article);
					listeArticleUse.get(textCode.getText()).setQuantite(listeArticleUse.get(textCode.getText()).getQuantite() - article.getQuantite());
					articleData.add(article);
					System.out.println("Liste des articles du panier :");
					
					for(Entry<String, Article> element : panier.entrySet()) {
						System.out.println("Code: " + element.getValue().getCode());
						System.out.println("Code Categorie: " + element.getValue().getCodeCategorie());
						System.out.println("Designation: " + element.getValue().getDesignation());
						System.out.println("Quantite: " + element.getValue().getQuantite());
						System.out.println("Prix: " + element.getValue().getPrix_unitaire());
					}
					
					colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
					tableVue.setItems(articleData);

				  }else {
					  System.out.println("La valeur n");
				  }
			  }else {
				  System.out.println("l'article n'existe pas ou n'a pas été créé");
			  }
			 
		  }catch(NullPointerException e) {
			  e.printStackTrace();
		  }
		  finally {
			  textCode.clear();
			  textQuantite.clear();
		  }
		
	   
		}
	   //FONCTION POUR PASSER LA COMMANDE DU PANIER QU'ON A CREE
	   @FXML void BouttonPasserCommande() {
		   int nombreAleatoire = (int)(Math.random() * (10000));
		   System.out.println(nombreAleatoire);
		   double montant_ttc=0;
		   for(Entry<String, Article> article1:panier.entrySet())
		   {System.out.println("bonjour");
			   montant_ttc+=article1.getValue().getPrix_unitaire()*article1.getValue().getQuantite();
			   System.out.println(montant_ttc);
		   }
		   
		  for(Entry<String, Client> lid:listeClientMap.entrySet())
		  {
			  lid.getValue().getCode();
		  }
		  // new Commande().creerCRUD(nombreAleatoire+"bien",textCodeDuClient.getText(),montant_ttc,1);
		   

		   
		   //Commmande.creerCRUD(vCode, vCode_Client, vCode_Mode_Reglement, vTotalTTC);
		}
		@FXML
	    private void ButtonFichierDesCommandes(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			mainApp.NewFenFichierDesCommandes();
	        }
		@FXML
		private void ButtonRechercher(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
				
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
					
				}
	   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*Quantite.getItems().add(1);
		Quantite.getItems().add(2);
		Quantite.getItems().add(3);
		Quantite.getItems().add(4);
		Quantite.getItems().add(5);
		Quantite.getItems().add(6);
		Quantite.getItems().add(7);
		
		*/
		//comboBox.getItems().addAll("Option A", "Option B", "Option C");
	}
	
	@FXML
    void ButtonRetourner() {
		mainApp.NewFenMenuPrincipal();
    }

}
