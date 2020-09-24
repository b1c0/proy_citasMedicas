package proyecto.model.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {

    /**
     * Default constructor. 
     */
    public ManagerLogin() {
        // TODO Auto-generated constructor stub
    }
    public String verificarUsuario(String correo, String clave, String tipoUsuario) {
    	if(correo.equals("ypcoyagom@utn.edu.ec")&& clave.equals("1234") && tipoUsuario.equals("pct"))
    		return "vistaAdministrador";
    	else
    		return "login";
    	
    }
}
