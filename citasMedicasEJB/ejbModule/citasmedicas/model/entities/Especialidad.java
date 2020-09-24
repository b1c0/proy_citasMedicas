package citasmedicas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
@Table(name="especialidad")
@NamedQuery(name="Especialidad.findAll", query="SELECT e FROM Especialidad e")
public class Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_especialidad", unique=true, nullable=false)
	private Integer idEspecialidad;

	@Column(name="descripcion_especialidad", nullable=false, length=300)
	private String descripcionEspecialidad;

	@Column(name="estado_especialidad", nullable=false)
	private Boolean estadoEspecialidad;

	@Column(name="nombre_especialidad", nullable=false, length=50)
	private String nombreEspecialidad;

	//bi-directional many-to-one association to Medico
	@OneToMany(mappedBy="especialidad")
	private List<Medico> medicos;

	public Especialidad() {
	}

	public Integer getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getDescripcionEspecialidad() {
		return this.descripcionEspecialidad;
	}

	public void setDescripcionEspecialidad(String descripcionEspecialidad) {
		this.descripcionEspecialidad = descripcionEspecialidad;
	}

	public Boolean getEstadoEspecialidad() {
		return this.estadoEspecialidad;
	}

	public void setEstadoEspecialidad(Boolean estadoEspecialidad) {
		this.estadoEspecialidad = estadoEspecialidad;
	}

	public String getNombreEspecialidad() {
		return this.nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico addMedico(Medico medico) {
		getMedicos().add(medico);
		medico.setEspecialidad(this);

		return medico;
	}

	public Medico removeMedico(Medico medico) {
		getMedicos().remove(medico);
		medico.setEspecialidad(null);

		return medico;
	}

}