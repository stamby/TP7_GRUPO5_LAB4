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

@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletSeguro() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtener ultimo ID de Seguro al entrar en Agregar Seguro
		if(request.getParameter("Param")!=null) {
			SeguroDao sd = new SeguroDao();
			int ultimoId=0;
			ArrayList<Seguro> lista= sd.obtenerSeguros();
			for(Seguro seg: lista) {
				ultimoId=seg.getIdSeguro();
			}
			request.setAttribute("ultimoId", ultimoId+1);
			RequestDispatcher rd = request.getRequestDispatcher("/servletTipoSeguro?Param2=2");   
	        rd.forward(request, response);
		}
		//Agregar Seguro a Base de datos
		if(request.getParameter("btnAceptar")!=null) {
			int filasAfectadas=0;
			SeguroDao sd = new SeguroDao();
			String descripcion = request.getParameter("txtDescripcion");
			int idTipo = Integer.valueOf(request.getParameter("tipoSeguro"));
			float costoContratacion = Float.parseFloat(request.getParameter("txtCostoContratacion"));
			float costoMaximo = Float.parseFloat(request.getParameter("txtCostoMaximo"));
			
			Seguro s = new Seguro(descripcion, idTipo, costoContratacion, costoMaximo);
			filasAfectadas = sd.agregarSeguro(s);
			request.setAttribute("filasAfectadas", filasAfectadas);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
	        rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
