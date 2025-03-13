package com.selekode.topaz.model;

public class PersonalRatings {
	private int valoracionDisciplina;
	private int valoracionOrden;
	private int valoracionImpulsividad;
	private int valoracionConstancia;
	private int valoracionTolerancia;
	private int valoracionControlPrepotencia;
	private int valoracionHonestidad;
	private int valoracionAceptacion;
	private int valoracionConsecucionObjetivos;
	
	public int getValoracionDisciplina() {
		return valoracionDisciplina;
	}
	public void setValoracionDisciplina(int valoracionDisciplina) {
		this.valoracionDisciplina = valoracionDisciplina;
	}
	public int getValoracionOrden() {
		return valoracionOrden;
	}
	public void setValoracionOrden(int valoracionOrden) {
		this.valoracionOrden = valoracionOrden;
	}
	public int getValoracionImpulsividad() {
		return valoracionImpulsividad;
	}
	public void setValoracionImpulsividad(int valoracionImpulsividad) {
		this.valoracionImpulsividad = valoracionImpulsividad;
	}
	public int getValoracionConstancia() {
		return valoracionConstancia;
	}
	public void setValoracionConstancia(int valoracionConstancia) {
		this.valoracionConstancia = valoracionConstancia;
	}
	public int getValoracionTolerancia() {
		return valoracionTolerancia;
	}
	public void setValoracionTolerancia(int valoracionTolerancia) {
		this.valoracionTolerancia = valoracionTolerancia;
	}
	public int getValoracionControlPrepotencia() {
		return valoracionControlPrepotencia;
	}
	public void setValoracionControlPrepotencia(int valoracionControlPrepotencia) {
		this.valoracionControlPrepotencia = valoracionControlPrepotencia;
	}
	public int getValoracionHonestidad() {
		return valoracionHonestidad;
	}
	public void setValoracionHonestidad(int valoracionHonestidad) {
		this.valoracionHonestidad = valoracionHonestidad;
	}
	public int getValoracionAceptacion() {
		return valoracionAceptacion;
	}
	public void setValoracionAceptacion(int valoracionAceptacion) {
		this.valoracionAceptacion = valoracionAceptacion;
	}
	public int getValoracionConsecucionObjetivos() {
		return valoracionConsecucionObjetivos;
	}
	public void setValoracionConsecucionObjetivos(int valoracionConsecucionObjetivos) {
		this.valoracionConsecucionObjetivos = valoracionConsecucionObjetivos;
	}
	
	public PersonalRatings(int valoracionDisciplina, int valoracionOrden, int valoracionImpulsividad,
			int valoracionConstancia, int valoracionTolerancia, int valoracionControlPrepotencia,
			int valoracionHonestidad, int valoracionAceptacion, int valoracionConsecucionObjetivos) {
		super();
		this.valoracionDisciplina = valoracionDisciplina;
		this.valoracionOrden = valoracionOrden;
		this.valoracionImpulsividad = valoracionImpulsividad;
		this.valoracionConstancia = valoracionConstancia;
		this.valoracionTolerancia = valoracionTolerancia;
		this.valoracionControlPrepotencia = valoracionControlPrepotencia;
		this.valoracionHonestidad = valoracionHonestidad;
		this.valoracionAceptacion = valoracionAceptacion;
		this.valoracionConsecucionObjetivos = valoracionConsecucionObjetivos;
	}
	@Override
	public String toString() {
		return "PersonalRatings [valoracionDisciplina=" + valoracionDisciplina + ", valoracionOrden=" + valoracionOrden
				+ ", valoracionImpulsividad=" + valoracionImpulsividad + ", valoracionConstancia="
				+ valoracionConstancia + ", valoracionTolerancia=" + valoracionTolerancia
				+ ", valoracionControlPrepotencia=" + valoracionControlPrepotencia + ", valoracionHonestidad="
				+ valoracionHonestidad + ", valoracionAceptacion=" + valoracionAceptacion
				+ ", valoracionConsecucionObjetivos=" + valoracionConsecucionObjetivos + "]";
	}	
	
	
}
