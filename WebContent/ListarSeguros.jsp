<%@page import="dominio.Seguro"%>
<%@page import="dominio.SeguroDao"%>
<%@page import="dominio.TipoSeguro"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	

	
	
	<title>Lista de seguros</title>
</head>
<body>
<a href="Inicio.jsp">Inicio</a>
<a href="servletSeguro?Param=1">Agregar Seguro</a>
<a href="ListarSeguros.jsp">Listar Seguros</a><br>
	<h1>Tipos de seguros de la base de datos</h1>
	<% String mensaje="Hola" ; %>
	<%
	SeguroDao sdao = new SeguroDao();
	ArrayList<Seguro> listaSeguros = null;
	String idTipoSeleccionado = "";
	
	idTipoSeleccionado = (request.getAttribute("idTipoSeleccionado")!=null) ? request.getAttribute("idTipoSeleccionado").toString() : "";
	listaSeguros = (request.getAttribute("listaSeguros")!=null) ? (ArrayList<Seguro>)request.getAttribute("listaSeguros") : sdao.obtenerSeguros();
	
	%>
	
	<form method="post" action="servletTipoSeguro">
		Busqueda por tipo de seguros: 
		<select name="idTipo">
			<option value="" <%= (idTipoSeleccionado.equals("") ) ? "selected" : "" %>>Todos</option>
			
			<%
				TipoSeguroDao tiposegDao = new TipoSeguroDao();
				ArrayList<TipoSeguro> listaTipoSeguro = tiposegDao.obtenerTiposSeguros();
				
				if(listaTipoSeguro!=null){
					for(TipoSeguro tiposeg : listaTipoSeguro) {
						String value = Integer.toString(tiposeg.getIdTipo());
						String descripcion = tiposeg.getDescripcion();
			%>
						<option value="<%= value %>" <%= (idTipoSeleccionado.equals(value)) ? "selected" : "" %>><%=descripcion %> </option>
			<%	
					}
				}
			%>
			
		</select>
		<button type="submit" name="btnFiltrar">Filtrar</button>
	</form>
	
	
	<br><br>
	
	
	<table border="1" width="900">
		<tr>
			<th>ID seguro</th>
			<th>Descripcion seguro</th>
			<th>Costo contratacion</th>
			<th>Costo maximo asegurado</th>
			<th></th>
		</tr>
		
		<%  if(listaSeguros!=null)
			for(Seguro seguro : listaSeguros) 
			{
		%>
		<tr>  
		     <td><%=seguro.getIdSeguro() %></td>
		     <td><%=seguro.getDescripcion() %></td>
		     <td><%=seguro.getCostoContratacion() %></td>
		     <td><%=seguro.getCostoAsegurado() %></td>  
		</tr>
		<%  } %>
	
	</table>
</body>
</html>