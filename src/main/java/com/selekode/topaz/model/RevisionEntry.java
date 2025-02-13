package com.selekode.topaz.model;

public class RevisionEntry {
	private int id;
	private String date;
	private String estadoEmocional;
	private String estadoEmocionalWhy;
	private String importanteParaMi;
	private String aprendidoSobreMi;
	private int valoracionDisciplina;
	private int valoracionOrden;
	private int valoracionImpulsividad;
	private int valoracionConstancia;
	private int valoracionTolerancia;
	private int valoracionControlPrepotencia;
	private int valoracionHonestidad;
	private int valoracionAceptacion;
	private int valoracionConsecucionObjetivos;
	private String explicacionValoracion;
	private String objetivosPersonales;
	
	public RevisionEntry(int id, String date, String estadoEmocional, String estadoEmocionalWhy,
			String importanteParaMi, String aprendidoSobreMi, int valoracionDisciplina, int valoracionOrden,
			int valoracionImpulsividad, int valoracionConstancia, int valoracionTolerancia,
			int valoracionControlPrepotencia, int valoracionHonestidad, int valoracionAceptacion,
			int valoracionConsecucionObjetivos, String explicacionValoracion, String objetivosPersonales) {
		super();
		this.id = id;
		this.date = date;
		this.estadoEmocional = estadoEmocional;
		this.estadoEmocionalWhy = estadoEmocionalWhy;
		this.importanteParaMi = importanteParaMi;
		this.aprendidoSobreMi = aprendidoSobreMi;
		this.valoracionDisciplina = valoracionDisciplina;
		this.valoracionOrden = valoracionOrden;
		this.valoracionImpulsividad = valoracionImpulsividad;
		this.valoracionConstancia = valoracionConstancia;
		this.valoracionTolerancia = valoracionTolerancia;
		this.valoracionControlPrepotencia = valoracionControlPrepotencia;
		this.valoracionHonestidad = valoracionHonestidad;
		this.valoracionAceptacion = valoracionAceptacion;
		this.valoracionConsecucionObjetivos = valoracionConsecucionObjetivos;
		this.explicacionValoracion = explicacionValoracion;
		this.objetivosPersonales = objetivosPersonales;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEstadoEmocional() {
		return estadoEmocional;
	}
	public void setEstadoEmocional(String estadoEmocional) {
		this.estadoEmocional = estadoEmocional;
	}
	public String getEstadoEmocionalWhy() {
		return estadoEmocionalWhy;
	}
	public void setEstadoEmocionalWhy(String estadoEmocionalWhy) {
		this.estadoEmocionalWhy = estadoEmocionalWhy;
	}
	public String getImportanteParaMi() {
		return importanteParaMi;
	}
	public void setImportanteParaMi(String importanteParaMi) {
		this.importanteParaMi = importanteParaMi;
	}
	public String getAprendidoSobreMi() {
		return aprendidoSobreMi;
	}
	public void setAprendidoSobreMi(String aprendidoSobreMi) {
		this.aprendidoSobreMi = aprendidoSobreMi;
	}
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
	public String getExplicacionValoracion() {
		return explicacionValoracion;
	}
	public void setExplicacionValoracion(String explicacionValoracion) {
		this.explicacionValoracion = explicacionValoracion;
	}
	public String getObjetivosPersonales() {
		return objetivosPersonales;
	}
	public void setObjetivosPersonales(String objetivosPersonales) {
		this.objetivosPersonales = objetivosPersonales;
	}
	
	
}
