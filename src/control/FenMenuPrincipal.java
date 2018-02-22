package control;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author aba
 */

public class FenMenuPrincipal implements Initializable {
	private Principal fenCon;
	private Principal mainApp;
	public void MainApp(Principal mainApp) {
	       this.mainApp = mainApp;
	}
	public void setMainApp(Principal fenConn) {
		this.fenCon = fenConn;
		//tableVue.setItems(Principal.getUtilisateurData());
	}
	
	
	/*private void handleButtonAction(ActionEvent event) {
		Dowl.setText("Abou!");
		NameUser.setText("Connexion Reussie!");
	}*/
	@FXML
	private void ButtonBtnClient(ActionEvent event) throws IOException  {
		fenCon.NewFenTableClient();
	}
	
	@FXML
	private void ButtonBtnArticle(ActionEvent event) throws IOException  {
		fenCon.NewFenTableArticle();
	}
	@FXML
	private void ButtonBtnComande(ActionEvent event) throws IOException  {
		fenCon.NewFenTableCommande();
	}
	@FXML
	private void ButtonBtnFenConnexion(ActionEvent event) throws IOException  {
		fenCon.showPersonOverview();
	}
	@FXML
	private void ButtonBtnMenu(ActionEvent event) throws IOException  {
		fenCon.showPersonOverview();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
}
