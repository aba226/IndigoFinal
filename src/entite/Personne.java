package entite;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Personne {
	StringProperty code;
	StringProperty nom;
	StringProperty prenom;
	
	public String getCode() {
		return code.get();
	}
	public StringProperty codeProperty() {
		return code;
	}
	
	public void setCode(String code) {
		this.code.set(code);
	}
	
	public String getNom() {
		return nom.get();
	}
	public StringProperty nomProperty() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public String getPrenom() {
		return prenom.get();
	}
	public StringProperty prenomProperty() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}
	
	// 1er constructeur
	public Personne (String vCode, String vNom, String vPrenom){
		code = new SimpleStringProperty(vCode);
		nom = new SimpleStringProperty(vNom) ;
		prenom = new SimpleStringProperty(vPrenom);
	}
	// 2ème constructeur pour les recherches
	public Personne(String vCode) {
		code = new SimpleStringProperty(vCode);
	}
	// 3ème constructeur pour une simple lecture de collection
	public Personne () {
		
	}
}
