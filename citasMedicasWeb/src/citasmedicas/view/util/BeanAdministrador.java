package citasMedicas.view.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import proyecto.model.entities.*;
import proyecto.model.managers.ManagerAdministrador;
import proyecto.model.managers.ManagerLogin;

import java.io.Serializable;

@Named
@SessionScoped
public class BeanAdministrador implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAdministrador mAdministrador;
	private ManagerLogin mLogin;

	private List<Especialidad> listaEspecialidades;
	private List<Enfermedad> listaEnfermedades;
	private List<Rol> listaRoles;
	private List<Usuario> listaUsuarios;
	private List<Medico> listaMedicos;
	private List<UsuarioRol> listaUsuarioRoles;
	private Especialidad especialidad;
	private Enfermedad enfermedad;
	private Rol rol;
	private Usuario usuario;
	private Medico medico;
	private UsuarioRol usuariorol;
	private List<SelectItem> listaEstados;
	private boolean estado;
	private boolean panelColapsado;
	private String usuarioLogin;
	private String claveLogin;
	private String tipoLogin;
	/* selecciones */
	private Rol rolSeleccionado;
	private Especialidad especialidadSeleccionado;
	private Enfermedad enfermedadSeleccionada;
	private Usuario usuarioSeleccionado;
	private UsuarioRol usuariorolSeleccionado;
	private Medico medicoSeleccionado;

	public BeanAdministrador() {
	}

	@PostConstruct
	public void inicializar() {

		listaEspecialidades = mAdministrador.findAllEspecialidades();
		especialidad = new Especialidad();
		listaEnfermedades = mAdministrador.findAllEnfermedades();
		enfermedad = new Enfermedad();
		listaRoles = mAdministrador.findAllRoles();
		rol = new Rol();
		listaMedicos = mAdministrador.findAllMedicos();
		medico = new Medico();
		listaUsuarios = mAdministrador.findAllUsuarios();
		usuario = new Usuario();
		listaUsuarioRoles = mAdministrador.findAllUsuariosRoles();
		usuariorol = new UsuarioRol();

		listaEstados = new ArrayList<SelectItem>();
		listaEstados.add(new SelectItem(true, "Activo"));
		listaEstados.add(new SelectItem(false, "Inactivo"));

		panelColapsado = true;

	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	/*
	 * LOGUEO
	 */
	public String actionLogin() {
		String ruta=mLogin.verificarUsuario(usuarioLogin, claveLogin, tipoLogin);
		return ruta;
	}

	public String cerrarSesion() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	/*
	 * ACTION DE CRUD
	 */

	public void actionListenerInsertarEspecialidad() {
		try {
			mAdministrador.insertarEspecialidad(especialidad);
			listaEspecialidades = mAdministrador.findAllEspecialidades();
			especialidad = new Especialidad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarEnfermedad() {
		try {
			mAdministrador.insertarEnfermedad(enfermedad);
			listaEnfermedades = mAdministrador.findAllEnfermedades();
			enfermedad = new Enfermedad();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarRol() {
		try {
			mAdministrador.insertarRol(rol);
			listaRoles = mAdministrador.findAllRoles();
			rol = new Rol();
			JSFUtil.crearMensajeInfo("Datos ingresados correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarUsuario() {
		try {
			mAdministrador.insertarUsuario(usuario);
			listaUsuarios = mAdministrador.findAllUsuarios();
			usuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarMedico() {
		try {
			mAdministrador.insertarMedico(medico);
			listaMedicos = mAdministrador.findAllMedicos();
			medico = new Medico();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarEspecialidad(Especialidad esp) {
		especialidadSeleccionado = esp;
		JSFUtil.crearMensajeInfo("Estado desactivado");

	}

	public void actionListenerEliminarRol(Rol ro) {
		rolSeleccionado = ro;
		JSFUtil.crearMensajeInfo("Estado desactivado");
	}

	/* Seleccionar crud actualizar */
	public void actionListenerSeleccionarRol(Rol r) {
		rolSeleccionado = r;
	}

	public void actionListenerSeleccionarEspecialidad(Especialidad esp) {
		especialidadSeleccionado = esp;
	}

	public void actionListenerSeleccionarEnfermedad(Enfermedad enf) {
		enfermedadSeleccionada = enf;
	}

	public void actionListenerSeleccionarUsuario(Usuario usu) {
		usuarioSeleccionado = usu;
	}

	public void actionListenerSeleccionarUsuarioRol(UsuarioRol usurol) {
		usuariorolSeleccionado = usurol;
	}

	public void actionListenerSeleccionarMedico(Medico med) {
		medicoSeleccionado = med;
	}

	public void actionListenerActualizarRol() {
		try {
			mAdministrador.actualizarRol(rolSeleccionado);
			listaRoles = mAdministrador.findAllRoles();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarEspecialidad() {
		try {
			mAdministrador.actualizarEspecialidad(especialidadSeleccionado);
			listaEspecialidades = mAdministrador.findAllEspecialidades();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarEnfermedad() {
		try {
			mAdministrador.actualizarEnfermedad(enfermedadSeleccionada);
			listaEnfermedades = mAdministrador.findAllEnfermedades();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarUsuario() {
		try {
			mAdministrador.actualizarUsuario(usuarioSeleccionado);
			listaUsuarios= mAdministrador.findAllUsuarios();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarMedico() {
		try {
			
			mAdministrador.actualizarUsuario(usuarioSeleccionado);
			listaMedicos = mAdministrador.findAllMedicos();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarUsuarioRol() {
		try {
			mAdministrador.actualizarUsuarioRol(usuariorolSeleccionado);
			listaUsuarioRoles = mAdministrador.findAllUsuariosRoles();
					JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	/*
	 * CREACION DE GET Y SET
	 * 
	 */

	public List<Especialidad> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public List<Enfermedad> getListaEnfermedades() {
		return listaEnfermedades;
	}

	public void setListaEnfermedades(List<Enfermedad> listaEnfermedades) {
		this.listaEnfermedades = listaEnfermedades;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Medico> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<Medico> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public List<UsuarioRol> getListaUsuarioRoles() {
		return listaUsuarioRoles;
	}

	public void setListaUsuarioRoles(List<UsuarioRol> listaUsuarioRoles) {
		this.listaUsuarioRoles = listaUsuarioRoles;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public UsuarioRol getUsuariorol() {
		return usuariorol;
	}

	public void setUsuariorol(UsuarioRol usuariorol) {
		this.usuariorol = usuariorol;
	}

	public List<SelectItem> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<SelectItem> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getClaveLogin() {
		return claveLogin;
	}

	public void setClaveLogin(String claveLogin) {
		this.claveLogin = claveLogin;
	}

	public String getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(String tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

	public Rol getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(Rol rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}

	public Especialidad getEspecialidadSeleccionado() {
		return especialidadSeleccionado;
	}

	public void setEspecialidadSeleccionado(Especialidad especialidadSeleccionado) {
		this.especialidadSeleccionado = especialidadSeleccionado;
	}

	public Enfermedad getEnfermedadSeleccionada() {
		return enfermedadSeleccionada;
	}

	public void setEnfermedadSeleccionada(Enfermedad enfermedadSeleccionada) {
		this.enfermedadSeleccionada = enfermedadSeleccionada;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public UsuarioRol getUsuariorolSeleccionado() {
		return usuariorolSeleccionado;
	}

	public void setUsuariorolSeleccionado(UsuarioRol usuariorolSeleccionado) {
		this.usuariorolSeleccionado = usuariorolSeleccionado;
	}

	public Medico getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(Medico medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

}
