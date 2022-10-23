package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Seguro;
import dominio.SeguroDao;
import dominio.TipoSeguro;
import dominio.TipoSeguroDao;

@WebServlet("/servletTipoSeguro")
public class servletTipoSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletTipoSeguro() {
    	 super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtener lista de Tipos de seguros al entrar a Agregar Seguro
		if(request.getParameter("Param2")!=null) {
			TipoSeguroDao tpsDao = new TipoSeguroDao();
			ArrayList<TipoSeguro> listaTipos = tpsDao.obtenerTiposSeguros();
			request.setAttribute("listaTipos", listaTipos);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
	        rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Seguro> listaSeguros = null;
		SeguroDao sdao = new SeguroDao();
		
		
		String idTipoSeleccionado = "";
		
		if (request.getParameter("btnFiltrar")!=null) {
			idTipoSeleccionado = request.getParameter("idTipo"); 
		}
		
		if (idTipoSeleccionado.equals("")) {
			listaSeguros = sdao.obtenerSeguros();
		} else {
			
			listaSeguros = sdao.obtenerSeguros(idTipoSeleccionado);
			
		}
		
		request.setAttribute("idTipoSeleccionado", idTipoSeleccionado);
		request.setAttribute("listaSeguros", listaSeguros);
		RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
		rd.forward(request, response);
			

	}

}
