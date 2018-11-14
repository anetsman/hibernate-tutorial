package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

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

            //delete student
            System.out.println("Deleting student..." + newStudent);
            session.delete(newStudent);

            //delete student alternatively
            System.out.println("deleting Student alternatively");
            session.createQuery("delete from Student where id=2").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
