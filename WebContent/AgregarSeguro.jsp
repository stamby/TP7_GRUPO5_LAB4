<%@ page import="dominio.TipoSeguro" %>
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
			if(request.getAttribute("ultimoId")!=null){
				aux = (Integer)request.getAttribute("ultimoId");			
			} 
			%>
			<%=aux %></td>
	</tr>
	<tr> 
		<td>Descripcion </td>
		<td style="height: 0px; "><input type="text" name="txtDescripcion"/></td>
	</tr>
	<tr> 
		<td>Tipo de Seguro</td>
		<td style="height: 0px; "><select name="tipoSeguro">
						<%
						ArrayList<TipoSeguro> listaTipos = null;
						String idTipo="";
						if(request.getAttribute("listaTipos")!=null){
							listaTipos = (ArrayList<TipoSeguro>)request.getAttribute("listaTipos");
						}
						
						for(TipoSeguro tps: listaTipos){
						idTipo = String.valueOf(tps.getIdTipo());
						 %>	
						 <option value="<%=idTipo  %>">
						 	<%= tps.getDescripcion()%>
						 </option>
						 <%} %>
						 
													 				
			 </select>	</td>
	</tr>
	<tr> 
		<td>Costo contratación</td>
		<td style="height: 0px; "><input type="text" name="txtCostoContratacion"/><br></td>
	</tr>
	<tr> 
		<td style="width: 165px; ">Costo máximo asegurado</td>
		<td style="height: 0px; "><input type="text" name="txtCostoMaximo"/><br></td>
	</tr>
	<tr> 
		<td></td>
		<td style="height: 0px; "><input type="submit" name="btnAceptar" value="Aceptar"/></td>
	</tr>

</table>
</form>

<% 
	int filas=0;
	if(request.getAttribute("filasAfectadas")!=null)
		filas= (int)request.getAttribute("filasAfectadas");
%>
<% if(filas==1) 
	{
%>
		Seguro agregado correctamente
<%} %>


</body>
</html>