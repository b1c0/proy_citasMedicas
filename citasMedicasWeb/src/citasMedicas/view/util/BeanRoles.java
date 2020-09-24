package citasMedicas.view.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto.model.entities.*;
import proyecto.model.managers.ManagerAdministrador;
@Named
@SessionScoped
public class BeanRoles implements Serializable {

	private static final long serialVersionUID =1L;
	@EJB
	private ManagerAdministrador mAdministrador;
	private List<Rol> listaRoles;
	
	public BeanRoles() {
	}
	@PostConstruct
	public void inicializar() {
		listaRoles=mAdministrador.findAllRoles();
		
	}
	public List<Rol> getListaRoles() {
		return listaRoles;
	}
	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

}
