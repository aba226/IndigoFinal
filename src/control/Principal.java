package control;

import java.io.IOException;
import java.util.ArrayList;

import entite.Article;
import entite.Client;
import entite.Person;
import entite.Utilisateur;
//import entite.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Principal extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    // ... AFTER THE OTHER VARIABLES ...

    /**
     * Clients .
     */
    private ArrayList<Client> clientList = new ArrayList<Client>();
    private ObservableList<Client> clientListOb = FXCollections.observableArrayList(clientList);
    //Article
    private static ArrayList<Article> articleList = new ArrayList<Article>();
    private static ObservableList<Article> articleListOb = FXCollections.observableArrayList(articleList);
    //Utilisateurs
    private static ArrayList<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    private static ObservableList<Utilisateur> utilisateurListOb = FXCollections.observableArrayList(utilisateurList);
    /**
     * Constructor
     */
    public Principal() {
    	clientListOb = FXCollections.observableArrayList((new Client()).getlesEnreg());
    	articleListOb = FXCollections.observableArrayList((new Article()).getLesEnreg());
    	utilisateurListOb = FXCollections.observableArrayList((new Utilisateur()).getLesEnreg());
    	}
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Client> getPersonData() {
    	return clientListOb;
       }
    public static ObservableList<Article> getArticleData() {
    	
    	return articleListOb;
    }
public static ObservableList<Utilisateur> getUtilisateurData() {
    	
    	return utilisateurListOb;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        this.primaryStage.setTitle("SARL INDIGO");
        
        initRootLayout();
        
        showPersonOverview();
       
    }

    /**
     * Initializes the root layout.
     */
    
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
           // System.out.println("Bonjour");
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("FXMFenCo.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the control access to the main app.
            FenConn controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void administrateur() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("FXMFenTableAdministrateur.fxml"));//
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the control access to the main app.
            FenTableAdministrateur controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void NewFenMenuPrincipal() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("FXMFenMenPrincip.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the control access to the main app.
            FenMenuPrincipal controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void NewFenTableClient() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("FXMFenTableClie.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the control access to the main app.
            FenTableClient controller = loader.getController();
        
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void NewFenTableArticle() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("FXMFenTableArticle.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the control access to the main app.
            FenTableArticle controller = loader.getController();
        
            controller.MainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void NewFenTableCommande() {
	try {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("FXMFenTableComm.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        
        // Set person overview into the center of root layout.
        rootLayout.setCenter(personOverview);

        // Give the control access to the main app.
        FenTableCommande controller = loader.getController();
    
        controller.MainApp(this);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void NewFenFichierDesCommandes() {
    try {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("FXMFenFichierDesCommandes.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        
        // Set person overview into the center of root layout.
        rootLayout.setCenter(personOverview);

        // Give the control access to the main app.
        FenFichierDesCommandes controller = loader.getController();
    
        controller.MainApp(this);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}