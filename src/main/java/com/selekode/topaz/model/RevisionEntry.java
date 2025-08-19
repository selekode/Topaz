package com.selekode.topaz.model;

import java.time.LocalDate;

import com.selekode.topaz.converter.LocalDateUnixSecondsConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "revision")
public class RevisionEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Stores as Unix seconds in DB, used as LocalDate in Java
	@Convert(converter = LocalDateUnixSecondsConverter.class)
	@Column(name = "date", nullable = false)
	private LocalDate date;
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

	// Boolean fields for emotions
	private boolean emocionAlegria;
	private boolean emocionTristeza;
	private boolean emocionIra;
	private boolean emocionMiedo;
	private boolean emocionAnsiedad;
	private boolean emocionAmor;
	private boolean emocionSorpresa;
	private boolean emocionVerguenza;
	private boolean emocionFrustracion;
	private boolean emocionSatisfaccion;
	private boolean emocionAburrimiento;
	private boolean emocionSerenidad;
	private boolean emocionConfianza;
	private boolean emocionAbrumado;
	private boolean emocionEsperanza;
	
	public RevisionEntry() {
		
	}

	/*
	public RevisionEntry(int id, String date, String estadoEmocional, String estadoEmocionalWhy,
			String importanteParaMi, String aprendidoSobreMi, int valoracionDisciplina, int valoracionOrden,
			int valoracionImpulsividad, int valoracionConstancia, int valoracionTolerancia,
			int valoracionControlPrepotencia, int valoracionHonestidad, int valoracionAceptacion,
			int valoracionConsecucionObjetivos, String explicacionValoracion, String objetivosPersonales,
			boolean emocionAlegria, boolean emocionTristeza, boolean emocionIra, boolean emocionMiedo,
			boolean emocionAnsiedad, boolean emocionAmor, boolean emocionSorpresa, boolean emocionVerguenza,
			boolean emocionFrustracion, boolean emocionSatisfaccion, boolean emocionAburrimiento,
			boolean emocionSerenidad, boolean emocionConfianza, boolean emocionAbrumado, boolean emocionEsperanza) {
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
		this.emocionAlegria = emocionAlegria;
		this.emocionTristeza = emocionTristeza;
		this.emocionIra = emocionIra;
		this.emocionMiedo = emocionMiedo;
		this.emocionAnsiedad = emocionAnsiedad;
		this.emocionAmor = emocionAmor;
		this.emocionSorpresa = emocionSorpresa;
		this.emocionVerguenza = emocionVerguenza;
		this.emocionFrustracion = emocionFrustracion;
		this.emocionSatisfaccion = emocionSatisfaccion;
		this.emocionAburrimiento = emocionAburrimiento;
		this.emocionSerenidad = emocionSerenidad;
		this.emocionConfianza = emocionConfianza;
		this.emocionAbrumado = emocionAbrumado;
		this.emocionEsperanza = emocionEsperanza;
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public boolean isEmocionAlegria() {
		return emocionAlegria;
	}

	public void setEmocionAlegria(boolean emocionAlegria) {
		this.emocionAlegria = emocionAlegria;
	}

	public boolean isEmocionTristeza() {
		return emocionTristeza;
	}

	public void setEmocionTristeza(boolean emocionTristeza) {
		this.emocionTristeza = emocionTristeza;
	}

	public boolean isEmocionIra() {
		return emocionIra;
	}

	public void setEmocionIra(boolean emocionIra) {
		this.emocionIra = emocionIra;
	}

	public boolean isEmocionMiedo() {
		return emocionMiedo;
	}

	public void setEmocionMiedo(boolean emocionMiedo) {
		this.emocionMiedo = emocionMiedo;
	}

	public boolean isEmocionAnsiedad() {
		return emocionAnsiedad;
	}

	public void setEmocionAnsiedad(boolean emocionAnsiedad) {
		this.emocionAnsiedad = emocionAnsiedad;
	}

	public boolean isEmocionAmor() {
		return emocionAmor;
	}

	public void setEmocionAmor(boolean emocionAmor) {
		this.emocionAmor = emocionAmor;
	}

	public boolean isEmocionSorpresa() {
		return emocionSorpresa;
	}

	public void setEmocionSorpresa(boolean emocionSorpresa) {
		this.emocionSorpresa = emocionSorpresa;
	}

	public boolean isEmocionVerguenza() {
		return emocionVerguenza;
	}

	public void setEmocionVerguenza(boolean emocionVerguenza) {
		this.emocionVerguenza = emocionVerguenza;
	}

	public boolean isEmocionFrustracion() {
		return emocionFrustracion;
	}

	public void setEmocionFrustracion(boolean emocionFrustracion) {
		this.emocionFrustracion = emocionFrustracion;
	}

	public boolean isEmocionSatisfaccion() {
		return emocionSatisfaccion;
	}

	public void setEmocionSatisfaccion(boolean emocionSatisfaccion) {
		this.emocionSatisfaccion = emocionSatisfaccion;
	}

	public boolean isEmocionAburrimiento() {
		return emocionAburrimiento;
	}

	public void setEmocionAburrimiento(boolean emocionAburrimiento) {
		this.emocionAburrimiento = emocionAburrimiento;
	}

	public boolean isEmocionSerenidad() {
		return emocionSerenidad;
	}

	public void setEmocionSerenidad(boolean emocionSerenidad) {
		this.emocionSerenidad = emocionSerenidad;
	}

	public boolean isEmocionConfianza() {
		return emocionConfianza;
	}

	public void setEmocionConfianza(boolean emocionConfianza) {
		this.emocionConfianza = emocionConfianza;
	}

	public boolean isEmocionAbrumado() {
		return emocionAbrumado;
	}

	public void setEmocionAbrumado(boolean emocionAbrumado) {
		this.emocionAbrumado = emocionAbrumado;
	}

	public boolean isEmocionEsperanza() {
		return emocionEsperanza;
	}

	public void setEmocionEsperanza(boolean emocionEsperanza) {
		this.emocionEsperanza = emocionEsperanza;
	}

}
