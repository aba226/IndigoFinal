package entite;
//les differentes importations
	// pour la structure d'accueil des données
	import java.util.ArrayList;
	//pour la gestion de la propriété Date
	import java.util.Date;
	import java.util.HashMap;
	// pour la connexion à la base de Données
	import java.sql.Connection;
import java.sql.PreparedStatement;
//import control.connection.ControleConnexion;
	// pour les requetes SQL
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.LocalDate;

	// pour les boîtes de dialogue
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
public class Utilisateur {
	

	

	StringProperty code = new SimpleStringProperty();
	StringProperty nomUtilisateur = new SimpleStringProperty();;
	StringProperty nom = new SimpleStringProperty();
	StringProperty prenom = new SimpleStringProperty();
	StringProperty motDePasse =  new SimpleStringProperty();
	
	//Propriété pour établir la connexion avec la BD
	private static Connection laConnexion = 
			ControleConnexion.getConnexion();
	
	// propriété de type ArrayList qui contiendra les 
	//enregistrements de la BD
	private ArrayList<Utilisateur> lesEnreg = new ArrayList<Utilisateur>();
    private HashMap<String ,Utilisateur> listeUtilisateur = new HashMap<>();
	   
    
	//Getters 
	
	public String getCode() {
		return code.get() ;
	}
	public StringProperty codeProperty() {
		return code;
	}
	
	public String getNomUtilisateur() {
		return nomUtilisateur.get();
	}
	public StringProperty NomUtilisateurProperty() {
		return nomUtilisateur;
	}
	public String getNom() {
		return nom.get();
	}
	public StringProperty NomProperty() {
		return nom;
	}
	public String getPrenom() {
		return prenom.get();
	}
	public StringProperty PrenomProperty() {
		return prenom;
	}
	
	
	/*public Date getDate() {
		return date;
	}*/
	
	public ArrayList<Utilisateur>getLesEnreg(){
		return lesEnreg;
	}
	
	// Setters
	
	public void setCode(String code) {
		this.code.set(code);
	}
	public void setNomUtilisateur (String nomUtilisateur) {
		this.nomUtilisateur.set(nomUtilisateur);
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public void setPrenom( String prenom) {
		this.prenom.set(prenom);
	}
	
	/*public void setDate (Date date) {
		this.date = date;
	} */
	
	// CONSTRUCTEUR
	public Utilisateur(String vCode, String vNomUtilisateur, 
		String vNom, String vPrenom 
	/*, Date date*/) {
		this.code = new SimpleStringProperty(vCode);
		this.nomUtilisateur = new SimpleStringProperty(vNomUtilisateur);
		this.nom =new SimpleStringProperty(vNom);
		this.prenom = new SimpleStringProperty(vPrenom);
	
		//this.date = date;
	}
	public Utilisateur() {
		
	}
	/*public Utilisateur() {
		lireRecupCRUD();
	}*/
	/*public Article(String code) {
		this.code = code;
	}*/
	/////////////////////////////////////////////////////////
	// Lecture et recuperation des enregistrements
	 public void lireRecupCRUD() {
	    	try {
	    	  Statement state = laConnexion.createStatement();
	    	  ResultSet rs = state.executeQuery
	    			  ("SELECT * FROM utilisateurs ");
	    	  while (rs.next()) {
	    		  String code = rs.getString("code");
	    		  String nomUtilisateur = rs.getString("nomUtilisateur");
	    		  String nom = rs.getString("nom");
	    		  String prenom = rs.getString("prenom");
	    		
	    		  //Date date_creation = rs.getDate("date");
	    		   
	    	  
	    	  //Ajout à l'ArrayList
	    	  lesEnreg.add(new Utilisateur(code, nomUtilisateur, nom, prenom)); 
	    	  ///POUR VERIFIER SI L'ARRAYLIST EST VIDE OU PAS
	    	 /* if(lesEnreg.isEmpty()) {
	    		  System.out.println("Bassalor");
	    	  }	else {
	    		  System.out.println("Degage");
	    	  }*/
	    	  }
	    	}catch(SQLException e){
	    		JOptionPane.showMessageDialog(null, 
	    				"Problème rencontré : " +e.getMessage(),
	    				"Résultat", JOptionPane.ERROR_MESSAGE) ;
	    		}
	    }
	    ///////////////////////////////////////////
	    // Ajout d'un nouvel Utilisateur
	    public boolean creerCRUD( String vNomUtilisateur, 
	    		String vNom, String vPrenom/*, String vDate*/) {
	    	boolean bCreation = false;
	    	String requete = null;
	    	try {
	    	   requete = "INSERT INTO Utilisateurs (nomUtilisateur,nom,prenom) VALUES (?,?,?)";
	    	   
	    	   PreparedStatement prepare = 
	    			   laConnexion.prepareStatement(requete);
	    	   
	    	   prepare.setString(1, vNomUtilisateur);
	    	   prepare.setString(2, vNom);
	    	   prepare.setString(3, vPrenom);
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
	    //MODIFICATION D'UN UTILISATEUR
	    public boolean modifCRUD(String vCode, String vNomUtilisateur, 
	    		String vNom, String vPrenom) {
	    	boolean bModification = true;
	    	String requete = null;
	    	try {
	    		requete = "UPDATE utilisateurs SET  nomUtilisateur = '" + vNomUtilisateur + "', nom = '"+ vNom + "', prenom = '"+vPrenom +  "' WHERE code = '"  + vCode + "'";
	    		
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
	    
	    ////////////////////////////////////////////////////////////
	    // SUPPRESSION d'un Article  
	    public boolean supprimeerCRUD(String vCode) {
	    	boolean bSuppression = true; 
	    	String requete = null;
	    		try {
	    		  requete = "DELETE FROM utilisateurs WHERE code = ?";
	    		    
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
	    public ArrayList<Utilisateur> chercherCRUD(String recherche){
	      String requete =  "SELECT * FROM utilisateurs  WHERE code LIKE '%" + recherche + "%' OR nom LIKE '%" +recherche + "%' " ;
	    	
	    	try {
	    		Statement state = laConnexion.createStatement();
	    		ResultSet rs = state.executeQuery(requete);
	    		while (rs.next()) {
	    			String code = rs.getString("code");
	    			String nomUtilisateur = rs.getString("nomUtilisateur");
	    			String nom = rs.getString("nom");
	    			String prenom = rs.getString("prenom");
	    			System.out.println(code);
	    			System.out.println(nomUtilisateur);
	    			System.out.println(nom);
	    			System.out.println(prenom);
	    			
	    			//Date date_creation = rs.getDate("date");
	    			// Ajout à l'ArrayList
	    			lesEnreg.add(new Utilisateur (code, nomUtilisateur, 
	    					nom, prenom));
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
	
	    public HashMap<String, Utilisateur> listeUtil()
		{
			try {
				Statement state= laConnexion.createStatement();
				ResultSet rs=state.executeQuery("select * from utilisateurs");
				while(rs.next())
				{
					String code = rs.getString("code");
	    			String nomUtilisateur = rs.getString("nomUtilisateur");
	    			String nom = rs.getString("nom");
	    			String prenom = rs.getString("prenom");
	    			listeUtilisateur.put(code, new Utilisateur(code, nomUtilisateur, 
	    					nom, prenom));
	    			
				}
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
			}
			return listeUtilisateur;
		}   
	
}
