package iso3.pt.dao;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;
import iso3.pt.util.IncorrectPasswordException;
import iso3.pt.util.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class PtDAO implements IptDAO {

	//ATENCION HAY QUE MIRAR SI LAS QUERYS ESTAN BIEN
	private Map<Integer,Asignatura> cacheAsig;
	private Session session;
	private SessionFactory sessionFactory;
	
	//Singelton Pattern
	private static PtDAO instance;
	
	protected PtDAO(SessionFactory sf)
	{
		this.sessionFactory = sf;
		this.session = this.sessionFactory.openSession();
		loadCache();
	}
	
	public static PtDAO getInstance(){
		if(instance==null)
		{
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			instance = new PtDAO(sf);
		}
		return instance;
	}
	
	//Cargar las asignaturas en la cache. 
	private void loadCache(){
		//Session restart
		this.session.close();
		this.session = this.sessionFactory.openSession();
		//Load cache
		this.cacheAsig = new HashMap<Integer,Asignatura>();
		@SuppressWarnings("unchecked")
		List<Asignatura> asig = this.session.createQuery("from Asignatura").list();
		for(Asignatura a : asig)
		{
			this.cacheAsig.put(a.getCodigo() , a);
		}
		
	}
	
	@Override
	public Profesor getProfesor(int idAsignatura) {
		
		return cacheAsig.get(idAsignatura).getProfesor();
	}

	@Override
	public Set<Alumno> getAlumnos(int idAsignatura) {

		return cacheAsig.get(idAsignatura).getAlumnos();
	}

	@Override
	public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno) {
		List<Evaluacion> lista = session.createQuery("from Evaluacion as ev where ev.alumno.dni = " + idAlumno + " order by EVAL_ASIG").list();
		return lista;
	}

	@Override
	public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno) {
		Asignatura asig = cacheAsig.get(idAsignatura);
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
	public void addEvaluacion(String concepto, float nota, int idAsignatura,
			int idAlumno) {
		
		Transaction tx = session.beginTransaction();
		Evaluacion eval = new Evaluacion(concepto, nota, this.getAlumno(idAlumno), this.getAsignatura(idAsignatura));
		session.save(eval);
		tx.commit();	

	}

	@Override
	public Set<Unidad> getUnidades(int idAsignatura) {
		
		return cacheAsig.get(idAsignatura).getUnidades();
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		Collection<Asignatura> asig = cacheAsig.values();
		Set<Asignatura> asigs = new HashSet<Asignatura>();
		for(Asignatura asig1 : asig)
		{
			asigs.add(asig1);
		}
		return asigs;
	}

	@Override
	public Alumno getAlumno(int id) { //ID == DNI?? ID DE QUE?
		Alumno al = null;
		@SuppressWarnings("unchecked")
		List<Alumno> alumnos = session.createQuery("from Alumno as alum where alum.dni = " + id).list();
		if(!alumnos.isEmpty())
			al = alumnos.get(0);
		return al;
	}

	@Override
	public Asignatura getAsignatura(int id) { // ID? ID DE QUE???
		
		return cacheAsig.get(id);
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) { // ID ALUMNO == DNI ????
		
		Collection<Asignatura> asig = cacheAsig.values();
		Set<Asignatura> asignaturas = new HashSet<Asignatura>();
		for(Asignatura asig1 : asig)
		{
			asignaturas.add(asig1);
		}
		return asignaturas;
	
	}

	@Override
	public void matricular(int idAlumno, int idAsignatura) {
		Transaction tx = session.beginTransaction();
		
		Alumno alumno = (Alumno)session.get(Alumno.class, idAlumno);
		Set<Asignatura> setasig = alumno.getAsignaturas();
		setasig.add(cacheAsig.get(idAsignatura));
		alumno.setAsignaturas(setasig);
		
		session.save(alumno);
		tx.commit();
		loadCache();
	}

	@Override
	public void desmatricular(int idAlumno, int idAsignatura) {
		Transaction tx = session.beginTransaction();
		
		Alumno alumno = (Alumno)session.get(Alumno.class, idAlumno);
		Set<Asignatura> setasig = alumno.getAsignaturas();
		setasig.remove(cacheAsig.get(idAsignatura));
		alumno.setAsignaturas(setasig);
		
		session.save(alumno);
		tx.commit();
		loadCache();
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		
		Set<Asignatura> asignaturas = new HashSet<Asignatura>();
		Iterator it = cacheAsig.entrySet().iterator();
		while(it.hasNext()){
			Asignatura asig = (Asignatura) it.next();
			if(asig.getProfesor().getId() == idProfesor) asignaturas.add(asig);
		}
		return asignaturas;
	}

	@Override
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) {
		
		List<Evaluacion> evall = session.createQuery("from Evaluacion as ev where ev.asignatura.id = " + idAsignatura).list();
		return evall;
	}

	@Override
	public Alumno loginAlumno(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		Alumno alumno = getAlumno(dni);
		if(alumno == null)
			throw new UserNotFoundException("ERROR: User not found");
		else if (!alumno.getPassword().equals(pass))
			throw new IncorrectPasswordException("ERROR: Incorrect password");
		return alumno;
	}

	@Override
	public Profesor loginProfesor(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		Profesor profesor = this.getProfesorByDni(dni);
		if(profesor == null)
			throw new UserNotFoundException("ERROR: User not found");
		else if (!profesor.getPassword().equals(pass))
			throw new IncorrectPasswordException("ERROR: Incorrect password");
		return profesor;
		
	}

	@Override
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException {
		List<Profesor> profl = session.createQuery("from Profesor as prof where prof.dni = " + dni).list();		
		Profesor prof = null;
		if(!profl.isEmpty())
			prof = profl.get(0);
		return prof;
	}

}
