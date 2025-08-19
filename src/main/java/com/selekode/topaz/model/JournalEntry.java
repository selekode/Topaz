package com.selekode.topaz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;

import java.time.LocalDate;

import com.selekode.topaz.converter.LocalDateUnixSecondsConverter;

@Entity
@Table(name = "journal")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stores as Unix seconds in DB, used as LocalDate in Java
    @Convert(converter = LocalDateUnixSecondsConverter.class)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    private String title;
    private String contentGeneral;
    private String contentSaludFisica;
    private String contentBienestarMental;
    private String contentRelacionesSociales;
    private String contentCarreraProfesional;
    private String contentEstabilidadFinanciera;
    private String contentCrecimientoPersonal;
    private String contentPasatiemposCreatividad;
    private String contentEspiritualidadProposito;
    private String contentRecreacionDiversion;
    private String contentContribucionLegado;
    private String contentErroresCometidos;

    // Default constructor required by JPA
    public JournalEntry() {}

    // Optional: constructor with all fields except ID
    public JournalEntry(LocalDate date, String title, String contentGeneral,
                        String contentSaludFisica, String contentBienestarMental,
                        String contentRelacionesSociales, String contentCarreraProfesional,
                        String contentEstabilidadFinanciera, String contentCrecimientoPersonal,
                        String contentPasatiemposCreatividad, String contentEspiritualidadProposito,
                        String contentRecreacionDiversion, String contentContribucionLegado,
                        String contentErroresCometidos) {
        this.date = date;
        this.title = title;
        this.contentGeneral = contentGeneral;
        this.contentSaludFisica = contentSaludFisica;
        this.contentBienestarMental = contentBienestarMental;
        this.contentRelacionesSociales = contentRelacionesSociales;
        this.contentCarreraProfesional = contentCarreraProfesional;
        this.contentEstabilidadFinanciera = contentEstabilidadFinanciera;
        this.contentCrecimientoPersonal = contentCrecimientoPersonal;
        this.contentPasatiemposCreatividad = contentPasatiemposCreatividad;
        this.contentEspiritualidadProposito = contentEspiritualidadProposito;
        this.contentRecreacionDiversion = contentRecreacionDiversion;
        this.contentContribucionLegado = contentContribucionLegado;
        this.contentErroresCometidos = contentErroresCometidos;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContentGeneral() { return contentGeneral; }
    public void setContentGeneral(String contentGeneral) { this.contentGeneral = contentGeneral; }

    public String getContentSaludFisica() { return contentSaludFisica; }
    public void setContentSaludFisica(String contentSaludFisica) { this.contentSaludFisica = contentSaludFisica; }

    public String getContentBienestarMental() { return contentBienestarMental; }
    public void setContentBienestarMental(String contentBienestarMental) { this.contentBienestarMental = contentBienestarMental; }

    public String getContentRelacionesSociales() { return contentRelacionesSociales; }
    public void setContentRelacionesSociales(String contentRelacionesSociales) { this.contentRelacionesSociales = contentRelacionesSociales; }

    public String getContentCarreraProfesional() { return contentCarreraProfesional; }
    public void setContentCarreraProfesional(String contentCarreraProfesional) { this.contentCarreraProfesional = contentCarreraProfesional; }

    public String getContentEstabilidadFinanciera() { return contentEstabilidadFinanciera; }
    public void setContentEstabilidadFinanciera(String contentEstabilidadFinanciera) { this.contentEstabilidadFinanciera = contentEstabilidadFinanciera; }

    public String getContentCrecimientoPersonal() { return contentCrecimientoPersonal; }
    public void setContentCrecimientoPersonal(String contentCrecimientoPersonal) { this.contentCrecimientoPersonal = contentCrecimientoPersonal; }

    public String getContentPasatiemposCreatividad() { return contentPasatiemposCreatividad; }
    public void setContentPasatiemposCreatividad(String contentPasatiemposCreatividad) { this.contentPasatiemposCreatividad = contentPasatiemposCreatividad; }

    public String getContentEspiritualidadProposito() { return contentEspiritualidadProposito; }
    public void setContentEspiritualidadProposito(String contentEspiritualidadProposito) { this.contentEspiritualidadProposito = contentEspiritualidadProposito; }

    public String getContentRecreacionDiversion() { return contentRecreacionDiversion; }
    public void setContentRecreacionDiversion(String contentRecreacionDiversion) { this.contentRecreacionDiversion = contentRecreacionDiversion; }

    public String getContentContribucionLegado() { return contentContribucionLegado; }
    public void setContentContribucionLegado(String contentContribucionLegado) { this.contentContribucionLegado = contentContribucionLegado; }

    public String getContentErroresCometidos() { return contentErroresCometidos; }
    public void setContentErroresCometidos(String contentErroresCometidos) { this.contentErroresCometidos = contentErroresCometidos; }
}
