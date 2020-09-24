package proyecto.model.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import proyecto.model.entities.Enfermedad;
import proyecto.model.entities.Especialidad;
import proyecto.model.entities.Medico;
import proyecto.model.entities.Rol;
import proyecto.model.entities.Usuario;
import proyecto.model.entities.UsuarioRol;

/**
 * Session Bean implementation class ManagerAdministrador
 */
/**
 * @author yesenia
 *
 */
@Stateless
@LocalBean
public class ManagerAdministrador {
	@PersistenceContext

	private EntityManager em;

	/**
	 * Default constructor.
	 * 
	 * @author Castro Fabricio, Coyago Yesenia, De la Cruz Tatiana fecha : semestre
	 *         junio - septiembre descripcion: agendamiento de citas médcias
	 */
	public ManagerAdministrador() {

	}

	/*
	 * CRUD MEDICO
	 */
	public void insertarMedico(Medico medico) {
		em.persist(medico);
	}

	public List<Medico> findAllMedicos() {
		return em.createNamedQuery("Medico.findAll", Medico.class).getResultList();
	}

	/*
	 * CRUD ENFERMEDAD
	 */
	public List<Enfermedad> findAllEnfermedades() {
		return em.createNamedQuery("Enfermedad.findAll", Enfermedad.class).getResultList();
	}

	public void actualizarEnfermedad(Enfermedad enf) throws Exception {
		enf.setNombreEnfermedad(enf.getNombreEnfermedad());
		enf.setDescripcionEnfermedad(enf.getDescripcionEnfermedad());
		enf.setSintomas(enf.getSintomas());
		enf.setTratamiento(enf.getTratamiento());
		em.merge(enf);
	}

	public void insertarEnfermedad(Enfermedad enfermedad) {
		em.persist(enfermedad);
	}

	/*
	 * CRUD ESPECIALIDAD
	 */
	public List<Especialidad> findAllEspecialidades() {
		return em.createNamedQuery("Especialidad.findAll", Especialidad.class).getResultList();
	}

	public void actualizarEspecialidad(Especialidad esp) throws Exception {
		esp.setNombreEspecialidad(esp.getNombreEspecialidad());
		esp.setDescripcionEspecialidad(esp.getDescripcionEspecialidad());
		esp.setEstadoEspecialidad(esp.getEstadoEspecialidad());
		em.merge(esp);
	}

	public void eliminarEspecialidad(Especialidad esp) throws Exception {

		esp.setEstadoEspecialidad(false);
		em.persist(esp);
	}

	public void insertarEspecialidad(Especialidad especialidad) {
		em.persist(especialidad);
	}

	/*
	 * CRUD ROL
	 */
	public List<Rol> findAllRoles() {
		return em.createNamedQuery("Rol.findAll", Rol.class).getResultList();
	}

	public void actualizarRol(Rol r) throws Exception {
		r.setNombreRol(r.getNombreRol());
		r.setEstadoRol(r.getEstadoRol());
		em.merge(r);
	}

	public void eliminarRol(Rol ro) throws Exception {

		ro.setEstadoRol(false);
		em.persist(ro);
	}

	public void insertarRol(Rol rol) {
		em.persist(rol);
	}

	/*
	 * CRUD USUARIO
	 */
	public void actualizarUsuario(Usuario usu) throws Exception {
//		Usuario u = findUsuarioByCorreo(usu.getCorreo());
//		if (u!= null)
//			throw new Exception("No existe el usuario");
//		else 
//		
//		u.setNombres(usu.getNombres());
//		u.setApellidos(usu.getApellidos());
//		u.setFechaNacimiento(usu.getFechaNacimiento());
//		u.setTelefono(usu.getTelefono());
//		u.setDireccion(usu.getDireccion());
//		u.setCorreo(usu.getCorreo());
//		u.setClave(usu.getClave());
//		em.merge(u);
	}

	public void insertarUsuario(Usuario usuario) throws Exception {
		if (findUsuarioByCorreo(usuario.getCorreo()) != null)
			throw new Exception("Ya existe el correo");
		em.persist(usuario);
	}

	public Usuario findUsuarioByCorreo(String correo) {
		return em.find(Usuario.class, correo);
	}

	public List<Usuario> findAllUsuarios() {
		return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
	}

	/*
	 * CRUD ROL USUARIO
	 */

	public void insertarRolUsuario(UsuarioRol usuariorol) {
		em.persist(usuariorol);
	}

	public List<UsuarioRol> findAllUsuariosRoles() {
		return em.createNamedQuery("UsuarioRol.findAll", UsuarioRol.class).getResultList();
	}
	public void actualizarUsuarioRol(UsuarioRol usurol) throws Exception {
		usurol.setIdUsuarioRol(usurol.getIdUsuarioRol());
		usurol.setUsuario(usurol.getUsuario());
		usurol.setRol(usurol.getRol());
		
		em.merge(usurol);
	}


}
