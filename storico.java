package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Servlet implementation class storico
 */
@WebServlet("/storico")
public class storico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public storico() {
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
		
		 ServletContext context = getServletContext();
		 
	       String utente = (String) context.getAttribute("user");
	       
	       String nomeTabellaDaCercare = utente.split("@")[0];
	       boolean trovata = false;
	       try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1")) {
	    	    // Query per recuperare le informazioni sulle tabelle del database
	    	    java.sql.DatabaseMetaData metaData = conn.getMetaData();
	    	    ResultSet rs = metaData.getTables(null, null, "%", null);

	    	    // Iterazione attraverso i risultati per trovare la tabella desiderata
	    	    while (rs.next()) {
	    	        String nomeTabella = rs.getString("TABLE_NAME");
	    	        if (nomeTabella.equalsIgnoreCase(nomeTabellaDaCercare)) {
	    	            System.out.println("Tabella trovata: " + nomeTabella);
	    	            trovata = true;
	    	           
	    	        }
	    	    }
	    	} catch (SQLException e) {
	    	    e.printStackTrace();
	    	}
	       String cerca =  "SELECT * FROM " + nomeTabellaDaCercare;
	       ArrayList <String> lista = new ArrayList<>();
	       
	       if (trovata == true) {
	    	   
	    	   try (Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1");
	                    Statement stmt = conn1.createStatement()) {
	    		   ResultSet rs = stmt.executeQuery(cerca);

	    	        // Elaborazione dei risultati
	    	        while (rs.next()) {
	    	            // Recupera i valori delle colonne desiderate
	    	            String titolo = rs.getString("titolo");
	    	            lista.add(titolo);
	    	            // Aggiungi qui altre operazioni sui risultati se necessario
	    	            System.out.println("Titolo: " + titolo);
	                 
	               } }catch (SQLException e) {
	                   // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
	                   System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
	                   e.printStackTrace();
	               }
	    	   request.setAttribute("lista", lista);
	    	   request.getRequestDispatcher("/storico.jsp").forward(request, response);
	       }


			    
			    // Chiusura delle risorse
			   
	}

}
