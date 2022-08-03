package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {

            //start transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Pacman- zero to hero");

            // add some reviews
            tempCourse.addReview(new Review("Nice game, but ages terrible!"));
            tempCourse.addReview(new Review("Did play it once, never felt teh buzz."));
            tempCourse.addReview(new Review("Awesome game. We play it whenever i meet my friends for a hangout"));

            //save the course and leverage the cascade all :)
            session.save(tempCourse);

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done! Successfully added new Course with reviews.");
        } finally {
            session.close();
            factory.close();
            System.out.println("Closing the factory.");
        }


    }
}
