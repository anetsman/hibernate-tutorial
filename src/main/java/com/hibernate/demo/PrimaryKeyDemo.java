package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {
            //create 3 student objects
            System.out.println("Creating 3 students...");
            Student student1 = new Student("John", "Doe", "jd@gmail.com");
            Student student2 = new Student("Alex", "Torph", "at@gmail.com");
            Student student3 = new Student("Jane", "Doe", "jd@gmail.com");

            //begin the transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the students...");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
