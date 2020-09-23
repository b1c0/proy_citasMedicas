package citasmedicas.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@Table(name="rol")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_rol", unique=true, nullable=false, length=3)
	private String idRol;

	@Column(name="estado_rol", nullable=false)
	private Boolean estadoRol;

	@Column(name="nombre_rol", nullable=false, length=50)
	private String nombreRol;

	//bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy="rol")
	private List<UsuarioRol> usuarioRols;

	public Rol() {
	}

	public String getIdRol() {
		return this.idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public Boolean getEstadoRol() {
		return this.estadoRol;
	}

	public void setEstadoRol(Boolean estadoRol) {
		this.estadoRol = estadoRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}

	public void setUsuarioRols(List<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setRol(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setRol(null);

		return usuarioRol;
	}

}