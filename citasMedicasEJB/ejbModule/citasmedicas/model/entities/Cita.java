package citasmedicas.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@Table(name="cita")
@NamedQuery(name="Cita.findAll", query="SELECT c FROM Cita c")
public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cita", unique=true, nullable=false)
	private Integer idCita;

	@Column(name="observaciones_cita", length=300)
	private String observacionesCita;

	//bi-directional many-to-one association to Enfermedad
	@ManyToOne
	@JoinColumn(name="id_enfermedad")
	private Enfermedad enfermedad;

	//bi-directional many-to-one association to SolicitudCita
	@ManyToOne
	@JoinColumn(name="id_solicitud", nullable=false)
	private SolicitudCita solicitudCita;

	public Cita() {
	}

	public Integer getIdCita() {
		return this.idCita;
	}

	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}

	public String getObservacionesCita() {
		return this.observacionesCita;
	}

	public void setObservacionesCita(String observacionesCita) {
		this.observacionesCita = observacionesCita;
	}

	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public SolicitudCita getSolicitudCita() {
		return this.solicitudCita;
	}

	public void setSolicitudCita(SolicitudCita solicitudCita) {
		this.solicitudCita = solicitudCita;
	}

}