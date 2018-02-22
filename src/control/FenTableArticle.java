package control;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JTable;

import com.mysql.jdbc.Constants;

import dialogue.ControleConnexion;
import entite.Article;


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


public class FenTableArticle implements Initializable {
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
			
			tableVue.setItems(Principal.getArticleData());
			System.out.println("AAAAAAAAAAAAAAAAAA");
			System.out.println("ooo "+tableVue.getItems().get(0).getCode());
			
		}
	    private Article unArticle = new Article();
		private ArrayList<Article> listArticle = new ArrayList<Article>();
		
		private ObservableList<ObservableList> data;
		@FXML private TextArea resultArea;
		@FXML private TextField textCode;
		@FXML private TextField textCodeCateg;
		@FXML private TextField textDesignation;
		@FXML private TextField textQuantite;
		@FXML private TextField textPrixUnitaire;
		
		@FXML private TableView<Article> tableVue;
		@FXML private TableColumn<Article, String> colCode;
		@FXML private TableColumn<Article, String> colCodeCategorie;
		@FXML private TableColumn<Article, String> colDesignation;
		@FXML private TableColumn<Article, String> ColQuantite;
		@FXML private TableColumn<Article, String> ColPrixUnitaire;
		
		@FXML
	    private void ButtonAjouter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			unArticle.creerCRUD(textCode.getText(), textCodeCateg.getText(),textDesignation.getText()
					,Integer.parseInt(textQuantite.getText()),Double.parseDouble(textPrixUnitaire.getText()));
		}
		@FXML
	    private void ButtonEffacer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			
	        }
		@FXML
		private void ButtonRechercher(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
				//trouver comment faire la recherche
		        }
		@FXML
		private void ButtonModifier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			unArticle.modifCRUD(textCode.getText(), textCodeCateg.getText(),textDesignation.getText()
					,Integer.parseInt(textQuantite.getText()),Double.parseDouble(textPrixUnitaire.getText()));
		        }
		@FXML
		private void ButtonAnnuler(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
				
		        }
		@FXML
		private void ButtonSupprimer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
			unArticle.supprimeerCRUD(textCode.getText());
			
		}    
		//SOUCIS AVEC LE BOUTON MENU PRINCIPAL
		@FXML
	    private void ButtonMenuPrinc(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
					
				}
		private HashMap<String ,Article> listeArticleD = new HashMap(); 
			
	        @Override
			public void initialize(URL location, ResourceBundle resources) {
	        	colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty()); ;
				colCodeCategorie.setCellValueFactory(cellData -> cellData.getValue().CodeCategorieProperty());
				colDesignation.setCellValueFactory(cellData -> cellData.getValue().DesignationProperty());
			}
	    }
