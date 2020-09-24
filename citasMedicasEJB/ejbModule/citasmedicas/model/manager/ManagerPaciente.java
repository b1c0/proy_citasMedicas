package citasmedicas.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import citasmedicas.model.entities.Medico;
import citasmedicas.model.entities.SolicitudCita;
import citasmedicas.model.entities.Usuario;


/**
 * Session Bean implementation class ManagerPaciente
 */
@Stateless
@LocalBean
public class ManagerPaciente {

	@PersistenceContext
	private EntityManager em;

	public ManagerPaciente() {

	}

	public List<SolicitudCita> findAllSolicitudCitas() {
		return em.createNamedQuery("SolicitudCita.findAll", SolicitudCita.class).getResultList();
	}

	public List<SolicitudCita> findAllMiSProximasCitas(int paciente) {
		Query q = em.createQuery("select d from SolicitudCita d where d.usuario=" + paciente+" and estadoSolicitudPaciente='P'", SolicitudCita.class);
		return q.getResultList();
	}
	
	public List<SolicitudCita> findMiSCitas(int paciente) {
		Query q = em.createQuery("select d from SolicitudCita d where d.usuario=" + paciente, SolicitudCita.class);
		return q.getResultList();
	}
	
    public List<Medico> findAllMedicos(){
    	return em.createNamedQuery("Medico.findAll", Medico.class).getResultList();
    }
	
    public Medico findMedicoById(int codigoMedico) {
    	return em.find(Medico.class, codigoMedico);
    }
    
    public Usuario findUsuarioById(int codigoPaciente) {
    	return em.find(Usuario.class, codigoPaciente);
    }
    
    public SolicitudCita findSolicitudCitaById(int codigoSolicitud) {
    	return em.find(SolicitudCita.class, codigoSolicitud);
    }
    
	public void insertarSolicitudPaciente(SolicitudCita cita) throws Exception {
		em.persist(cita);
	}
	
    public void actualizarSolicitudCitaPaciente(SolicitudCita solicitud) throws Exception {
    	SolicitudCita e = findSolicitudCitaById(solicitud.getIdSolicitud());
    	if(e==null) {
    		throw new Exception("No existe la solicitud indicada");
    	}
    	e.setEstadoSolicitudPaciente("A");
    	em.merge(e);
    }

}
