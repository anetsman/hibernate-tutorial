package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {
            //begin the transaction
            session.beginTransaction();

            //query students
            List<Student> students = session.createQuery("from Student").getResultList();

            //display the students
            displayStudents(students);

            //query students with last name='Doe'
            students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
            System.out.println("\nStudents with last name Doe: ");
            displayStudents(students);

            //query students last name Doe or first name Daffy
            students = session.createQuery("from Student s where " +
                    "s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
            System.out.println("\nStudents last name Doe or first name Daffy");
            displayStudents(students);

            //query students with email LIKE gmail.com
            students = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
            System.out.println("\nStudents with email LIKE gmail.com");
            displayStudents(students);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        students.forEach(System.out::println);
    }
}
