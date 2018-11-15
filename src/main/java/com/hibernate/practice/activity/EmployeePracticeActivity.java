package com.hibernate.practice.activity;

import com.hibernate.practice.activity.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class EmployeePracticeActivity {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session;

        try {
            String theDateOfBirthStr = "31/12/1998";

            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            //creating Employess
            Employee employee1 = new Employee("John", "Doe", theDateOfBirth, "Marvel");
            Employee employee2 = new Employee("Jane", "Doe", theDateOfBirth, "DC");
            Employee employee3 = new Employee("John", "Malkovich", theDateOfBirth, "WB");

            //saving the employee
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.save(employee1);
            session.save(employee2);
            session.save(employee3);

            session.getTransaction().commit();

            //retrieving employees
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Employee readEmployee1 = session.get(Employee.class, employee1.getId());
            Employee readEmployee2 = session.get(Employee.class, employee2.getId());
            Employee readEmployee3 = session.get(Employee.class, employee3.getId());

            session.getTransaction().commit();

            System.out.println("\nretrieving employees...");
            System.out.println("\n" + readEmployee1 + "\n" + readEmployee2 + "\n" + readEmployee3);

            //searching by company
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            List<Employee> employees = session.createQuery("from Employee e where e.company='Marvel'").getResultList();
            System.out.println("\nsearching for employees...");
            displayingEmployees(employees);

            session.getTransaction().commit();

            //delete object by primary key
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Employee removedEmployee1 = session.get(Employee.class, employee3.getId());
            session.delete(removedEmployee1);
            employees = session.createQuery("from Employee").getResultList();
            System.out.println("\ndeleting employees...");
            displayingEmployees(employees);

            session.createQuery("delete from Employee where id=2").executeUpdate();

            employees = session.createQuery("from Employee").getResultList();
            System.out.println("\ndeleting employees alternatively...");
            displayingEmployees(employees);

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    private static void displayingEmployees(List<Employee> employees) {
        employees.forEach(System.out::println);
    }
}
