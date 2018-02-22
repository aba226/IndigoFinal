package dialogue;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Connection;
//import Parametres;
//import com.sun.rowset.CachedRowSetImpl;

//import entite.ModlCli;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

public class ControleConnexion {
	static Parametres lesParametres;
	static boolean etatConnexion;
	static Connection laConnectionStatique;
	static {
		boolean ok = true;
		lesParametres = new Parametres();
		try {
			Class.forName(lesParametres.getDriverSGBD());
			etatConnexion = true;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Classes non trouvées" + "pour le chargement " + "du pilote JDBC MySQL",
					"ALERTE", JOptionPane.ERROR_MESSAGE);
			ok = false;
			etatConnexion = false;
		}
		// 2. Etablissement de la connexion
		// -----------------------------
		if (ok == true) {
			try {
				// récupération des parametres présents
				// dans la classe Parametres
				String urlBD = lesParametres.getServeurBD();
				String nomUtilisateur = lesParametres.getNomUtilisateur();
				String MDP = lesParametres.getMotDePasse();
				// Création d'une connexion
				// contenant les parametres de connexion
				laConnectionStatique = (Connection) DriverManager.getConnection(urlBD, nomUtilisateur, MDP);
				etatConnexion = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter " + "à la base de données", "ALERTE",
						JOptionPane.ERROR_MESSAGE);
				etatConnexion = false;
			}
		}
	}

	public ControleConnexion() {
	}

	public static Parametres getParametres() {
		return lesParametres;
	}

	public static boolean getControleConnexion() {
		return etatConnexion;
	}

	public static Connection getConnexion() {
		return laConnectionStatique;
	}

	public static boolean controle(String Nom, String MotDePasse) {
		// 1. Vérification de la saisie
		// -------------------------------
		boolean verificationSaisie;
		if (Nom.equals(lesParametres.getNomUtilisateur()) && MotDePasse.equals(lesParametres.getMotDePasse())) {
			verificationSaisie = true;
		} else {
			/*JOptionPane.showMessageDialog(null, "verifier votre saisie.", "ERREUR", JOptionPane.ERROR_MESSAGE);*/
			verificationSaisie = false;
		}
		return verificationSaisie;
	}

	public static void fermetureSession() {
		try {
			laConnectionStatique.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problème rencontré" + "à la fermeture de la connexion", "ERREUR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void transfertDonnees() throws SQLException {
		// simple parcours de jeu d'enregistrements
		System.out.println("Parcours du jeu d'enregistrements");
		System.out.println("--------------------------------");

		// Interface Statement : pour transmettre des instructions
		// SQL simples
		// la fermerture d'un Statement engendre la fermeture
		// automatique de tous Les Resultats associés
		Statement leStatement = null;
		// jeu d'enregistrement
		ResultSet jeuEnreg = null;
		// variables
		String vCode = "";
		String vNom = "";
		String vPrenom = "";
		Boolean vCarteFidele = false;
		String vDate = "";
		String vAdresse = "";
		String vCodePostale = "";
		String vVille = "";
		String vTelFix = "";
		String vMobilis = "";
		String vEmail = "";
		String vRemarques = "";
		
		
		String chaineSQL = "select * from clients";

		leStatement = laConnectionStatique.createStatement();
		jeuEnreg = leStatement.executeQuery(chaineSQL);

		while (jeuEnreg.next()) {
			// utilisation des n° de colonnes
			vCode = jeuEnreg.getString(1);
			vNom = jeuEnreg.getString(2);
			// ou des noms de colonnes
			vPrenom = jeuEnreg.getString("prenom");
			vCarteFidele = jeuEnreg.getBoolean(4);
			vDate = jeuEnreg.getString(5);
			vAdresse = jeuEnreg.getString(6);
			vCodePostale = jeuEnreg.getString(7);
			vVille = jeuEnreg.getString(8);
			vTelFix = jeuEnreg.getString(9);
			vMobilis = jeuEnreg.getString(10);
			vEmail = jeuEnreg.getString(11);
			vRemarques = jeuEnreg.getString(12);
			System.out.println(vCode + "," + vNom 
			 + "," + vPrenom + "," + vCarteFidele 
			 + "," + vDate
			 + "," + vAdresse
			 + "," + vCodePostale
			 + "," + vVille
			 + "," + vTelFix
			 + "," + vMobilis
			 + "," + vEmail
			 + "," + vRemarques);
		}
		///////////////////////////////////////////////////
		
	        //Declare a observable List which comprises of Employee objects
	       /* ObservableList<ModlCli> empLis = FXCollections.observableArrayList();
	        ModlCli empo = new ModlCli();
	        while (jeuEnreg.next()) {
	           
	           // emp.setEmployeeId(rs.getString(1));
	            empo.setFirstName(jeuEnreg.getString(2));
	            empo.setLastName(jeuEnreg.getString(3));
	            empo.setEmail(jeuEnreg.getString(4));
	            empo.setPhoneNumber(jeuEnreg.getString(5));
	            emp.setHireDate(rs.getDate(5));
	            emp.setJobId(rs.getString("JOB_ID"));
	            emp.setSalary(rs.getInt("SALARY"));
	            emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
	            emp.setManagerId(rs.getInt("MANAGER_ID"));
	            emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
	            //Add employee to the ObservableList
	             
	            empLis.add(empo);   */
	            
	        /*}
	        System.out.println("c'est quoi ");
            System.out.println(empo);
            System.out.println("c'est qui ");
            System.out.println(empLis );
	       
	        //return empList (ObservableList of Employees)
	     */  
	    
		///////////////////////////////////////////////////
	}
	 //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
    	/*
    	Statement leStatement = null;
		ResultSet jeuEnreg = null;
        */
        Statement stmt = null; 
        ResultSet resultSet = null;
       // CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
           // dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");
            
            stmt = laConnectionStatique.createStatement();
    	   //Create statement
           // stmt = conn.createStatement();
 
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
            ///////////////////////////////////
            String vCode = "";
    		String vNom = "";
    		String vPrenom = "";
    		Boolean vCarteFidele = false;
    		String vDate = "";
    		String vAdresse = "";
    		String vCodePostale = "";
    		String vVille = "";
    		String vTelFix = "";
    		String vMobilis = "";
    		String vEmail = "";
    		String vRemarques = "";
            while (resultSet.next()) {
    			// utilisation des n° de colonnes
    			vCode = resultSet.getString(1);
    			vNom = resultSet.getString(2);
    			// ou des noms de colonnes
    			vPrenom = resultSet.getString("prenom");
    			vCarteFidele = resultSet.getBoolean(4);
    			vDate = resultSet.getString(5);
    			vAdresse = resultSet.getString(6);
    			vCodePostale = resultSet.getString(7);
    			vVille = resultSet.getString(8);
    			vTelFix = resultSet.getString(9);
    			vMobilis = resultSet.getString(10);
    			vEmail = resultSet.getString(11);
    			vRemarques = resultSet.getString(12);
    			System.out.println(vCode + "," + vNom 
    			 + "," + vPrenom + "," + vCarteFidele 
    			 + "," + vDate
    			 + "," + vAdresse
    			 + "," + vCodePostale
    			 + "," + vVille
    			 + "," + vTelFix
    			 + "," + vMobilis
    			 + "," + vEmail
    			 + "," + vRemarques);
            ///////////////////////////////////
 
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
           // crs = new CachedRowSetImpl();
           // crs.populate(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            //dbDisconnect();
        }
        //Return CachedRowSet
       // return crs;
        return resultSet;
    }

}
