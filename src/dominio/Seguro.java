package dominio;

public class Seguro {
	private int idSeguro;
	private String descripcion;
	private TipoSeguro tipoSeguro;
	private float costoContratacion;
	private float costoAsegurado;
	
	public Seguro() {
		
	}

	public Seguro(String descripcion, TipoSeguro tipoSeguro, float costoContratacion, float costoAsegurado) {
		this.descripcion = descripcion;
		this.tipoSeguro = tipoSeguro;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}
	
	public int getIdSeguro() {
		return idSeguro;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getIdTipoSeguro() {
		return tipoSeguro;
	}

	public float getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public float getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(float costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}
	
	
}
