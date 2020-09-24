package citasmedicas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import citasmedicas.model.entities.Usuario;
import citasmedicas.model.entities.UsuarioRol;
import citasmedicas.model.manager.ManagerUsuarios;
import citasmedicas.view.util.JSFUtil;




@Named
@SessionScoped
//@ConversationScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuarios mUsuarios;
	private String correo;
	private String clave;
	private boolean confirmadoLogin;
	private String rol;	
	private String apellido;
	private String nombre;
	private int id_usuario;

	public BeanUsuario() {
	}


	public String actionLogin() {
		try {
			confirmadoLogin = mUsuarios.comprobarUsuario(correo, clave);
			Usuario u = mUsuarios.encontrarUsuarioByCorreo(correo);
			UsuarioRol ur = mUsuarios.obtenerRol(u);
			rol = ur.getRol().getIdRol();
			apellido = u.getApellidos();
			nombre = u.getNombres();
			id_usuario = u.getIdUsuario();
			System.out.println("******************************************************OOOOOOOOOOOOOOOOOOOOOOOOO");
			System.out.println("Existe el usuario: " + confirmadoLogin + " MI CORREO: " + correo + " MI CLAVE: " + clave
					+ " MI ROL: " + rol);
			JSFUtil.crearMensajeInfo("LOGIN CORRECTO");
			if (confirmadoLogin) {
				if (rol.equals("adm")) {
					return "admin";
				} else if (rol.equals("med")) {
					return "medico";
				} else if (rol.equals("pct")) {
					return "paciente";
				} else {
					return "login";
				}
			}

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	public String actionCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}

	public void actionListenerVerificarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = ec.getRequestPathInfo();

		try {
			if (confirmadoLogin == false) {
				// el usuario no ingreso por login
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				// el usuario hizo login pero se verifica el control de acceso
				if (requestPath.contains("medico") && !rol.equals("mdc")) {
					ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
				} else if (requestPath.contains("paciente") && !rol.equals("pct")) {
					ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
				} else if (requestPath.contains("admin") && !rol.equals("adm")) {
					ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


//---------------------------------------------------getters & setters	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


	
}
