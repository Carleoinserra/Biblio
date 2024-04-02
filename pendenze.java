package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pendenze
 */
@WebServlet("/pendenze")
public class pendenze extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pendenze() {
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
		
		
		String cerca =  "SELECT * FROM biblio";
	       ArrayList <String> lista = new ArrayList<>();
	       ServletContext context = getServletContext();
			
			
			String user = (String) context.getAttribute("user");
			
	      
	    	   
	    	   try (Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1");
	                    Statement stmt = conn1.createStatement()) {
	    		   ResultSet rs = stmt.executeQuery(cerca);

	    	        // Elaborazione dei risultati
	    	        while (rs.next()) {
	    	            // Recupera i valori delle colonne desiderate
	    	            String utente = rs.getString("idUtente");
	    	            if (utente.equals(user)) {
	    	            
	    	          
	    	            lista.add(rs.getString("titolo"));
	    	            // Aggiungi qui altre operazioni sui risultati se necessario
	    	            System.out.println("Titolo: " + rs.getString("titolo"));
	                 
	                }}}
	    	        
	    	        catch (SQLException e) {
	                   // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
	                   System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
	                   e.printStackTrace();
	               }
	    	   request.setAttribute("lista", lista);
	    	   request.getRequestDispatcher("/pendenze.jsp").forward(request, response);
	       


			    
			    // Chiusura delle risorse
			   
	} }
	


