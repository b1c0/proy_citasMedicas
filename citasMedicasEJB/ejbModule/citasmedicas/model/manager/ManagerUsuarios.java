package citasmedicas.model.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import citasmedicas.model.entities.Rol;
import citasmedicas.model.entities.Usuario;
import citasmedicas.model.entities.UsuarioRol;

/**
 * Session Bean implementation class ManagerUsuarios
 */
@Stateless
@LocalBean
public class ManagerUsuarios {
	@PersistenceContext
	private EntityManager em;

	public ManagerUsuarios() {
	}

	public boolean comprobarUsuario(String correo, String clave) throws Exception {
		Usuario u = encontrarUsuarioByCorreo(correo);
		UsuarioRol ur = em.find(UsuarioRol.class, u.getIdUsuario());
		if (u == null) {
			throw new Exception("No existe el usuario");
		}
		if (!ur.getEstadoUsuario()) {
			throw new Exception("EL USUARIO NO ESTA ACTIVO");
		}
		if (u.getClave().equals(clave)) {
			return true;
		} else {
			throw new Exception("Contraseña incorrecta");
		}

	}

	public Usuario encontrarUsuarioByCorreo(String correo) {
		Query q = em.createQuery("select u from Usuario u where correo='" + correo + "'");
		return (Usuario) q.getSingleResult();
	}

	public UsuarioRol obtenerRol(Usuario u) {
		return em.find(UsuarioRol.class, u.getIdUsuario());
	}

	public Usuario findUsuarioById(int id_usuario) {
		return em.find(Usuario.class, id_usuario);
	}
	
	public Rol findRolById(String rol) {
		return em.find(Rol.class, rol);
	}
	
	public void insertarNuevoUsuario(Usuario u) {
		em.persist(u);
	}

	public void insertarNuevoUsuarioROL(UsuarioRol ur) {
		em.persist(ur);
	}
	

}
