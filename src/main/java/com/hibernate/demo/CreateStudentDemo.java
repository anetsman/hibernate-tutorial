package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

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
            Student student = new Student("John", "Doe", "jd@gmail.com");

            //begin the transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(student);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
