package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registra
 */
@WebServlet("/registra")
public class registra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
       
        String url = "jdbc:mysql://localhost:3306/"; // URL del database
	    String dbName = "mydb"; // Nome del database
	    String user = "root"; // Nome utente
	    String password = "Ilfoggia1"; //
        List<utente> listaUtenti = new ArrayList<>();
        
        
        String selectQuery = "SELECT * FROM utenti";
       
        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(selectQuery)) {

	            // Iterazione sui risultati e lettura dei dati
	            while (rs.next()) {
	                int id = rs.getInt("idutenti");
	                String utente = rs.getString("mail");
	                
	                
	                // Creazione di un nuovo oggetto Prodotto e aggiunta alla lista
	                utente l1 = new utente();
	                
	               l1.setId(id);
	               l1.setMail(utente);
	               
	               listaUtenti.add(l1);
	            }

	            // Stampa dei prodotti nella lista
	            

	        } catch (SQLException e) {
	            // Gestione delle eccezioni per la connessione al database o la lettura dei dati
	            System.out.println("Errore durante la lettura dei dati dalla tabella 'prodotti':");
	            e.printStackTrace();
	        }
        boolean ok = true;
        for (int i = 0; i < listaUtenti.size(); i++) { 
        	System.out.println(listaUtenti.get(i).getMail());      if (listaUtenti.get(i).getMail() == mail) {
           ok = false;
        }}
        if (ok == true) {
        String insertQuery1 = "INSERT INTO utenti (mail, password) VALUES (?, ?)";
		// Informazioni di connessione al database MySQL
	   
	    try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
        		PreparedStatement stmt = conn.prepareStatement(insertQuery1))
        		
        		
        		{
        	stmt.setString(1, mail);
        	stmt.setString(2, pass);
        	
        	
        	
        	
            // Esecuzione della query per l'inserimento dei dati
            int rowsAffected = stmt.executeUpdate();

            // Stampa il numero di righe aggiornate
            System.out.println("Numero di righe aggiornate: " + rowsAffected);

        } catch (SQLException e) {
            // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
            System.out.println("Errore durante l'inserimento dei dati nella tabella 'utenti':");
            e.printStackTrace();
        }
	    
	    }
        else {
        	System.out.println("Utente già presente");
        }
        
        
        
	    String username = mail.split("@")[0]; 
	    String insertQuery2 = "CREATE TABLE IF NOT EXISTS " + username + " (" +
	    	    "id INT AUTO_INCREMENT PRIMARY KEY," +
	    	    "titolo VARCHAR(255) NOT NULL" +
	    	")";

	    	try (Connection conn1 = DriverManager.getConnection(url + dbName, user, password);
	    	    PreparedStatement stmt = conn1.prepareStatement(insertQuery2)) { // Usa insertQuery2 invece di insertQuery1

	    	    // Esecuzione della query per la creazione della tabella
	    	   stmt.execute();

	    	    

	    	} catch (SQLException e) {
	    	    // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
	    	    System.out.println("Errore durante la creazione della tabella:");
	    	    e.printStackTrace();
	    	}

	    
        
        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
        request.getRequestDispatcher("/risultato.jsp").forward(request, response);
	}
	}


