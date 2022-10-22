<%@page import="dominio.SeguroDao"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@ page import="dominio.TipoSeguro" %>
<%@ page import="dominio.Seguro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Seguro</title>
</head>
<body>
<a href="Inicio.jsp">Inicio</a>
<a href="servletSeguro?Param=1">Agregar Seguro</a>
<a href="ListarSeguros.jsp">Listar Seguros</a><br>
<h3>Agregar seguros</h3>

<form action="servletSeguro" method="get">
<table>
	<tr> 
		<td style="width: 139px; ">Id Seguro</td>
		<td style="height: 0px; "><%
			Integer aux=0;
			if(request.getAttribute("ultimoId")!=null ){
				aux = (Integer)request.getAttribute("ultimoId");			
			}else {%>
				<%
				SeguroDao segDao = new SeguroDao();
				Seguro seguro = segDao.ObtenerIdSeg_Increment();
				aux = seguro.getIdSeguro();%>
			<%}%>
			
			<%=aux %></td>
	</tr>
	<tr> 
		<td>Descripcion </td>
		<td style="height: 0px; "><input type="text" name="txtDescripcion"/></td>
	</tr>
	<tr> 
		<td>Tipo de Seguro</td>
		<td style="height: 0px; ">
			<select name="tipoSeguro" >
						<%
						TipoSeguroDao tiposeguroDao = new TipoSeguroDao();
						ArrayList<TipoSeguro> listaTipos = tiposeguroDao.obtenerTiposSeguros();//(ArrayList<TipoSeguro>)request.getAttribute("listaTipos");
						String idTipo="";
						//if(request.getAttribute("listaTipos")!=null){
							
						//}
						
						for(TipoSeguro tps : listaTipos){
							idTipo = String.valueOf(tps.getIdTipo());
						%>	
						 	<option value="<%=idTipo  %>"><%= tps.getDescripcion()%> </option>
						<%
						} 
						%>
						 
													 				
			 </select>	
		</td>
	</tr>
	<tr> 
		<td>Costo contratación</td>
		<td style="height: 0px; "><input type="text" name="txtCostoContratacion" min="1" max="999999" required/><br></td>
	</tr>
	<tr> 
		<td style="width: 165px; ">Costo máximo asegurado</td>
		<td style="height: 0px; "><input type="text" name="txtCostoMaximo" min="1" max="999999" required/><br></td>
	</tr>
	<tr> 
		<td></td>
		<td style="height: 0px; "><input type="submit" name="btnAceptar" value="Aceptar"/></td>
	</tr>

</table>
</form>

<% 
	//int filas=0;
	if(request.getAttribute("filasAfectadas")!=null){
		int filas= (int)request.getAttribute("filasAfectadas");
%>
<% 		if(filas==1) 
		{
%>
			Seguro agregado correctamente
		<%} else { %>
		Verifique existe un error.....
		<%}
  	}
  	%>


</body>
</html>