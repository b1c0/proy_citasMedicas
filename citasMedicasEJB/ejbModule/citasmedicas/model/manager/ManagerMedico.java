package citasmedicas.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import citasmedicas.model.entities.Cita;
import citasmedicas.model.entities.SolicitudCita;


/**
 * Session Bean implementation class ManagerMedico
 */
@Stateless
@LocalBean
public class ManagerMedico {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public ManagerMedico() {
        // TODO Auto-generated constructor stub
    }
    public void insertarCita(Cita c) throws Exception {
		em.persist(c);
	}
    public List <SolicitudCita> findAllSolicitudCitas(int medico){
    	Query q= em.createQuery("select d from SolicitudCita d where d.medico="+medico+" and d.estadoSolicitudPaciente='A' and d.estadoSolicitudMedico='P'",SolicitudCita.class);
    	return q.getResultList();
    }
    public SolicitudCita findSolicitudById(int idSolicitud) {
    	return em.find(SolicitudCita.class,idSolicitud);
    }
    public List<SolicitudCita> findAllCitas(int medico) {
    	Query q= em.createQuery("select d from SolicitudCita d where d.medico="+medico+" and d.estadoSolicitudMedico='A' and d.estadoSolicitudPaciente='A'",SolicitudCita.class);
    	return q.getResultList();
	}
    public void actualizarSolicitud(SolicitudCita solicitudcita) throws Exception{
    	
    	SolicitudCita sc= findSolicitudById(solicitudcita.getIdSolicitud());
    	
    	if(sc==null) {
    		throw new Exception("No existe la solicitud de cita especificada.");
    	}
    	sc.setEstadoSolicitudMedico("A");
    	System.out.println("AIUDA"+sc);
    	em.merge(sc);
    }


}
