package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import com.mysql.cj.jdbc.DatabaseMetaData;

/**
 * Servlet implementation class prenota
 */
@WebServlet("/prenota")
public class prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prenota() {
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
		
		
		String user = (String) context.getAttribute("user");
		
		
		
		String[] libriSelezionati = request.getParameterValues("ordini");
		
		
		if (libriSelezionati != null) {
			
			double somma = 0;
			
		    for (String libro : libriSelezionati) {
		
		
		String nomeTabellaDaCercare = user.split("@")[0];
		
		
		try {
		    // Connessione al database
		    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1");
		    
		    // Query per recuperare le informazioni sulle tabelle del database
		    java.sql.DatabaseMetaData metaData = conn.getMetaData();
		    ResultSet rs = metaData.getTables(null, null, "%", null);
		    
		    // Iterazione attraverso i risultati per trovare la tabella desiderata
		    while (rs.next()) {
		        String nomeTabella = rs.getString("TABLE_NAME");
		        if (nomeTabella.equalsIgnoreCase(nomeTabellaDaCercare)) {
		            System.out.println("Tabella trovata: " + nomeTabella);
		            
		            // Operazione di inserimento di un elemento nella tabella trovata
		            String insertQuery = "INSERT INTO " + nomeTabella + " (titolo) VALUES (?)";
		            PreparedStatement stmt = conn.prepareStatement(insertQuery);
		            
		            // Impostazione dei valori per le colonne nella query di inserimento
		            stmt.setString(1, libro);
		            
		            // Aggiungi altre impostazioni per le colonne se necessario
		            
		            // Esecuzione della query di inserimento
		            int rowsInserted = stmt.executeUpdate();
		            System.out.println("Numero di righe inserite: " + rowsInserted);
		            
		            // Chiusura delle risorse
		            stmt.close();
		            break;
		        }
		    }
		    
		    // Chiusura delle risorse
		    rs.close();
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		

		    	
		    	 try {
		    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1");
	            
	            // Query per aggiornare la quantità del prodotto selezionato
		    	String updateQuery = "UPDATE biblio " +
	                     "SET stato = 'non disponibile', idUtente = ? " +
	                     "WHERE titolo = ? AND stato = 'disponibile'";

	            
	            // Creazione dello statement preparato
	            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
	            pstmt.setString(1, user); // Assumendo che ordine sia l'ID del prodotto
	            pstmt.setString(2, libro);
	            // Esecuzione dell'aggiornamento
	            pstmt.executeUpdate();
	            
	            // Chiusura delle risorse
	            pstmt.close();
	            conn.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace(); // Gestisci eventuali errori di connessione o query SQL
	        }
		
		
		    }
		
	}}}


