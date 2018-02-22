package entite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import dialogue.ControleConnexion;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Article {
	//PROPRIETES
	
	//Popriété de base de la classe
	/*private String code;
	private String code_categorie;
	private String designation;
	private int quantite;
	private double prix_unitaire;
	private Date date;*/
	
	StringProperty code = new SimpleStringProperty();
	StringProperty code_categorie = new SimpleStringProperty();;
	StringProperty designation = new SimpleStringProperty();
	IntegerProperty quantite = new SimpleIntegerProperty();
	DoubleProperty prix_unitaire =  new SimpleDoubleProperty();
	
	//Propriété pour établir la connexion avec la BD
	private static Connection laConnexion = 
			ControleConnexion.getConnexion();
	
	// propriété de type ArrayList qui contiendra les 
	//enregistrements de la BD
	private ArrayList<Article> lesEnreg = new ArrayList<Article>();
    private HashMap<String ,Article> listeArticleD = new HashMap<>();
	   
    
	//Getters 
	
	public String getCode() {
		return code.get() ;
	}
	public StringProperty codeProperty() {
		return code;
	}
	
	public String getCodeCategorie() {
		return code_categorie.get();
	}
	public StringProperty CodeCategorieProperty() {
		return code_categorie;
	}
	public String getDesignation() {
		return designation.get();
	}
	public StringProperty DesignationProperty() {
		return designation;
	}
	public int getQuantite() {
		return quantite.get();
	}
	public IntegerProperty QuantiteProperty() {
		return quantite;
	}
	public double getPrix_unitaire() {
		return prix_unitaire.get();
	}
	public DoubleProperty Prix_unitaireProperty() {
		return prix_unitaire;
	}
	/*public Date getDate() {
		return date;
	}*/
	
	public ArrayList<Article>getLesEnreg(){
		return lesEnreg;
	}
	
	// Setters
	
	public void setCode(String code) {
		this.code.set(code);
	}
	public void setDesignation (String designation) {
		this.designation.set(designation);
	}
	public void setCodeCategorie(String code_categorie) {
		this.code_categorie.set(code_categorie);
	}
	public void setQuantite( Integer quantite) {
		this.quantite.set(quantite);
	}
	public void setPrix_unitaire(Double prix_unitaire) {
		this.prix_unitaire.set(prix_unitaire);
	}
	/*public void setDate (Date date) {
		this.date = date;
	} */
	
	// CONSTRUCTEUR
	public Article(String vCode, String vCode_categorie, 
		String vDesignation, int vQuantite, 
		double vPrix_unitaire /*, Date date*/) {
		this.code = new SimpleStringProperty(vCode);
		this.code_categorie = new SimpleStringProperty(vCode_categorie);
		this.designation =new SimpleStringProperty(vDesignation);
		this.quantite = new SimpleIntegerProperty(vQuantite);
		this.prix_unitaire = new SimpleDoubleProperty(vPrix_unitaire);
		//this.date = date;
	}
	public Article() {
		lireRecupCRUD();
	}
	/*public Article(String code) {
		this.code = code;
	}*/
	/////////////////////////////////////////////////////////
	// Lecture et recuperation des enregistrements
	 public void lireRecupCRUD() {
	    	try {
	    	  Statement state = laConnexion.createStatement();
	    	  ResultSet rs = state.executeQuery
	    			  ("SELECT * FROM articles ");
	    	  while (rs.next()) {
	    		  String code = rs.getString("code");
	    		  String code_categorie = rs.getString("code_categorie");
	    		  String designation = rs.getString("designation");
	    		  int quantite = rs.getInt("quantite");
	    		  double prix_unitaire = rs.getDouble("prix_unitaire");
	    		  //Date date_creation = rs.getDate("date");
	    	  
	    	  //Ajout à l'ArrayList
	    	  lesEnreg.add(new Article(code, code_categorie, designation, 
	    			  quantite, prix_unitaire));  
	    	  }	
	    	}catch(SQLException e){
	    		JOptionPane.showMessageDialog(null, 
	    				"Problème rencontré : " +e.getMessage(),
	    				"Résultat", JOptionPane.ERROR_MESSAGE) ;
	    		}
	    }
	    ///////////////////////////////////////////
	    // Ajout d'un nouvel Article
	    public boolean creerCRUD(String vCode, String vCodeCategorie, 
	    		String vDesignation, int vQuantite, double vPu/*, String vDate*/) {
	    	boolean bCreation = false;
	    	String requete = null;
	    	try {
	    	   requete = "INSERT INTO articles VALUES (?,?,?,?,?,NOW())";
	    	   
	    	   PreparedStatement prepare = 
	    			   laConnexion.prepareStatement(requete);
	    	   prepare.setString(1, vCode);
	    	   prepare.setString(2, vCodeCategorie);
	    	   prepare.setString(3, vDesignation);
	    	   prepare.setInt(4, vQuantite);
	    	   prepare.setDouble(5, vPu);
	    	   prepare.executeUpdate();
	    	   bCreation = true;
	    	    
	    	}
	    	catch(SQLException e) { 
	    		JOptionPane.showMessageDialog(null,
	    			"Ajout dans la BD non effectué : " + e.getMessage(),
	    			"Problème rencontré", JOptionPane.ERROR_MESSAGE );    		
	    	}
	    	return bCreation;
	    }
	    
	    //////////////////////////////////////////////////////////////////
	    ///////////////////////////////////////////////////////////////////////
	    //MODIFICATION D'UN CLIENT n°2
	    public boolean modifCRUD(String vCode, String vCodeCategorie, 
		    	String vDesignation, int vQuantite, double vPu) {
	    	boolean bModification = true;
	    	String requete = null;
	    	try {
	    		requete = "UPDATE articles SET  code_categorie = '" + vCodeCategorie + "', designation = '"+ vDesignation + "', quantite = '"+vQuantite + "', prix_unitaire = '"+vPu + "' WHERE code = '"  + vCode + "'";
	    		
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
	    //////////////////////////////////////////////////////////////////
	    //////////////////////////////////////////////////////////////////
	    //Modification d'un Article
	    public boolean modifierCRUD(String vCode, String vReference, 
	    	String vDesignation, int vQuantite, double vPu) {
	    	boolean bModification = false;
	    	String requete = null;
	    	try {
	    		requete = "UPDATE articles SET "
	    			+ "code_categorie = ?, "          
	    			+ "designation = ?, "   
	    			+ "quantite = ?, " 
	    			+ "prix_unitaire = '"  
	    			+ " WHERE code = ? " ;
	    		
	    		
	    		   PreparedStatement prepare = 
		    			   laConnexion.prepareStatement(requete);
		    	   prepare.setString(1, vReference);
		    	   prepare.setString(2, vDesignation);
		    	   prepare.setInt(3, vQuantite );
		    	   prepare.setDouble(4, vPu );
		    	  
		    	   prepare.executeUpdate();
		    	   bModification = true;
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
	    // SUPPRESSION d'un Article  
	    public boolean supprimeerCRUD(String vCode) {
	    	boolean bSuppression = true; 
	    	String requete = null;
	    		try {
	    		  requete = "DELETE FROM articles WHERE code = ?";
	    		    
	    		  PreparedStatement prepare = 
		    			   laConnexion.prepareStatement(requete);
		    	   prepare.setString(1, vCode);
		    	   int nbEnregSup = prepare.executeUpdate();
		    	   if(nbEnregSup == 0) { 
		    			 JOptionPane.showMessageDialog(null,
		    			 "Aucun enregistrement correspondant.",
		    			 "Resultat", JOptionPane.ERROR_MESSAGE);		
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
	    // 1ère Recherche 
	    public ArrayList<Article> chercherCRUD(String recherche){
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
	    }
	
	    public HashMap<String, Article> listeDe()
		{
			try {
				Statement state= laConnexion.createStatement();
				ResultSet rs=state.executeQuery("select * from articles");
				while(rs.next())
				{
					String code=rs.getString("code");
					String code_categorie=rs.getString("code_categorie");
					String designation=rs.getString("designation");
					int quantite=rs.getInt("quantite");
					double prix_unitaire=rs.getDouble("prix_unitaire");
					Date date_creation=rs.getDate("date");
					
					listeArticleD.put(code, new Article(code,code_categorie,designation,quantite,prix_unitaire));
				
				}
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
			}
			return listeArticleD;
		}   
	
}
