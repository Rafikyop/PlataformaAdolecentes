package com.plataforma.proyecto.dto;

public class IntervencionDTO {
    private int id;
    private int reporteId;
    private int moderadorId;
    private int psicologoId;
    private String descripcionAccion;

    // Constructor vacío
    public IntervencionDTO() {}

    public IntervencionDTO(int reporteId, int moderadorId, int psicologoId, String descripcionAccion) {
        this.reporteId = reporteId;
        this.moderadorId = moderadorId;
        this.psicologoId = psicologoId;
        this.descripcionAccion = descripcionAccion;
    }

    // Getters y Setters
    public int getReporteId() { return reporteId; }
    public void setReporteId(int reporteId) { this.reporteId = reporteId; }

    public int getModeradorId() { return moderadorId; }
    public void setModeradorId(int moderadorId) { this.moderadorId = moderadorId; }

    public int getPsicologoId() { return psicologoId; }
    public void setPsicologoId(int psicologoId) { this.psicologoId = psicologoId; }

    public String getDescripcionAccion() { return descripcionAccion; }
    public void setDescripcionAccion(String descripcionAccion) { this.descripcionAccion = descripcionAccion; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
