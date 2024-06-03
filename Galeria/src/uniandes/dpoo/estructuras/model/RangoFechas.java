package uniandes.dpoo.estructuras.model;

import java.time.LocalDate;

public class RangoFechas {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public RangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public RangoFechas() {
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RangoFechas) {
            RangoFechas rango = (RangoFechas) obj;

            if (rango.getFechaInicio() == null && rango.getFechaFin() == null) {
                return this.getFechaInicio() == null && this.getFechaFin() == null;
            }

            if (rango.getFechaInicio() == null) {
                return this.getFechaInicio() == null && rango.getFechaFin().equals(this.getFechaFin());
            }

            if (rango.getFechaFin() == null) {
                return rango.getFechaInicio().equals(this.getFechaInicio()) && this.getFechaFin() == null;
            }

            return rango.getFechaInicio().equals(this.getFechaInicio()) && rango.getFechaFin().equals(this.getFechaFin());
        }
        return false;
    }
}
