package com.selekode.topaz.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

	// 1. Core Emotions (The Baseline Ring)
	@Getter @Setter
	@Column(name = "emocion_alegria", nullable = false)
	private boolean emocionAlegria;

	@Getter @Setter
	@Column(name = "emocion_tristeza", nullable = false)
	private boolean emocionTristeza;

	@Getter @Setter
	@Column(name = "emocion_ira", nullable = false)
	private boolean emocionIra;

	@Getter @Setter
	@Column(name = "emocion_miedo", nullable = false)
	private boolean emocionMiedo;

	@Getter @Setter
	@Column(name = "emocion_confianza", nullable = false)
	private boolean emocionConfianza;

	@Getter @Setter
	@Column(name = "emocion_sorpresa", nullable = false)
	private boolean emocionSorpresa;

	@Getter @Setter
	@Column(name = "emocion_anticipacion", nullable = false)
	private boolean emocionAnticipacion;

	@Getter @Setter
	@Column(name = "emocion_rechazo", nullable = false)
	private boolean emocionRechazo;

	// 2. Mild Emotions (The Outer Ring)
	@Getter @Setter
	@Column(name = "emocion_serenidad", nullable = false) // Mild Joy
	private boolean emocionSerenidad;

	@Getter @Setter
	@Column(name = "emocion_melancolia", nullable = false) // Mild Sadness (Formerly under reflective)
	private boolean emocionMelancolia;

	@Getter @Setter
	@Column(name = "emocion_fastidio", nullable = false) // Mild Anger
	private boolean emocionFastidio;

	@Getter @Setter
	@Column(name = "emocion_aprension", nullable = false) // Mild Fear
	private boolean emocionAprension;

	@Getter @Setter
	@Column(name = "emocion_aceptacion", nullable = false) // Mild Trust
	private boolean emocionAceptacion;

	@Getter @Setter
	@Column(name = "emocion_distraccion", nullable = false) // Mild Surprise
	private boolean emocionDistraccion;

	@Getter @Setter
	@Column(name = "emocion_interes", nullable = false) // Mild Anticipation
	private boolean emocionInteres;

	@Getter @Setter
	@Column(name = "emocion_aburrimiento", nullable = false) // Mild Disgust
	private boolean emocionAburrimiento;

	// 3. Intense Emotions (The Inner Ring)
	@Getter @Setter
	@Column(name = "emocion_extasis", nullable = false) // Intense Joy
	private boolean emocionExtasis;

	@Getter @Setter
	@Column(name = "emocion_pena_dolor", nullable = false) // Intense Sadness
	private boolean emocionPenaDolor;

	@Getter @Setter
	@Column(name = "emocion_furia", nullable = false) // Intense Anger
	private boolean emocionFuria;

	@Getter @Setter
	@Column(name = "emocion_terror", nullable = false) // Intense Fear
	private boolean emocionTerror;

	@Getter @Setter
	@Column(name = "emocion_admiracion", nullable = false) // Intense Trust
	private boolean emocionAdmiracion;

	@Getter @Setter
	@Column(name = "emocion_asombro", nullable = false) // Intense Surprise
	private boolean emocionAsombro;

	@Getter @Setter
	@Column(name = "emocion_vigilancia", nullable = false) // Intense Anticipation
	private boolean emocionVigilancia;

	@Getter @Setter
	@Column(name = "emocion_asco", nullable = false) // Intense Disgust
	private boolean emocionAsco;

	// 4. Complex / Reflective States (Social & Cognitive)
	@Getter @Setter
	@Column(name = "emocion_ansiedad", nullable = false)
	private boolean emocionAnsiedad;

	@Getter @Setter
	@Column(name = "emocion_frustracion", nullable = false)
	private boolean emocionFrustracion;

	@Getter @Setter
	@Column(name = "emocion_verguenza", nullable = false)
	private boolean emocionVerguenza;

	@Getter @Setter
	@Column(name = "emocion_esperanza", nullable = false)
	private boolean emocionEsperanza;

	@Getter @Setter
	@Column(name = "emocion_orgullo", nullable = false)
	private boolean emocionOrgullo;

	@Getter @Setter
	@Column(name = "emocion_agobio", nullable = false)
	private boolean emocionAgobio;

	// 5. Baseline State
	@Getter @Setter
	@Column(name = "emocion_neutral", nullable = false)
	private boolean emocionNeutral;

	/**
	 * Comprueba si el usuario ha marcado al menos una emoción en este registro.
	 * Útil para la lógica de visualización en el frontend (Thymeleaf).
	 */
	public boolean hasEmotions() {
		return emocionNeutral ||
				// Core
				emocionAlegria || emocionTristeza || emocionIra || emocionMiedo ||
				emocionConfianza || emocionSorpresa || emocionAnticipacion || emocionRechazo ||
				// Mild
				emocionSerenidad || emocionMelancolia || emocionFastidio || emocionAprension ||
				emocionAceptacion || emocionDistraccion || emocionInteres || emocionAburrimiento ||
				// Intense
				emocionExtasis || emocionPenaDolor || emocionFuria || emocionTerror ||
				emocionAdmiracion || emocionAsombro || emocionVigilancia || emocionAsco ||
				// Reflective
				emocionAnsiedad || emocionFrustracion || emocionVerguenza ||
				emocionEsperanza || emocionOrgullo || emocionAgobio;
	}
}