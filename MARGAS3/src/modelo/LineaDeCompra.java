package modelo;

public class LineaDeCompra extends LineaDePedido {
	private int cantidadRestante;
	
	public LineaDeCompra(TipoGarrafa tipoGarrafa, long cantidadComprada) {
		super(tipoGarrafa, (int) cantidadComprada);
	}

	/**
	 * @return the cantidadRestante
	 */
	public int getCantidadRestante() {
		return cantidadRestante;
	}

	/**
	 * @param cantidadRestante the cantidadRestante to set
	 */
	public void setCantidadRestante(int cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}

	public int getNUnidades(int cantidadSolicitada) {
		int unidades = 0;
		
		if (cantidadRestante >= cantidadSolicitada) {
			unidades = cantidadSolicitada;
			cantidadRestante -= cantidadSolicitada;
		}
		else { 
			unidades = cantidadRestante;
			cantidadRestante = 0;
		}
		
		return unidades;
	}

}
