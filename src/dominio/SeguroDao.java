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
			String query = "Insert into seguros(descripcion, idTipo, costoContratacion, costoAsegurado) values ('"+seguro.getDescripcion()+"','"+seguro.getIdTipo()+"','"+seguro.getCostoContratacion()+"','"+seguro.getCostoAsegurado()+"')";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	//LISTAR SEGUROS
	public ArrayList<Seguro> obtenerSeguros() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT idSeguro, descripcion, idTipo, costoContratacion, costoAsegurado FROM segurosgroup.seguros;");
			
			while(rs.next()){
			

				Seguro rsSeguro = new Seguro();
				rsSeguro.setIdSeguro(rs.getInt("idSeguro"));
				rsSeguro.setDescripcion(rs.getString("descripcion"));
				rsSeguro.setIdTipo(rs.getInt("idTipo"));
				rsSeguro.setCostoContratacion(rs.getFloat("costoContratacion"));
				rsSeguro.setCostoAsegurado(rs.getFloat("costoAsegurado"));
			
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
	
	public Seguro ObtenerIdSeg_Increment() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
