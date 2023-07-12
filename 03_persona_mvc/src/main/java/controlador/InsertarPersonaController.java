package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Persona;

@WebServlet("/InsertarPersonaController")
public class InsertarPersonaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1.- Obtener datos que me envían en la solicitud
		
		//2.- Llamo al Modelo para obtener datos
				
		//3.- Llamo a la Vista
		response.sendRedirect("jsp/insertarpersona.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1.- Obtener datos que me envían en la solicitud
		Integer id = Integer.parseInt( request.getParameter("txtId") );
		String nombre = request.getParameter("txtNombre");
		String password = request.getParameter("txtClave");
		
		//2.- Llamo al Modelo para obtener datos
		Persona persona = new Persona();
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setPassword(password);
		
		Persona modeloPersona = new Persona();
		modeloPersona.create(persona);
						
		//3.- Llamo a la Vista
		response.sendRedirect("ListarPersonaController");
	}

}
