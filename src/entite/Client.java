package entite;

//les differentes importations
// pour la structure d'accueil des données
import java.util.ArrayList;
//pour la gestion de la propriété Date
import java.util.Date;
import java.util.HashMap;
// pour la connexion à la base de Données
import java.sql.Connection;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends Personne {
	// propiétés de baxse de la classe
	private BooleanProperty carte_fidele;
	//private LocalDate date;
	Date day;
	
	
	// Propriété pour établir la connexion avec la base de données
	private static Connection laConnexion = 
			ControleConnexion.getConnexion();
	
	// propriété de type ArrayList qui contiendra
	// les Enregistrements de la base données
	private final ArrayList<Client> lesEnreg = new ArrayList<Client>();
	 private HashMap<String ,Client> listeClient = new HashMap<>();
	
	//Getters de base
	public String getCode() {
		return code.get();
	}
	public StringProperty codeProperty() {
		return code;
	}
	
	public String getNom() {
		return nom.get();
	}
	public StringProperty nomProperty() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom.get();
	}
	public StringProperty prenomProperty() {
		return prenom;
	}
	
	/*public boolean isCarte_Fidele() {
		/*public BooleanProperty carteFideleProperty() {
		return carte_fidele;
	}*//*return carte_fidele.get();
	}*/
	
	/*public Date getDate_Creation() {
		return date;
	}*/
	
	//Getter pour transmettre l'ArrayList
	public ArrayList<Client> getlesEnreg(){
		return lesEnreg;
	}
	
	//Setters 
	public void setCode(String code) {
		this.code.set(code) ;
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	/*public void setCarte_fidele(boolean carte_fidele) {
		this.carte_fidele.set(carte_fidele);
	} */
    /*
	public void setDate_creation(Date date_creation) {
    	this.date.set(date_creation);
    }*/
    
    // CONSTRUCTEURS
    
    //1er Constructeur
    public Client(String vCode, String vNom, String vPrenom
    	      	/* ,boolean vCarteFidele, Date vDateCreation*/) {
    	super (vCode, vNom, vPrenom);
    	this.code =new SimpleStringProperty(vCode);
    	this.nom = new SimpleStringProperty(vNom);
    	this.prenom = new SimpleStringProperty(vPrenom);
    	//carte_fidele = new SimpleBooleanProperty(vCarteFidele);
    	//date = vDateCreation;
    	}
    
    //2ième Constructeur
    public Client(String vCode) {
    	super(vCode);
    	this.code = new SimpleStringProperty(vCode);
    }
    
    //3ième Constructeur
    public Client() {
    	lireRecupCRUD();
    }
    
    //4ième Constructeur
    public Client(String vNom, String vPrenom) {
    	nom = new SimpleStringProperty(vNom);
    	prenom = new SimpleStringProperty(vPrenom);
    }
    
   /* public Client(String code, String nom, String prenom) {
		// TODO Auto-generated constructor stub
	}*/
	//////////////////////////////////////////
    //method pour lire et recuperer des enregistrements
    public void lireRecupCRUD() {
    	try {
    	  Statement state = laConnexion.createStatement();
    	  ResultSet rs = state.executeQuery("SELECT * " + 
    	   "FROM clients ORDER BY nom");
    	  while (rs.next()) {
    		  String code = rs.getString("code");
    		  String nom = rs.getString("nom");
    		  String prenom = rs.getString("prenom");
    		  System.out.print(nom);
    		  System.out.println(prenom); 
    		 // boolean carte_fidele = rs.getBoolean("carte_fidele");
    		 // Date day = rs.getDate("date");
    	  
    	  //Ajout à l'ArrayList
    	  lesEnreg.add(new Client(code, nom, prenom /*, 
    			  carte_fidele, day*/));
    	  
    	  }	
    	}catch(SQLException e){
    		JOptionPane.showMessageDialog(null, 
    				"Problème rencontré : " +e.getMessage(),
    				"Résultat", JOptionPane.ERROR_MESSAGE) ;
    		}
    }
    ///////////////////////////////////////////
    // Ajout d'un nouveau client
    public boolean creerCRUD(String vCode, String vNom, 
    		String vPrenom/*, int vCarte_fidele, String vDate*/) {
    	boolean bCreation = false;
    	String requete = null;
    	try {
    		
    	  requete = "INSERT INTO clients (code, nom, prenom) VALUES ('"+vCode+"','"+vNom+"','"+vPrenom+"')";//,'"
    		//+ vCarte_fidele + "','"
    		//+ vDate         + "','"
    		//+")";
    	   
    	   Statement state = laConnexion.createStatement();
    	   state.executeUpdate(requete);
    	   bCreation = true;	
    	}
    	catch(SQLException e) { 
    		JOptionPane.showMessageDialog(null,
    			"Ajout dans la Base de donnée non effectué : " + e.getMessage(),
    			"Problème rencontré------------------", JOptionPane.ERROR_MESSAGE );    		
    	}
    	return bCreation;
    }
    
    ///////////////////////////////////////////////////////////////////////
    //MODIFICATION DONNEES CLIENT
    public boolean modifierCRUD(String vCode, String vNom,
    		String vPrenom/*, int vCarte_fidele, String vDate*/) {
    	boolean bModification = true;
    	String requete = null;
    	try {
    		requete = "UPDATE clients SET  nom = '" + vNom + "', prenom = '"+ vPrenom + "' WHERE code = '"  + vCode + "'";
    			//+ "carte_fidele = '" + vCarte_fidele + "',"
    			//+ "date = '"         + vDate + "',"
    			
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
    // SUPPRESSION d'un client 
    public boolean supprimerCRUD(String vCode) {
    	boolean bSuppression = true; 
    	
    	// Vérifier avant qu'il n'existe aucune commande
    	try {
    	  String requeteClient = "SELECT count(*) AS nbLignes FROM commandes "+ "WHERE code_client LIKE '" + vCode + "'";
    	  Statement state = laConnexion.createStatement();
  		  ResultSet jeuEnreg = state.executeQuery(requeteClient);
  		  int nbLignes = 0;
  		  jeuEnreg.next();
  		  nbLignes= jeuEnreg.getInt("nbLignes");
  		  if(nbLignes > 0) {
  			  bSuppression = false;
  			  JOptionPane.showMessageDialog(null, 
  					" Il existe des commandes pour ce client." + " Suppression interdite. ",
  			  		"Résultat", JOptionPane.ERROR_MESSAGE);
  			bSuppression = false;
  		  }
  		  else {
  			  JOptionPane.showMessageDialog(null,
  			     "Aucune commande pour ce client." + 
  			     "Supression Autorisée. ", 
  			     "Résultat" ,JOptionPane.INFORMATION_MESSAGE);
  		  }
    	}
    	catch(SQLException e) {
    		bSuppression = false;
    		JOptionPane.showMessageDialog(null,
    				"Aucune Suppession effectuée dans la BD : "
    				+ e.getMessage(),
    				"Problème rencontré", JOptionPane.ERROR_MESSAGE);
    	}
    	if (bSuppression == true) {
    		try {
    			String requete = "DELETE FROM clients" +
    		              "WHERE Code = '" +vCode + "'";
    			Statement state = laConnexion.createStatement();
    			int nbEnregSup = state.executeUpdate(requete);
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
    	}
    	return bSuppression;
    }
///////////////////////////////////////////////////////////////
    // 1ère Recherche 
    public ArrayList<Client> chercherCRUD(String vCode, 
    		String vNom, String vPrenom){
    	if(vCode.equals("")) {
    		vCode = "%";
    	}
    	if(vNom.equals("")) {
    		vNom = "%";
    	}
    	if(vPrenom.equals("")) {
    		vPrenom = "%";
    	}
    	String requete = "SELECT * FROM clients"
    			+ " WHERE code LIKKE '" + vCode + "'"
    			+ " AND nom LIKE '"     + vNom + "'"
    			+ " AND prenom LIKE '"  +vPrenom +",";
    	try {
    		Statement state = laConnexion.createStatement();
    		ResultSet rs = state.executeQuery(requete);
    		while (rs.next()) {
    			String code = rs.getString("code");
    			String nom = rs.getString("nom");
    			String prenom = rs.getString("prenom");
    			//boolean carte_fidele = rs.getBoolean("cvarte_fidele");
    			//Date date_creation = rs.getDate("date");
    			// Ajout à l'ArrayList
    			lesEnreg.add(new Client(code, nom, prenom/*, 
    					carte_fidele, date_creation*/));
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
    //Deuxième Recherche
     public ArrayList<Client> chercherCRUD (String recherche){
    	 String requete= "select * from clients where nom like '%"+recherche+"%' or prenom like '"+recherche+"' or code like '"+recherche+"'";
    	 try { 
    		 Statement state = laConnexion.createStatement();
    		 ResultSet rs = state.executeQuery(requete);
    		 while(rs.next()) {
    			 String code = rs.getString("code");
    			 String nom = rs.getString("nom");
    			 String prenom = rs.getString("prenom");
    			 //boolean carte_fidele = rs.getBoolean("carte_fidele");
    			 //Date date_creation = rs.getDate("date");
    			// Ajout à l'ArrayList
     			lesEnreg.add(new Client(code, nom, prenom/*, 
     					carte_fidele, date_creation*/));
    		 }
    		 
    	 }
    	 catch(SQLException e){
     		JOptionPane.showMessageDialog(null,
     				"Problème activé pendant la recherche"
     				+ e.getMessage(), "Résultat", 
     				JOptionPane.ERROR_MESSAGE);
    	 }
    	 return lesEnreg;
     }
     public HashMap<String, Client> listeDeClient()
		{
			try {
				Statement state= laConnexion.createStatement();
				ResultSet rs=state.executeQuery("select * from clients");
				while(rs.next())
				{
					String code=rs.getString("code");
					String codeclient=rs.getString("nom");
					String prenom=rs.getString("prenom");
					/*int quantite=rs.getInt("quantite");
					double prix_unitaire=rs.getDouble("prix_unitaire");
					Date date_creation=rs.getDate("date");*/
					
					listeClient.put(code, new Client(code, codeclient, prenom));
				
				}
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
			}
			return listeClient;
		}   
    
}