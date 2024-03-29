package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class submit
 */
@WebServlet("/submit")
public class submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submit() {
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
		 String utente = request.getParameter("mail");
		 String pass = request.getParameter("pass");
		 System.out.println(utente);
		 System.out.println(pass);
		 ServletContext context = getServletContext();
		 
	        context.setAttribute("user", utente);
	        ArrayList <utente> listaUtenti = new ArrayList<>();
	        String selectQuery1 = "SELECT * FROM utenti";
	        String url = "jdbc:mysql://localhost:3306/"; // URL del database
		    String dbName = "mydb"; // Nome del database
		    String user = "root"; // Nome utente
		    String password = "Ilfoggia1"; //
	        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(selectQuery1)) {

		            // Iterazione sui risultati e lettura dei dati
		            while (rs.next()) {
		                int id = rs.getInt("idutenti");
		                String mail = rs.getString("mail");
		                String pass1 = rs.getString("password");
		                
		                // Creazione di un nuovo oggetto Prodotto e aggiunta alla lista
		                utente l1 = new utente();
		                
		               l1.setId(id);
		               l1.setMail(mail);
		               l1.setPassword(pass1);
		               
		               
		               listaUtenti.add(l1);
		            }

		            // Stampa dei prodotti nella lista
		            

		        } catch (SQLException e) {
		            // Gestione delle eccezioni per la connessione al database o la lettura dei dati
		            System.out.println("Errore durante la lettura dei dati dalla tabella 'prodotti':");
		            e.printStackTrace();
		        }
	        boolean ok = false;
	        for (int i = 0; i < listaUtenti.size(); i++) {
	        	System.out.println(listaUtenti.get(i).getMail());
	        	System.out.println(listaUtenti.get(i).getPassword());
	        	if (listaUtenti.get(i).getMail().equals(utente) &&
	        			listaUtenti.get(i).getPassword().equals(pass)) {
	        		
	        		ok = true;
	        	}
	        }
		  
	        if (ok) {
		
	        List<libro> listaLibri = new ArrayList<>();
	        String selectQuery = "SELECT * FROM biblio";
	       
	        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(selectQuery)) {

		            // Iterazione sui risultati e lettura dei dati
		            while (rs.next()) {
		                int id = rs.getInt("id");
		                String titolo = rs.getString("titolo");
		                String autore = rs.getString("autore");
		                String stato = rs.getString("stato");
		                int anno = rs.getInt("anno");
		                
		                // Creazione di un nuovo oggetto Prodotto e aggiunta alla lista
		                libro l1 = new libro();
		                
		               l1.setTitolo(titolo);
		               l1.setAnno(anno);
		               l1.setAutore(autore);
		               l1.setStato(stato);
		               if (l1.getStato().equals("disponibile")) {
		               listaLibri.add(l1);
		            }}

		            // Stampa dei prodotti nella lista
		            

		        } catch (SQLException e) {
		            // Gestione delle eccezioni per la connessione al database o la lettura dei dati
		            System.out.println("Errore durante la lettura dei dati dalla tabella 'prodotti':");
		            e.printStackTrace();
		        }
	         request.setAttribute("lista", listaLibri);
	        
	        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
	        request.getRequestDispatcher("/risultato1.jsp").forward(request, response);
	        } 
	        else {
	        	request.getRequestDispatcher("/risultato2.jsp").forward(request, response);
	        }
	}

}
