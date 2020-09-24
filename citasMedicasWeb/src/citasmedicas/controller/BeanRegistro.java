package citasmedicas.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import citasmedicas.model.entities.Rol;
import citasmedicas.model.entities.Usuario;
import citasmedicas.model.entities.UsuarioRol;
import citasmedicas.model.manager.ManagerUsuarios;
import citasmedicas.view.util.JSFUtil;

@Named
@SessionScoped
public class BeanRegistro implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuarios mUsuarios;
	private Usuario nuevoUsuario;
	
	
	@PostConstruct
	public void inicializar() {
		nuevoUsuario = new Usuario();
	}
	public void actionListenerREgistrarUsuario() {
		System.out.println(
				"CEDULA: "+nuevoUsuario.getCedula()+
				" Apellidos: " + nuevoUsuario.getApellidos()+
				" NOMBRES: " +nuevoUsuario.getNombres() +
				" FECHA: " +nuevoUsuario.getFechaNacimiento()+ 
				" DIRECCION: " +nuevoUsuario.getDireccion()+ 
				" CORREO: " +nuevoUsuario.getCorreo()+ 
				" CLAVE: " +nuevoUsuario.getClave() );
//		Usuario u = new Usuario();
//		u.setCedula(ncedula);
//		u.setApellidos(napellidos);
//		u.setNombres(nnombres);
//		u.setFechaNacimiento(nfechanacimiento);
//		u.setDireccion(ndireccion);
//		u.setCorreo(ncorreo);
//		u.setClave(nclave);
		try {
			mUsuarios.insertarNuevoUsuario(nuevoUsuario);
			UsuarioRol ur = new UsuarioRol();
			Rol rol = mUsuarios.findRolById("pct");
			ur.setUsuario(nuevoUsuario);
			ur.setRol(rol);
			ur.setEstadoUsuario(true);
			mUsuarios.insertarNuevoUsuarioROL(ur);
			nuevoUsuario = new Usuario();
			JSFUtil.crearMensajeInfo("Te has registrado");
		}catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public Usuario getNuevoUsuario() {
		return nuevoUsuario;
	}

	public void setNuevoUsuario(Usuario nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}
	
	
}
