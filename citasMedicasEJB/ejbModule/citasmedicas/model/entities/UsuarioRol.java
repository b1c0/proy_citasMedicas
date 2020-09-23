package citasmedicas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario_rol database table.
 * 
 */
@Entity
@Table(name="usuario_rol")
@NamedQuery(name="UsuarioRol.findAll", query="SELECT u FROM UsuarioRol u")
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario_rol", unique=true, nullable=false)
	private Integer idUsuarioRol;

	@Column(name="estado_usuario", nullable=false)
	private Boolean estadoUsuario;

	//bi-directional many-to-one association to Medico
	@OneToMany(mappedBy="usuarioRol")
	private List<Medico> medicos;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol", nullable=false)
	private Rol rol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;

	public UsuarioRol() {
	}

	public Integer getIdUsuarioRol() {
		return this.idUsuarioRol;
	}

	public void setIdUsuarioRol(Integer idUsuarioRol) {
		this.idUsuarioRol = idUsuarioRol;
	}

	public Boolean getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(Boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico addMedico(Medico medico) {
		getMedicos().add(medico);
		medico.setUsuarioRol(this);

		return medico;
	}

	public Medico removeMedico(Medico medico) {
		getMedicos().remove(medico);
		medico.setUsuarioRol(null);

		return medico;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}