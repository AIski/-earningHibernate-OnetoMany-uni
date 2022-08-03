package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {
            //create the objects

            //start transaction
            session.beginTransaction();
            int theId = 1;
            // get the instructor from db
            Instructor tempInstructor = session.get(Instructor.class, theId);
            // creat som courses
            Course tempCourse1 = new Course("Flying high- in the sky!");
            Course tempCourse2 = new Course("Rocking and Rolling- zero to hero!");
            // add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            //save the courses
session.save(tempCourse1);
session.save(tempCourse2);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
            System.out.println("Closing the factory.");
        }


    }
}
