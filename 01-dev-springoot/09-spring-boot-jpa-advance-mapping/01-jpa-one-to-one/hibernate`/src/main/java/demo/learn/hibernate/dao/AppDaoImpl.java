package demo.learn.hibernate.dao;

import demo.learn.hibernate.entity.instructor;
import demo.learn.hibernate.entity.instructor_details;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDaoImpl implements AppDao{


    //define field for entiry manager  and inject into the constructor
    private EntityManager entityManager;

    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager=entityManager;

    }



    @Override
    @Transactional//from spring framework
    // find  what happend when we dont add this annotation on a method in gpt
    public void save(instructor theInstructor) {

        //this will also save the details object because of cascadeType.ALL
        entityManager.persist(theInstructor);

    }

    @Override
    public instructor findById(int theId) {

        /*
        this will also retrive the instructor details object beacuase of default behaviour of @OneToOne
        fetch type is eager .. more on fetch types later
         */
        return entityManager.find(instructor.class,theId);
    }

    @Override
    //since we are modyfying the database
    @Transactional
    public void deleteByUId(int theId) {

        //find the instructor first by id
        instructor inst = entityManager.find(instructor.class,theId);


        /*
        this will also delete the instructor details objects beacuse of cascadeType.All
         */
        //then delete that instructor
        entityManager.remove(inst);
    }

    @Override
    public instructor_details findInstructorDetailById(int theId) {
        return entityManager.find(instructor_details.class,theId);
    }


}
