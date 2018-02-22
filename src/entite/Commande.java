package entite;
//les differentes importations
//pour la structure d'accueil des données
import java.util.ArrayList;
//pour la gestion de la propriété Date
import java.util.Date;
//pour la connexion à la base de Données
import java.sql.Connection;
import java.sql.PreparedStatement;
//import control.connection.ControleConnexion;
//pour les requetes SQL
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

//pour les boîtes de dialogue
import javax.swing.JOptionPane;

import dialogue.ControleConnexion;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande {



	
	//Popriété de base de la classe
	/*private String code;
	private String code_categorie;
	private String designation;
	private int quantite;
	private double prix_unitaire;
	private Date date;*/
	
	StringProperty code;
	StringProperty codeDuClient;
    Client code_client;
    DoubleProperty total_ttc;
    IntegerProperty codeModeReglement;
    Mode_Reglements mode_reglement;
	//DateProperty date;
	
	//Propriété pour établir la connexion avec la BD
	private static Connection laConnexion = 
			ControleConnexion.getConnexion();
	
	
	// propriété de type ArrayList qui contiendra les 
	//enregistrements de la BD
	private final ArrayList<Commande> lesEnreg = new ArrayList<Commande>();
	
	//CONSTRUCTEUR
	//--------------------
	//CONSTRUCTEUR1
	public Commande(StringProperty code, Client code_client, 
	   DoubleProperty total_ttc, Mode_Reglements mode_reglement){
	   this.code = code;
	   this.code_client = code_client;
	   this.total_ttc = total_ttc;
	   this.mode_reglement = mode_reglement;
	}
	//CONSTYRUCTEUR 2
	public Commande() {
		lireRecupCRUD1();
	}
	//CONSTRUCTEUR 3 POUR INITER LA RECHHERCHE
	public Commande(String code) {
	}
	public Commande(String code2, Client code_client2, double total_ttc2, Mode_Reglements mode_reglement2) {
		// TODO Auto-generated constructor stub
	}
	public Commande(String code2, String code_client2, String total_ttc2) {
		// TODO Auto-generated constructor stub
	}
	//Getters 
	
	public String getCode() {
		return code.get() ;
	}
	public StringProperty codeProperty() {
		return code;
	}
	public String getCodeDuClient() {
		return codeDuClient.get() ;
	}
	public StringProperty codeDuClient() {
		return codeDuClient;
	}
	public Client getCodeClient() {
		return code_client;
	}
	public double getTotal_ttc() {
		return total_ttc.get();
	}
	public DoubleProperty total_ttcProperty() {
		return total_ttc;
	}
	public Mode_Reglements getMode_reglement() {
		return mode_reglement;
	}
	
	/*public Date getDate() {
		return date;
	}*/
	
	public ArrayList<Commande>getLesEnreg(){
		return lesEnreg;
	}
	
	// Setters
	
	public void setCode(String code) {
		this.code.set(code);
	}
	public void setCodeDuClient(String codeDuClient) {
		this.codeDuClient.set(codeDuClient);
	}
	public void setTotal_ttc(Double total_ttc) {
		this.total_ttc.set(total_ttc);
	}
	/*public void setDesignation (String designation) {
		this.designation.set(designation);
	}
	public void setTReference(String code_categorie) {
		this.code_categorie.set(code_categorie);
	}
	public void setQuantite( int quantite) {
		this.quantite.set(quantite);
	}
	public void setPrix_unitaire(double prix_unitaire) {
		this.prix_unitaire.set(prix_unitaire);
	}
	/*public void setDate (Date date) {
		this.date = date;
	} */
	
	
	/////////////////////////////////////////////////////////
	// Lecture et recuperation des enregistrements
	 public void lireRecupCRUD() {
	    	try {
	    	  Statement state = laConnexion.createStatement();
	    	  ResultSet rs = state.executeQuery
	    			  ("SELECT com.code,"
	    			  	+ " com.total_ttc,com.date,cli.nom,"
	    			  	+ " cli.prenom,mode.type"
	    			  	+ " FROM commandes AS com, clients AS cli,"
	    			  	+ " mode_reglements AS mode"
	    			  	+ " WHERE com.code_mode_reglement =mode.code"
	    			  	+ " AND com.code_client = cli.code");
	    	  while (rs.next()) {
	    		  //Information générale commande
	    		  String code = rs.getString("code");
	    		  double total_ttc = rs.getDouble("total_ttc");
	    		//Date date_creation = rs.getDate("date");
	    		  
	    		  //Informations client
	    		  String type = rs.getString("type");
	    		 
	    	  
	    	  //Ajout à l'ArrayList
	    		//  lesEnreg.add(new Commande(code, codeClient, total_ttc /*, 
	        		//	  carte_fidele, day*/));
	    	 /* lesEnreg.add( code,
	    			       (code,type),
	    			       total_ttc,
	    			       new Mode_Reglements (type)));*/
	    	  }	
	    	}catch(SQLException e){
	    		JOptionPane.showMessageDialog(null, 
	    				"Problème rencontré : " +e.getMessage(),
	    				"Résultat", JOptionPane.ERROR_MESSAGE) ;
	    		
	    }}
	 //////////////////////////////////////
	 //FONCTION POUR RECUPERER LES COMMANDES
	 public void lireRecupCRUD1() {
	    	try {
	    	  Statement state = laConnexion.createStatement();
	    	  ResultSet rs = state.executeQuery("SELECT * " + 
	    	   "FROM commandes ");
	    	  while (rs.next()) {
	    		  String code = rs.getString("code");
	    		  String code_client = rs.getString("code_client");
	    		  String total_ttc = rs.getString("total_ttc");
	    		  System.out.print(code);
	    		  System.out.println(code_client);
	    		  System.out.println(total_ttc); 
	    		 // boolean carte_fidele = rs.getBoolean("carte_fidele");
	    		 // Date day = rs.getDate("date");
	    	  
	    	  //Ajout à l'ArrayList
	    	  lesEnreg.add(new Commande(code, code_client, total_ttc /*, 
	    			  carte_fidele, day*/));
	    	  
	    	  }	
	    	}catch(SQLException e){
	    		JOptionPane.showMessageDialog(null, 
	    				"Problème rencontré : " +e.getMessage(),
	    				"Résultat", JOptionPane.ERROR_MESSAGE) ;
	    		}
	    }
	    ///////////////////////////////////////////
	    // Ajout d'une nouvelle Commande
	   public boolean creerCRUD(String vCode, String vCode_Client, 
	    		Double vTotalTTC,int vCode_Mode_Reglement) {
	    	boolean bCreation = false;
	    	String requete = null;
	    	//REQUETE POUR VERIFIER SI LE CODE DU CLIENT EXISTE DANS LA BASE DE DONNEES
	    	try {
	      	  Statement state = laConnexion.createStatement();
	      	  ResultSet rs = state.executeQuery("SELECT code " + 
	      	   "FROM clients ");
	      	  while (rs.next()) {
	      		  String code = rs.getString("code");
	      //ON INSERE LA COMMANDE APRES VERIFICATION
	      		if (vCode_Client.equals(rs.getString("code"))) {
	      			try {
	     	    	   requete = "INSERT INTO commandes VALUES (?,?,?,?,NOW())";
	     	    	   
	     	    	   PreparedStatement prepare = 
	     	    			   laConnexion.prepareStatement(requete);
	     	    	   prepare.setString(1, vCode);
	     	    	   prepare.setString(2, vCode_Client);
	     	    	   prepare.setDouble(3, vTotalTTC);
	     	    	   prepare.setInt(4, vCode_Mode_Reglement);
	     	    	  
	     	    	   prepare.executeUpdate();
	     	    	   bCreation = true;
	     	    	}
	     	    	catch(SQLException e) { 
	     	    		JOptionPane.showMessageDialog(null,
	     	    			"Ajout dans la BD non effectué : " + e.getMessage(),
	     	    			"Problème rencontré", JOptionPane.ERROR_MESSAGE );    		
	     	    	}
	    			
	    		}else JOptionPane.showMessageDialog(null,
						"Impossible de creer la commande." + "Véfier l'existance Du Code du client" + '\n' + '\n'
						+ "" + '\n' + ""
						+ "" + '\n' + '\n' + ""
						+ "informatique.",
				"ALERTE", JOptionPane.ERROR_MESSAGE);
	      		  System.out.print(code);
	      		 // boolean carte_fidele = rs.getBoolean("carte_fidele");
	      		 // Date day = rs.getDate("date");
	      	  }	
	      	}catch(SQLException e){
	      		JOptionPane.showMessageDialog(null, 
	      				"Problème rencontré : " +e.getMessage(),
	      				"Résultat", JOptionPane.ERROR_MESSAGE) ;
	      		}
	    	
	    	return bCreation;
	    }
	    
	    //////////////////////////////////////////////////////////////////
	    ///////////////////////////////////////////////////////////////////////
	    //MODIFICATION D'UNE COMMANDE
	    public boolean modifierCRUD(String vCode, String vCode_Client, 
	    		Double vTotalTTC,int vCode_Mode_Reglement) {
	    	boolean bModification = true;
	    	String requete = null;
	    	try {
	    		requete = "UPDATE articles SET  code_client = '" + vCode_Client + "', total_ttc = '"+ vTotalTTC + "', code_Mode_Reglement = '"+vCode_Mode_Reglement + "' WHERE code = '"  + vCode + "'";
	    		
	    		Statement state = laConnexion.createStatement();
	    		state.executeUpdate(requete);
	    		state.close();
	    	}
	    	catch (SQLException e) {
	    		bModification = false;
	    		JOptionPane.showMessageDialog(null,
	    				"Modification dans la BD non effectuées. "
	    				+ e.getMessage(),
	    				"Problème rencontré", JOptionPane.ERROR_MESSAGE);
	    	}
	    	return bModification;
	    }
	    
	    ////////////////////////////////////////////////////////////
	    // SUPPRESSION D'UNE COMMANDE 
	    public boolean supprimeerCRUD(String vCode) {
	    	boolean bSuppression = true; 
	    	String requete = null;
	    		try {
	    		  requete = "DELETE commandes, lignes_commandes" 
	    				  +"FROM commandes,lignes_commandes"
	    				  + " WHERE code_commande = code AND code = ?";
	    		    
	    		  PreparedStatement prepare = 
		    			   laConnexion.prepareStatement(requete);
		    	   prepare.setString(1, vCode);
		    	   int nbEnregSup = prepare.executeUpdate();
		    	   if(nbEnregSup == 0) { 
		    			 JOptionPane.showMessageDialog(null,
		    			 "Aucune suppression effectuée dans la BD.",
		    			 "Problème rencontré"
		    			, JOptionPane.ERROR_MESSAGE);		
	    			}
	    		}
	    		catch (SQLException e) {
	    			bSuppression = false;
	    			JOptionPane.showMessageDialog(null,
	    				"Aucune Suppression effectuée dans la BD :"
	    				+ e.getMessage(), "Problème rencontré", 
	    				JOptionPane.ERROR_MESSAGE);
	    		}
	    	return bSuppression;
	    }
	///////////////////////////////////////////////////////////////
	    // Recherche 
	   /* public ArrayList<Commande> chercherCRUD(String recherche){
	      String requete = "";
	      requete += "SELECT * ";
	      requete += "FROM articles ";
	      requete += "WHERE code LIKE '%" + recherche + "%' ";
	      requete += "OR code_categorie LIKE '%" +recherche + "%' " ;
	      requete += "OR designation LIKE '%" +recherche + "%' " ;
	    	
	    	try {
	    		Statement state = laConnexion.createStatement();
	    		ResultSet rs = state.executeQuery(requete);
	    		while (rs.next()) {
	    			String code = rs.getString("code");
	    			String code_categorie = rs.getString("code_categorie");
	    			String designation = rs.getString("designation");
	    			int quantite = rs.getInt("quantite");
	    			double prix_unitaire = rs.getDouble("prix_unitaire");
	    			
	    			//Date date_creation = rs.getDate("date");
	    			// Ajout à l'ArrayList
	    			lesEnreg.add(new Article (code, code_categorie, 
	    					designation, quantite, 
	    					prix_unitaire));
	    		}
	    	}
	    	catch(SQLException e){
	    		JOptionPane.showMessageDialog(null,
	    				"Problème rencontré pendant la recherche"
	    				+ e.getMessage(), "Résultat", 
	    				JOptionPane.ERROR_MESSAGE);
	    		
	    	}
	    	return lesEnreg;
	    }*/
		
}

