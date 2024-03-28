package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
       
       
        
        // Creazione di un oggetto DatiModulo e impostazione dei valori
       
        
        
     // Ottieni l'oggetto ServletContext
       

        // Aggiungi il nuovo oggetto DatiModulo all'ArrayList
        

       
        // Esegui eventuali operazioni aggiuntive qui...
        
        // Passa l'oggetto datiModulo alla JSP per l'elaborazione successiva
       
        
        String insertQuery1 = "INSERT INTO utenti (mail, password) VALUES (?, ?)";
		// Informazioni di connessione al database MySQL
	    String url = "jdbc:mysql://localhost:3306/"; // URL del database
	    String dbName = "mydb"; // Nome del database
	    String user = "root"; // Nome utente
	    String password = "Ilfoggia1"; 
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
	    
        
        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
        request.getRequestDispatcher("/risultato.jsp").forward(request, response);
	}
	}


