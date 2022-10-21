package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoSeguroDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "segurosgroup";
	
	public TipoSeguro obtenerTipoSeguro(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TipoSeguro tps = new TipoSeguro();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement("Select descripcion from tiposeguros where idTipo = ?");
			miSentencia.setInt(1, id); //Cargo el ID recibido
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			
			tps.setIdTipo(resultado.getInt(1));
		    tps.setDescripcion(resultado.getString(2));		 
		    
		    con.close();
		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida");
		}
		finally
		{
		}
		return tps;
	}
	
	public ArrayList<TipoSeguro> obtenerTiposSeguros() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<TipoSeguro> lista = new ArrayList<TipoSeguro>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT idTipo, descripcion FROM segurosgroup.tiposeguros;");
			
			while(rs.next()){
				TipoSeguro rsTipoSeguro = new TipoSeguro();
				rsTipoSeguro.setIdTipo(rs.getInt("idTipo"));
				rsTipoSeguro.setDescripcion(rs.getString("descripcion"));
			
				lista.add(rsTipoSeguro);
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
}
