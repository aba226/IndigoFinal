package entite;

//les differentes importations
//pour la structure d'accueil des données
import java.util.ArrayList;
//pour la gestion de la propriété Date
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

//pour la connexion à la base de Données
import java.sql.Connection;
import dialogue.ControleConnexion;
//pour les requetes SQL
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mode_Reglements {
	
	private String code;
	private String type;
	private Client code_client;
	private double total_ttc;
	private int codeModeReglement;
	private Mode_Reglements mode_reglement;
	private Date date;
	private static Connection laConnexion = 
			ControleConnexion.getConnexion();
	
	//CONSTRUCTEUR 3 pour initier la RECCHERCHE
		public Mode_Reglements (String type) {
			this.type = type ;
		}
		//Accesseurs 
		public String getType() {
			return type;
		}
		private HashMap<String ,Mode_Reglements> listeModeReglement = new HashMap<>();
		 public HashMap<String, Mode_Reglements> listeDeModeReglement()
			{
				try {
					Statement state= laConnexion.createStatement();
					ResultSet rs=state.executeQuery("select * from articles");
					while(rs.next())
					{
						String type=rs.getString("type");
						
						
						listeModeReglement.put(type, new Mode_Reglements(type));
					
					}
				} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
				}
				return listeModeReglement;
			}   

}
