package citasmedicas.view.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase JSF utilitaria, implementa varias funciones auxiliares 
 * para la creación de mensajes personalizados que podran ser 
 * visualizados en la capa view (páginas JSF).
 * @author alexandercastro
 *
 */
public class JSFUtil {
	//Mensaje Informativo
	public static void crearMensajeInfo(String mensaje) {
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	//Mensaje de advertencia
	public static void crearMensajeWarning(String mensaje) {
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	//Mensaje de error
	public static void crearMensajeError(String mensaje) {
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
}
