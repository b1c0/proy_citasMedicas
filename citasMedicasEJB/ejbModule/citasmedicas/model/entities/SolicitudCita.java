package citasmedicas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the solicitud_cita database table.
 * 
 */
@Entity
@Table(name="solicitud_cita")
@NamedQuery(name="SolicitudCita.findAll", query="SELECT s FROM SolicitudCita s")
public class SolicitudCita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_solicitud", unique=true, nullable=false)
	private Integer idSolicitud;

	@Column(name="estado_solicitud", nullable=false)
	private Boolean estadoSolicitud;

	@Column(name="estado_solicitud_medico", nullable=false, length=1)
	private String estadoSolicitudMedico;

	@Column(name="estado_solicitud_paciente", nullable=false, length=1)
	private String estadoSolicitudPaciente;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cita", nullable=false)
	private Date fechaCita;

	@Column(name="hora_cita", nullable=false)
	private Time horaCita;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="solicitudCita")
	private List<Cita> citas;

	//bi-directional many-to-one association to Medico
	@ManyToOne
	@JoinColumn(name="id_medico", nullable=false)
	private Medico medico;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;

	public SolicitudCita() {
	}

	public Integer getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Boolean getEstadoSolicitud() {
		return this.estadoSolicitud;
	}

	public void setEstadoSolicitud(Boolean estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public String getEstadoSolicitudMedico() {
		return this.estadoSolicitudMedico;
	}

	public void setEstadoSolicitudMedico(String estadoSolicitudMedico) {
		this.estadoSolicitudMedico = estadoSolicitudMedico;
	}

	public String getEstadoSolicitudPaciente() {
		return this.estadoSolicitudPaciente;
	}

	public void setEstadoSolicitudPaciente(String estadoSolicitudPaciente) {
		this.estadoSolicitudPaciente = estadoSolicitudPaciente;
	}

	public Date getFechaCita() {
		return this.fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Time getHoraCita() {
		return this.horaCita;
	}

	public void setHoraCita(Time horaCita) {
		this.horaCita = horaCita;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setSolicitudCita(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setSolicitudCita(null);

		return cita;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}