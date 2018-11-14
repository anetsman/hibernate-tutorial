package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {

            int studentID = 1;

            //get a new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentID);

            Student newStudent = session.get(Student.class, studentID);

            System.out.println("Updating student...");
            newStudent.setFirstName("Scooby");

            session.getTransaction().commit();

            //Update email for all students
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Update email for all students");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
