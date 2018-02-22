package dialogue;

public class Parametres {
	private String nomUtilisateur;
	private String motDePasse;
	private String serveurBD;
	private String driverSGBD;

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public String getServeurBD() {
		return serveurBD;
	}

	public void setServeurBD(String serveurBD) {
		this.serveurBD = serveurBD;
	}

	public String getDriverSGBD() {
		return driverSGBD;
	}

	public void setDriverSGBD(String driverSGBD) {
		this.driverSGBD = driverSGBD;
	}

	// constructeur
	public Parametres() {
		nomUtilisateur = "root";
		motDePasse = "";
		driverSGBD = "com.mysql.jdbc.Driver";
		serveurBD = "jdbc:mysql://localhost/ogidni";
	}
	// NOM :MySQL (Connector/J driver) (1)
	// Driver : com.mysql.jdbc.Driver
}
