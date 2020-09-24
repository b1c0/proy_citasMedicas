package citasmedicas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
@Table(name="medico")
@NamedQuery(name="Medico.findAll", query="SELECT m FROM Medico m")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_medico", unique=true, nullable=false)
	private Integer idMedico;

	@Column(name="descripcion_medico", nullable=false, length=300)
	private String descripcionMedico;

	@Column(name="estado_medico", nullable=false)
	private Boolean estadoMedico;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="id_especialidad", nullable=false)
	private Especialidad especialidad;

	//bi-directional many-to-one association to UsuarioRol
	@ManyToOne
	@JoinColumn(name="id_usuario_rol", nullable=false)
	private UsuarioRol usuarioRol;

	//bi-directional many-to-one association to SolicitudCita
	@OneToMany(mappedBy="medico")
	private List<SolicitudCita> solicitudCitas;

	public Medico() {
	}

	public Integer getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getDescripcionMedico() {
		return this.descripcionMedico;
	}

	public void setDescripcionMedico(String descripcionMedico) {
		this.descripcionMedico = descripcionMedico;
	}

	public Boolean getEstadoMedico() {
		return this.estadoMedico;
	}

	public void setEstadoMedico(Boolean estadoMedico) {
		this.estadoMedico = estadoMedico;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public UsuarioRol getUsuarioRol() {
		return this.usuarioRol;
	}

	public void setUsuarioRol(UsuarioRol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	public List<SolicitudCita> getSolicitudCitas() {
		return this.solicitudCitas;
	}

	public void setSolicitudCitas(List<SolicitudCita> solicitudCitas) {
		this.solicitudCitas = solicitudCitas;
	}

	public SolicitudCita addSolicitudCita(SolicitudCita solicitudCita) {
		getSolicitudCitas().add(solicitudCita);
		solicitudCita.setMedico(this);

		return solicitudCita;
	}

	public SolicitudCita removeSolicitudCita(SolicitudCita solicitudCita) {
		getSolicitudCitas().remove(solicitudCita);
		solicitudCita.setMedico(null);

		return solicitudCita;
	}

}