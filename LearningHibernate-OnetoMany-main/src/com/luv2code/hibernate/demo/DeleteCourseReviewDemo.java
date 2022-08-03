package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseReviewDemo {
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

            int theId=10;
            //get the course
            Course tempCourse= session.get(Course.class, theId);
           // print the course
            System.out.println("Deleting Course: " +tempCourse);

            // print the course reviews
            System.out.println(tempCourse.getReviews());

 // delete the course
            session.delete(tempCourse);
            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done! Successfully deleted the Course with reviews.");
        } finally {
            session.close();
            factory.close();
            System.out.println("Closing the factory.");
        }


    }
}
