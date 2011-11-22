package iso3.pt.model;

import iso3.pt.dao.PtDAO;
import iso3.pt.util.IncorrectPasswordException;
import iso3.pt.util.UserNotFoundException;

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
	        Profesor pro1 = new Profesor();
	        pro1.setNombre("Diego Lopez de Ipiña");
	        pro1.setDespacho("501");
	        pro1.setDni(78919033);
	        pro1.setEmail("dipiña@deusto.com");
	        pro1.setPassword("profesor");
	        pro1.setTelefono(944874231);
	        
	        Profesor pro2 = new Profesor();
	        pro2.setNombre("Pablo Garcia Bringas");
	        pro2.setPassword("profesor");
	        pro2.setTelefono(944558231);
	        pro2.setDespacho("S3LAB");
	        pro2.setDni(78122033);
	        pro2.setEmail("bringas@deusto.com");
	        
	        Profesor pro3 = new Profesor();
	        pro3.setNombre("Rebeca Cortazar");
	        pro3.setPassword("profesor");
	        pro3.setDni(987654);
	        pro3.setEmail("rebeca@deusto.com");
	        pro3.setTelefono(987654321);
	        
	        
	        //Asignaturas
	        
	        Asignatura asig1 = new Asignatura(30, "Ingeniería del Software 3", new Float(5.5));
	        asig1.setProfesor(pro1);
	        
	        Asignatura asig2 = new Asignatura(40, "Compiladores I", new Float(7.1));
	        asig2.setProfesor(pro2);
	        
	        Asignatura asig3 = new Asignatura(50, "Analisis y Diseño de Sistemas de Información", new Float(0.1));
	        asig3.setProfesor(pro3);
	        
	        Asignatura asig4 = new Asignatura(60, "Seminario de .NET", new Float(6.0));
	        asig4.setProfesor(pro1);
	        
	              
	        //Unidades (Añadidas a Asignaturas)
	        Unidad u1 = new Unidad("Ud1", "Unidad 1", "Contenido 1");
	        Set<Unidad> su = new HashSet<Unidad>();
	        su.add(u1);
	        asig1.setUnidades(su);
	        
	        Unidad u2 = new Unidad("Ud2", "Unidad 2", "Contenido 2");
	        Set<Unidad> su2 = new HashSet<Unidad>();
	        su2.add(u2);
	        asig2.setUnidades(su2);
	        
	        
	        //Alumnos
	        Alumno al1 = new Alumno(78876896,"alumno", "Tomas Madariaga", "944425123");
	        
	        Alumno al2 = new Alumno(123456789,"alumno", "Victor Prieto", "944425123");
	       
	        Alumno al3 = new Alumno(987654321,"alumno", "Paco de la Barca", "944425123");
	        
	        Alumno al4 = new Alumno(111122222,"alumno", "Jaime Perez", "944425123");
	      
	      	        
	        //Asignaciones Alumnos-Asignaturas
	        Set<Asignatura> sa1 = new HashSet<Asignatura>();
	        sa1.add(asig1);
	        sa1.add(asig4);
	        al1.setAsignaturas(sa1);
	        
	        Set<Asignatura> sa2 = new HashSet<Asignatura>();
	        sa2.add(asig2);
	        sa2.add(asig1);
	        sa2.add(asig3);
	        al2.setAsignaturas(sa2);
	        
	        Set<Asignatura> sa3 = new HashSet<Asignatura>();
	        sa3.add(asig2);
	        sa3.add(asig1);
	        sa3.add(asig3);
	        sa3.add(asig4);
	        al4.setAsignaturas(sa3);
	        
	        //Evaluaciones
	        Evaluacion ev1 = new Evaluacion("ex1", new Float(5.0), al1, asig1);	        
	        Evaluacion ev4 = new Evaluacion("ex2", new Float(5.0), al1, asig2);
	        Evaluacion ev2 = new Evaluacion("ex3", new Float(5.0), al1, asig3);
	        Evaluacion ev3 = new Evaluacion("ex4", new Float(5.0), al1, asig4);
	        
	        /* INSERCIONES
	         * -----------
	         * Orden:
	         * -Asignaturas
	         * -Profesores
	         * -Unidades (En principio no se haria la insert, porque van en cascada!)
	         * -Alumnos
	         * -Evaluaciones
	         */
	        
	        
	        
	        session.save(pro1); 
	        session.save(pro2);
	        session.save(pro3);
	        
	        session.save(asig1);
	        session.save(asig2);
	        session.save(asig3);
	        session.save(asig4);
	        	        
	        session.save(al1);
	        session.save(al2);
	        session.save(al3);
	        session.save(al4);
	        
	        session.save(ev1);
	        session.save(ev2);
	        session.save(ev3);
	        session.save(ev4);
	        
	        
	        //Commit
	        tx.commit();
	        //session.close();
	        //sessionFactory.close();
	       
	        System.out.println("Done inserciones1!");
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
	        System.out.println(dao.getEvaluaciones(1, 98789));
	        
	        System.out.println("PRUEBA DE GET UNIDADES");
	        System.out.println(dao.getUnidades(1));
	        
	        System.out.println("PRUEBA DE GET ASIGNATURAS");
	        System.out.println(dao.getAsignaturas());
	        
	        System.out.println("PRUEBA DE GET ASIGNATURA");
	        System.out.println(dao.getAsignatura(1));
	        
	        System.out.println("PRUEBA DE GET ALUMNOS");
	        System.out.println(dao.getAlumno(98789));
	        
	        System.out.println("PRUEBA LOGIN ALUMNO");
	        try {
				System.out.println(dao.loginAlumno(98789, "rinoceronte"));
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (IncorrectPasswordException e) {
				e.printStackTrace();
			}
			
			System.out.println("PRUEBA DE GET ASIGNATURAS POR ALUMNO");
			System.out.println(dao.getAsignaturas(98789));
			
			System.out.println("PRUEBA DE MATRICULAR");
			System.out.println("Asignaturas antes: " + dao.getAsignaturas(123456));
			dao.matricular(123456, 1);
			System.out.println("Asignaturas despues: " + dao.getAsignaturas(123456));
			dao.getAlumnos(1);
			
			System.out.println("PRUEBA DE DESMATRICULAR");
			dao.desmatricular(123456, 1);
			System.out.println("Asignaturas despues despues: " + dao.getAsignaturas(123456));
			
			System.out.println("PRUEBA DE LOGIN PROFESOR");
			try {
				System.out.println(dao.loginProfesor(78919033, "money"));
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (IncorrectPasswordException e) {
				e.printStackTrace();
			}
			
			System.out.println("PRUEBA DE GET ASIGNATURAS PROFESOR");
			System.out.println(dao.getAsignaturasProfesor(1));
			
			System.out.println("PRUEBA DE GET EVALUACIONES DE ASIGNATURA");
			System.out.println(dao.getEvaluacionesAsignatura(1));
			
			System.out.println("PRUEBA DE ADD EVALUACION");
			dao.addEvaluacion("KAKA", new Float(3.2), 1, 123456);
			System.out.println(dao.getEvaluacionesAsignatura(1));
			
			System.out.println("PRUEBA DE EVALUACIONES ORDERED BY ASIGNATURA");
			System.out.println(dao.getEvaluacionesOrderedByAsignatura(98789));
		}
		
	    public static void main(String[] args) {
	    	Test t1 = new Test();
	    	t1.inserciones1();
	    	t1.pruebaDAO();
	    	t1.close();
	    }
	}
