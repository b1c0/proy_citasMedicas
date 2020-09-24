package citasmedicas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import citasmedicas.model.entities.Cita;
import citasmedicas.model.entities.SolicitudCita;
import citasmedicas.model.manager.ManagerMedico;
import citasmedicas.view.util.JSFUtil;



@Named
@SessionScoped
public class BeanMedico implements Serializable {

	public BeanMedico() {
		// TODO Auto-generated constructor stub
	}
	@EJB
	private ManagerMedico mprototipo;
	private List<SolicitudCita> listasolicitudes;
	private int id_med=2;
	private SolicitudCita sol_cita;
	private List<SolicitudCita> listadoaprobado;
	private Cita nuevacita;

	@PostConstruct
	public void inicializar() {
		//listasolicitudes=mprototipo.findAllSolicitudCitas();
		listasolicitudes=mprototipo.findAllSolicitudCitas(id_med);
		listadoaprobado=mprototipo.findAllCitas(id_med);
		nuevacita = new Cita();
		
	}
	public void actionListenerSeleccionarSolicitudCita(SolicitudCita solicitudcita) {
		sol_cita=solicitudcita;
	}
	public void actionListenerSeleccionarActualizar(SolicitudCita s) {
		actionListenerSeleccionarSolicitudCita(s);
		actionListenerActualizarSolicitudCita();
		actionListenerInsertarCita();
		
	}
	public void actionListenerActualizarSolicitudCita() {

		try {
			mprototipo.actualizarSolicitud(sol_cita);		
			listasolicitudes=mprototipo.findAllSolicitudCitas(id_med);
			listadoaprobado=mprototipo.findAllCitas(id_med);
			//JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			//JSFUtil.crearMensajeError(e.getMessage());
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void actionListenerInsertarCita() {
		try {
			SolicitudCita s= mprototipo.findSolicitudById(sol_cita.getIdSolicitud());
			System.out.println("sol_cita "+sol_cita.getIdSolicitud()+" fecha "+sol_cita.getFechaCita());
			nuevacita.setObservacionesCita("");
			nuevacita.setSolicitudCita(s);
			System.out.println("solicitudcita "+s.getIdSolicitud()+"-"+s.getFechaCita());
			nuevacita.setEnfermedad(null);		
			System.out.println("nuevacita enfermedad "+nuevacita.getEnfermedad());
			
			mprototipo.insertarCita(nuevacita);
			listasolicitudes=mprototipo.findAllSolicitudCitas(id_med);
			listadoaprobado=mprototipo.findAllCitas(id_med);	
			nuevacita = new Cita();
			
			JSFUtil.crearMensajeInfo("La Solicitud a sido enviada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public List<SolicitudCita> getListasolicitudes() {
		return listasolicitudes;
	}
	public void setListasolicitudes(List<SolicitudCita> listasolicitudes) {
		this.listasolicitudes = listasolicitudes;
	}
	public SolicitudCita getSol_cita() {
		return sol_cita;
	}
	public void setSol_cita(SolicitudCita sol_cita) {
		this.sol_cita = sol_cita;
	}
	public List<SolicitudCita> getListadoaprobado() {
		return listadoaprobado;
	}
	public void setListadoaprobado(List<SolicitudCita> listadoaprobado) {
		this.listadoaprobado = listadoaprobado;
	}
	public Cita getNuevacita() {
		return nuevacita;
	}
	public void setNuevacita(Cita nuevacita) {
		this.nuevacita = nuevacita;
	}
	

}
