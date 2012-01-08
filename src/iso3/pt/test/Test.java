package iso3.pt.test;

import iso3.pt.dao.IncorrectPasswordException;
import iso3.pt.dao.PtDAO;
import iso3.pt.dao.UserNotFoundException;
import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class Test { 
	
	SessionFactory sessionFactory;
	
	public Test(){
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public void close(){
        sessionFactory.close();
	}

	public void inserciones1(){
    	
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        //Profesores
        Profesor prof1 = new Profesor("Diego Lopez de Ipina");
        prof1.setDespacho("501");
        prof1.setDni(78919033);
        prof1.setEmail("dipina@deusto.es");
        prof1.setPassword("profesor");
        prof1.setTelefono(944455567);
        
        Profesor prof2 = new Profesor("Alex Rayon");
        prof2.setDespacho("502");
        prof2.setDni(78122033);
        prof2.setEmail("alex@deusto.es");
        prof2.setPassword("profesor");
        prof2.setTelefono(944455568);
        
        
        Profesor prof3 = new Profesor("Rebeca Cortazar");
        prof3.setDespacho("301");
        prof3.setDni(78919034);
        prof3.setEmail("rebeca@deusto.es");
        prof3.setPassword("profesor");  
        prof3.setTelefono(944455569);
        
      //Alumnos
        Alumno al1 = new Alumno(111111111, "Tomas Madariaga", "alumno");
       
        Alumno al2 = new Alumno(222222222, "Victor Prieto", "alumno");
      
        Alumno al3 = new Alumno(333333333, "Paco de la Barca","alumno");
        
        Alumno al4 = new Alumno(444444444, "Jose Maria Perez", "alumno");
        
        
        //Asignaturas
        Asignatura asig1 = new Asignatura("Ingeniería del Software 3", 45, new Float(7.5));
        asig1.setProfesor(prof1);
        
        Asignatura asig2 = new Asignatura("Compiladores I", 78, new Float(4.5));
        asig2.setProfesor(prof2);
        
        Asignatura asig3 = new Asignatura("Analisis y Diseño de Sistemas de Información", 114, new Float(6));
        asig3.setProfesor(prof3);
        
        Asignatura asig4 = new Asignatura("Comercial", 21, new Float(7.5));
        asig4.setProfesor(prof1);
        
              
        //Unidades (Añadidas a Asignaturas)
        Unidad u1 = new Unidad("Ud1", "Unidad 1", "Contenido 1");
        Set<Unidad> su = new HashSet<Unidad>();
        su.add(u1);
        asig1.setUnidades(su);
        
        Unidad u2 = new Unidad("Ud2", "Unidad 2", "Contenido 2");
        Set<Unidad> su2 = new HashSet<Unidad>();
        su2.add(u2);
        asig2.setUnidades(su2);
     
        
        //Asignaciones Alumno - Asignaturas
        Set<Asignatura> setAsig1 = new HashSet<Asignatura>();
        setAsig1.add(asig1);
        setAsig1.add(asig4);
        al1.setAsignaturas(setAsig1);
        
        Set<Asignatura> setAsig2 = new HashSet<Asignatura>();
        setAsig2.add(asig2);
        setAsig2.add(asig1);
        setAsig2.add(asig3);
        al2.setAsignaturas(setAsig2);
        
        Set<Asignatura> setAsig3 = new HashSet<Asignatura>();
        setAsig3.add(asig2);
        setAsig3.add(asig1);
        setAsig3.add(asig3);
        setAsig3.add(asig4);
        al4.setAsignaturas(setAsig3);
        
        //Evaluaciones
        Evaluacion eval1 = new Evaluacion(asig1, al1, new Float(1.2), "Examen 1");	        
        Evaluacion eval2 = new Evaluacion(asig3, al4, new Float(7.2), "Ejercicio 3");
        Evaluacion eval3 = new Evaluacion(asig2, al4, new Float(5.9), "Practica 2");
        Evaluacion eval4 = new Evaluacion(asig2, al1, new Float(8.3), "Examen 2");
        
        /* INSERCION
         * -----------
         * Orden:
         * -Asignaturas
         * -Profesores
         * -Unidades //va en cascada
         * -Alumnos
         * -Evaluaciones
         */

        session.save(asig1);
        session.save(asig2);
        session.save(asig3);
        session.save(asig4);
        
        session.save(prof1); 
        session.save(prof2);
        session.save(prof3);
        
        /* en cascada no se hace
        session.save(u1);
        session.save(u2);
        */
        	        
        session.save(al1);
        session.save(al2);
        session.save(al3);
        session.save(al4);
        
        session.save(eval1);
        session.save(eval2);
        session.save(eval3);
        session.save(eval4);
           
        tx.commit();
        session.close();
        sessionFactory.close();
        System.out.println("Inserciones hecho!!!");
	}
	
	public void pruebaDAO()
	{
		//PRUEBAS DEL DAO
        PtDAO dao = PtDAO.getInstance();
        
        Profesor profe = dao.getProfesor(1);
        System.out.println("EL PROFESOR DE LA ASIGNATURA ES: " + profe.getNombre());
        
        System.out.println("Alumnos en la asignatura:");
        Set<Alumno> seta = dao.getAlumnos(1);
        for(Alumno a : seta)
        {
        	System.out.println(a.getNombre());
        }
        
        System.out.println("PRUEBA DE GET EVALUACIONES:");
        System.out.println(dao.getEvaluaciones(1, 111111111));
        
        System.out.println("PRUEBA DE GET ASIGNATURAS");
        System.out.println(dao.getAsignaturas());
        
        
        System.out.println("PRUEBA DE GET UNIDADES");
        System.out.println(dao.getUnidades(1));
        
        
        System.out.println("PRUEBA DE GET ASIGNATURA 1");
        System.out.println(dao.getAsignatura(1));
        
        System.out.println("PRUEBA DE GET ALUMNOS");
        System.out.println(dao.getAlumno(111111111));
        
        System.out.println("PRUEBA DE GET ASIGNATURAS POR ALUMNO");
		System.out.println(dao.getAsignaturas(111111111));
		

		System.out.println("PRUEBA DE MATRICULAR");
		System.out.println("Asignaturas antes: " + dao.getAsignaturas(222222222));
		dao.matricular(222222222, 4);
		System.out.println("Asignaturas despues: " + dao.getAsignaturas(222222222));
		dao.getAlumnos(1);
		
		System.out.println("PRUEBA DESMATRICULAR");
		dao.desmatricular(222222222, 4);
		System.out.println("Asignaturas despues despues: " + dao.getAsignaturas(222222222));
		
		
		System.out.println("PRUEBA DE GET ASIGNATURAS PROFESOR");
		System.out.println(dao.getAsignaturasProfesor(1));
		
		System.out.println("PRUEBA DE GET EVALUACIONES DE ASIGNATURA");
		System.out.println(dao.getEvaluacionesAsignatura(1));
		
		System.out.println("PRUEBA DE ADD EVALUACION");
		dao.addEvaluacion("Global", new Float(4.2), 1, 222222222);
		System.out.println(dao.getEvaluacionesAsignatura(1));
		
		System.out.println("PRUEBA DE EVALUACIONES ORDERED BY ASIGNATURA");
		System.out.println(dao.getEvaluacionesOrderedByAsignatura(111111111));
		
		 System.out.println("PRUEBA LOGIN ALUMNO");
	        try {
				System.out.println(dao.loginAlumno(123456788, "paco"));
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (IncorrectPasswordException e) {
				e.printStackTrace();
			}
			
			System.out.println("PRUEBA DE LOGIN PROFESOR");
			try {
				System.out.println(dao.loginProfesor(78919033, "mal"));
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (IncorrectPasswordException e) {
				e.printStackTrace();
			}
	}
	
	
    public static void main(String[] args) {
    	Test t = new Test();
    	t.inserciones1();
    	t.pruebaDAO();
    	t.close();
    }
}

