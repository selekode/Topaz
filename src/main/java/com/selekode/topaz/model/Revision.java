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
public class Revision {
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
    
    // Valoraciones 0-10 with updated column names
    @Column(name = "valoracion_disciplina")
    private int valoracionDisciplina;
    @Column(name = "valoracion_orden")
    private int valoracionOrden;
    @Column(name = "valoracion_impulsividad")
    private int valoracionImpulsividad;
    @Column(name = "valoracion_constancia")
    private int valoracionConstancia;
    @Column(name = "valoracion_tolerancia")
    private int valoracionTolerancia;
    @Column(name = "valoracion_control_prepotencia")
    private int valoracionControlPrepotencia;
    @Column(name = "valoracion_honestidad")
    private int valoracionHonestidad;
    @Column(name = "valoracion_aceptacion")
    private int valoracionAceptacion;
    @Column(name = "valoracion_consecucion_objetivos")
    private int valoracionConsecucionObjetivos;
    
    private String explicacionValoracion;
    private String objetivosPersonales;

    // Boolean fields for emotions with updated column names
    @Column(name = "emocion_alegria")
    private boolean emocionAlegria;
    @Column(name = "emocion_tristeza")
    private boolean emocionTristeza;
    @Column(name = "emocion_ira")
    private boolean emocionIra;
    @Column(name = "emocion_miedo")
    private boolean emocionMiedo;
    @Column(name = "emocion_ansiedad")
    private boolean emocionAnsiedad;
    @Column(name = "emocion_amor")
    private boolean emocionAmor;
    @Column(name = "emocion_sorpresa")
    private boolean emocionSorpresa;
    @Column(name = "emocion_verguenza")
    private boolean emocionVerguenza;
    @Column(name = "emocion_frustracion")
    private boolean emocionFrustracion;
    @Column(name = "emocion_satisfaccion")
    private boolean emocionSatisfaccion;
    @Column(name = "emocion_aburrimiento")
    private boolean emocionAburrimiento;
    @Column(name = "emocion_serenidad")
    private boolean emocionSerenidad;
    @Column(name = "emocion_confianza")
    private boolean emocionConfianza;
    @Column(name = "emocion_abrumado")
    private boolean emocionAbrumado;
    @Column(name = "emocion_esperanza")
    private boolean emocionEsperanza;
	
	public Revision() {
		
	}

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
