package demo.learn.hibernate.dao;


import demo.learn.hibernate.entity.instructor;
import demo.learn.hibernate.entity.instructor_details;

public interface AppDao {

    void save (instructor theInstructor);

    public instructor findById(int theId);

    public void deleteByUId(int theId);

    public instructor_details findInstructorDetailById(int theId);

}
