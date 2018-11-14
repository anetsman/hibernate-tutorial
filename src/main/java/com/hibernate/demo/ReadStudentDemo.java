package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {
            //create student object
            System.out.println("Creating new student...");
            Student student = new Student("Daffy", "Duck", "dd@gmail.com");

            //begin the transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);

            //commit transaction
            session.getTransaction().commit();

            //find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + student.getId());

            //get a new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + student.getId());

            Student newStudent = session.get(Student.class, student.getId());

            System.out.println("Get complete: " + newStudent);

            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
