package com.selekode.topaz.model;

import java.time.LocalDate;
import com.selekode.topaz.converter.LocalDateUnixSecondsConverter;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "revision")
@Getter
@Setter
@NoArgsConstructor
public class Revision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = LocalDateUnixSecondsConverter.class)
	@Column(name = "date", nullable = false)
	private LocalDate date;

	private String estadoEmocional;
	private String estadoEmocionalWhy;
	private String importanteParaMi;
	private String aprendidoSobreMi;

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
}