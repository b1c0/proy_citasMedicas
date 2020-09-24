package citasmedicas.controller;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import citasmedicas.model.entities.Medico;
import citasmedicas.model.entities.SolicitudCita;
import citasmedicas.model.entities.Usuario;
import citasmedicas.model.manager.ManagerPaciente;
import citasmedicas.view.util.JSFUtil;


@Named
@SessionScoped
//@ConversationScoped
public class BeanSolicitudes implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private BeanUsuario ub;
	@EJB
	private ManagerPaciente mPaciente;
	
	private List<SolicitudCita> listaSolicitudes;
	private List<SolicitudCita> listaProximasCitasPaciente;
	private List<SolicitudCita> listaMisSolicitudesPaciente;
	private boolean panelColapsado;
    private int id_usuario;
	private SolicitudCita solicitudPaciente;
	private int codigoMedico;
	private List<Medico> listaMedicos;
	private Date fechaCita;
	private Date horaCita;
	private SolicitudCita solicitudSeleccionada;
	
	//condiciones de la hora
	private LocalTime minTime = LocalTime.of(8, 0);
	private LocalTime maxTime = LocalTime.of(18, 0);
	
	public BeanSolicitudes() {
		
		
	}
	
	@PostConstruct
	public void inicializar() {
		id_usuario = ub.getId_usuario();
		listaSolicitudes=mPaciente.findAllSolicitudCitas();
		listaProximasCitasPaciente=mPaciente.findAllMiSProximasCitas(id_usuario);
		listaMisSolicitudesPaciente=mPaciente.findMiSCitas(id_usuario);
		listaMedicos=mPaciente.findAllMedicos();
		solicitudPaciente = new SolicitudCita();
		panelColapsado=true;
	}
	
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	
	
	
	public void actionListenerInsertarSolicitudCitaPaciente() {
		try {
			Usuario usuario = mPaciente.findUsuarioById(id_usuario);
			Medico medico = mPaciente.findMedicoById(codigoMedico);
			solicitudPaciente.setUsuario(usuario);; //USUARIO PACIENTE
			solicitudPaciente.setMedico(medico);;//MEDICO
			solicitudPaciente.setFechaCita(fechaCita);//FECHA
			solicitudPaciente.setHoraCita(new java.sql.Time(horaCita.getTime()));//HORA
			solicitudPaciente.setEstadoSolicitudPaciente("A");//ESTADO SOLICITUD PACIENTE
			solicitudPaciente.setEstadoSolicitudMedico("P"); //ESTADO SOLICITUD MEDICO
			solicitudPaciente.setEstadoSolicitud(true);//ESTADO VIGENCIA DE SOLICITUD (VIGENTE / CADUCA)
			System.out.println("INGRESO DE DATOS***************************************////***************");
			System.out.println("id paciente: "+id_usuario+"ID MEDICO: "+codigoMedico+" FECHA CITA:"+fechaCita+" HORA CITA: "+horaCita );
			mPaciente.insertarSolicitudPaciente(solicitudPaciente); 
			listaMisSolicitudesPaciente=mPaciente.findMiSCitas(id_usuario);
			solicitudPaciente = new SolicitudCita();
			JSFUtil.crearMensajeInfo("La Solicitud a sido enviada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerSeleccionarSolicitud(SolicitudCita solicitud) {
		solicitudSeleccionada = solicitud;
	}
	
	public void actionListenerActualizarSolicitudPaciente() {
		try {
			mPaciente.actualizarSolicitudCitaPaciente(solicitudSeleccionada);
			listaMisSolicitudesPaciente=mPaciente.findMiSCitas(id_usuario);
			listaProximasCitasPaciente=mPaciente.findAllMiSProximasCitas(id_usuario);
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
    public void buscaryActualizarSolicitud(SolicitudCita solicitud) {
    	
    	actionListenerSeleccionarSolicitud(solicitud);
    	actionListenerActualizarSolicitudPaciente();
    	
    }
	
	//--------------------------------------------------------------GETTERS & SETTERS

	public List<SolicitudCita> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<SolicitudCita> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public List<SolicitudCita> getListaProximasCitasPaciente() {
		return listaProximasCitasPaciente;
	}

	public void setListaProximasCitasPaciente(List<SolicitudCita> listaProximasCitasPaciente) {
		this.listaProximasCitasPaciente = listaProximasCitasPaciente;
	}

	public List<SolicitudCita> getListaMisSolicitudesPaciente() {
		return listaMisSolicitudesPaciente;
	}

	public void setListaMisSolicitudesPaciente(List<SolicitudCita> listaMisSolicitudesPaciente) {
		this.listaMisSolicitudesPaciente = listaMisSolicitudesPaciente;
	}

	public int getCodigoMedico() {
		return codigoMedico;
	}

	public void setCodigoMedico(int codigoMedico) {
		this.codigoMedico = codigoMedico;
	}

	public List<Medico> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<Medico> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Date getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(Date horaCita) {
		this.horaCita = horaCita;
	}

	public LocalTime getMinTime() {
		return minTime;
	}

	public void setMinTime(LocalTime minTime) {
		this.minTime = minTime;
	}

	public LocalTime getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(LocalTime maxTime) {
		this.maxTime = maxTime;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}




	
	
	
	
	

}
