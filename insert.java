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
 * Servlet implementation class insert
 */
@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
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
		String titolo = request.getParameter("titolo");
        
        String autore = request.getParameter("autore");
        String anno1 = request.getParameter("anno");
        int anno = Integer.parseInt(anno1);
       
        
        // Creazione di un oggetto DatiModulo e impostazione dei valori
        libro p1 = new libro();
        p1.setTitolo(titolo);
        p1.setAutore(autore);
        p1.setAnno(anno);
        p1.setStato("disponibile");
        p1.setIdUtente("");
        
        
     // Ottieni l'oggetto ServletContext
        ServletContext context = getServletContext();

        // Ottieni l'ArrayList dalla ServletContext, se già esiste, altrimenti crea un nuovo ArrayList
        ArrayList<libro> listaDati = (ArrayList<libro>) context.getAttribute("listaDati");
        if (listaDati == null) {
            listaDati = new ArrayList<>();
        }

        // Aggiungi il nuovo oggetto DatiModulo all'ArrayList
        listaDati.add(p1);

        // Memorizza l'ArrayList nell'oggetto ServletContext
        context.setAttribute("listaDati", listaDati);
        
        // Esegui eventuali operazioni aggiuntive qui...
        
        // Passa l'oggetto datiModulo alla JSP per l'elaborazione successiva
        request.setAttribute("datiModulo", p1);
        
        String insertQuery1 = "INSERT INTO biblio (titolo, autore, anno, stato,idUtente) VALUES (?, ?, ?,?,?)";
		// Informazioni di connessione al database MySQL
	    String url = "jdbc:mysql://localhost:3306/"; // URL del database
	    String dbName = "mydb"; // Nome del database
	    String user = "root"; // Nome utente
	    String password = "Ilfoggia1"; 
	    try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
        		PreparedStatement stmt = conn.prepareStatement(insertQuery1))
        		
        		
        		{
        	stmt.setString(1, titolo);
        	stmt.setString(2, autore);
        	stmt.setInt(3, anno);
        	stmt.setString(4, p1.getStato());
        	stmt.setString(5, p1.getIdUtente());
        	
        	
        	
            // Esecuzione della query per l'inserimento dei dati
            int rowsAffected = stmt.executeUpdate();

            // Stampa il numero di righe aggiornate
            System.out.println("Numero di righe aggiornate: " + rowsAffected);

        } catch (SQLException e) {
            // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
            System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
            e.printStackTrace();
        }
	    
        
        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
        request.getRequestDispatcher("/risultato.jsp").forward(request, response);
	}
	}


