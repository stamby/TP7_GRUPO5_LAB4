package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SeguroDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "segurosgroup";
	
	//AGREGAR SEGURO
	public int agregarSeguro(Seguro seguro)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Insert into seguros(descripcion, idTipo, costoContratacion, costoAsegurado) values ('"+seguro.getDescripcion()+"','"+seguro.getTipoSeguro().getIdTipo()+"','"+seguro.getCostoContratacion()+"','"+seguro.getCostoAsegurado()+"')";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}

	public ArrayList<Seguro> obtenerSeguros() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT SEG.idSeguro, SEG.Descripcion, TSEG.IdTipo, TSEG.Descripcion, SEG.costoContratacion, SEG.costoAsegurado FROM Seguros SEG INNER JOIN TipoSeguros TSEG ON SEG.idTipo = TSEG.idTipo");
			
			while(rs.next()){
			
				Seguro rsSeguro = new Seguro();
				TipoSeguro tipoSeguro = new TipoSeguro();
				
				tipoSeguro.setDescripcion(rs.getString("TSEG.Descripcion"));
				tipoSeguro.setDescripcion(rs.getString("TSEG.IdTipo"));
				
				rsSeguro.setIdSeguro(rs.getInt("SEG.idSeguro"));
				rsSeguro.setDescripcion(rs.getString("SEG.Descripcion"));
				rsSeguro.setTipoSeguro(tipoSeguro);
				rsSeguro.setCostoContratacion(rs.getFloat("SEG.costoContratacion"));
				rsSeguro.setCostoAsegurado(rs.getFloat("SEG.costoAsegurado"));
			
				lista.add(rsSeguro);
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
	
		}
	
		return lista;
	}
	
	public ArrayList<Seguro> obtenerSeguros(String idTipoSeleccionado) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT SEG.idSeguro, SEG.Descripcion, TSEG.IdTipo, TSEG.Descripcion, SEG.costoContratacion, SEG.costoAsegurado FROM Seguros SEG INNER JOIN TipoSeguros TSEG ON SEG.idTipo = TSEG.idTipo WHERE SEG.idTipo = " + idTipoSeleccionado);
			
			while(rs.next()){
				
				Seguro rsSeguro = new Seguro();
				TipoSeguro tipoSeguro = new TipoSeguro();
				
				tipoSeguro.setDescripcion(rs.getString("TSEG.Descripcion"));
				tipoSeguro.setDescripcion(rs.getString("TSEG.IdTipo"));
				
				rsSeguro.setIdSeguro(rs.getInt("SEG.idSeguro"));
				rsSeguro.setDescripcion(rs.getString("SEG.Descripcion"));
				rsSeguro.setTipoSeguro(tipoSeguro);
				rsSeguro.setCostoContratacion(rs.getFloat("SEG.costoContratacion"));
				rsSeguro.setCostoAsegurado(rs.getFloat("SEG.costoAsegurado"));
				
				lista.add(rsSeguro);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	public Seguro ObtenerIdSeg_Increment() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Seguro lista = new Seguro();
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(host, user, pass);
			String query = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'SegurosGroup' AND TABLE_NAME = 'seguros'";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			lista.setIdSeguro(rs.getInt(1));
			
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{	}
		return lista;
	}
}
