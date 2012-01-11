package iso3.pt.dao;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.*;
//import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Configuration;

public class PtDAO implements IPtDAO {
	
	private static PtDAO instance;
	private SessionFactory sessionFactory;
	private Map<Integer,Asignatura> mapAsig;
	private Session session;

	protected PtDAO(SessionFactory sf)
	{
		sessionFactory = sf;
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		this.loadCache();
	}
	
	//para el TEST
	 public static PtDAO getInstance(SessionFactory sf)
	{
		if(instance == null)
			instance = new PtDAO(sf);
		return instance;
	}
	
	public static PtDAO getInstance()
	{
		if(instance == null)
		{
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			instance = new PtDAO(sf);
		}
		return instance;
	}
	
	private void loadCache()
	{
		//Reinicio de la sesión
		session.close();
		session = sessionFactory.openSession();
		//Carga de la caché
		mapAsig = new HashMap<Integer,Asignatura>();
		List<Asignatura> asig = session.createQuery("from Asignatura").list();
	
		Iterator iter = asig.iterator();
		while ( iter.hasNext() ) {
			Asignatura asignatura = (Asignatura)iter.next();
			mapAsig.put(asignatura.getId(), asignatura);
			System.out.println("cargada "+asignatura.getNombre());
		   
		}
	}
	
	@Override
	public Profesor getProfesor(int idAsignatura) {
		Asignatura asig = mapAsig.get(idAsignatura);
		return asig.getProfesor();
	}

	@Override
	public Set<Alumno> getAlumnos(int idAsignatura) {
		Asignatura asig = mapAsig.get(idAsignatura);
		return asig.getAlumnos();
	}

	@Override
	public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno) {
		List<Evaluacion> evall = session.createQuery("from Evaluacion as ev where ev.alumno.dni = " + idAlumno + " order by EVAL_ASIG").list();
		return evall;
	}

	@Override
	public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno) {
		Asignatura asig = mapAsig.get(idAsignatura);
		Set<Alumno> alumnos = asig.getAlumnos();
		Set<Evaluacion> eval = null;
		for(Alumno al : alumnos)
		{
			if(al.getDni() == idAlumno)
			{
				eval = al.getEvaluaciones();
			}
		}
		return eval;
	}

	@Override
	public void addEvaluacion(String concepto, float nota, int idAsignatura, int idAlumno) {
		Transaction trans = session.beginTransaction();
		Evaluacion eval = new Evaluacion(this.getAsignatura(idAsignatura), this.getAlumno(idAlumno), nota, concepto);
		session.save(eval);
		trans.commit();		
	}

	@Override
	public Set<Unidad> getUnidades(int idAsignatura) {
		Set<Unidad> uds = mapAsig.get(idAsignatura).getUnidades();
		return uds;
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		Collection<Asignatura> asig = mapAsig.values();
		Set<Asignatura> asigs = new HashSet<Asignatura>();
		for(Asignatura asig1 : asig)
		{
			asigs.add(asig1);
		}
		return asigs;
	}

	@Override
	public Alumno getAlumno(int id) {
		Alumno al = null;
		List<Alumno> alumnos = session.createQuery("from Alumno as alum where alum.dni = " + id).list();
		if(!alumnos.isEmpty())
			al = alumnos.get(0);
		return al;
	}

	@Override
	public Asignatura getAsignatura(int id) {
		return mapAsig.get(id);
	}

	
	@Override
	public Alumno loginAlumno(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException
	{	
		Alumno alumno = getAlumno(dni);
		if(alumno == null)
			throw new UserNotFoundException("ERROR: User not found");
		else if (!alumno.getPassword().equals(pass))
			throw new IncorrectPasswordException("ERROR: Incorrect password");
		return alumno;
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) {
		Alumno al = getAlumno(idAlumno);
		Set<Asignatura> asig = null;
		if(al != null)
			asig = al.getAsignaturas();
		return asig;
	}

	@Override
	public void matricular(int idAlumno, int idAsignatura) {	
		Transaction tx = session.beginTransaction();
		
		Alumno alumno = (Alumno)session.get(Alumno.class, idAlumno);
		Set<Asignatura> setasig = alumno.getAsignaturas();
		setasig.add(mapAsig.get(idAsignatura));
		alumno.setAsignaturas(setasig);
		
		session.save(alumno);
		tx.commit();
		loadCache();
	}

	@Override
	public void desmatricular(int idAlumno, int idAsignatura) {
		Transaction trans = session.beginTransaction();
		
		Alumno alumno = (Alumno)session.get(Alumno.class, idAlumno);
		Set<Asignatura> setasig = alumno.getAsignaturas();
		setasig.remove(mapAsig.get(idAsignatura));
		alumno.setAsignaturas(setasig);
		
		session.save(alumno);
		trans.commit();
		loadCache();
	}

	@Override
	public Profesor loginProfesor(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException{
		Profesor prof = this.getProfesorByDni(dni);
		if(prof == null)
			throw new UserNotFoundException("ERROR: User not found");
		else if (!prof.getPassword().equals(pass))
			throw new IncorrectPasswordException("ERROR: Incorrect password");
		return prof;
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		Set<Asignatura> setAs = new HashSet<Asignatura>();
		for(Asignatura asig : mapAsig.values())
		{
			if(asig.getProfesor().getId() == idProfesor)
				setAs.add(asig);
		}
		return setAs;
	}

	@Override
	public Profesor getProfesorByDni(int dni) {
		List<Profesor> profl = session.createQuery("from Profesor as prof where prof.dni = " + dni).list();		
		Profesor prof = null;
		if(!profl.isEmpty())
			prof = profl.get(0);
		return prof;
	}

	@Override
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) {
		List<Evaluacion> evall = session.createQuery("from Evaluacion as ev where ev.asignatura.id = " + idAsignatura).list();
		return evall;
	}

}
