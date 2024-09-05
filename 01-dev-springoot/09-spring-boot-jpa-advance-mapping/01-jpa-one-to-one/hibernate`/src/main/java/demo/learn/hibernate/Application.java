package demo.learn.hibernate;

import demo.learn.hibernate.dao.AppDao;
import demo.learn.hibernate.entity.instructor;
import demo.learn.hibernate.entity.instructor_details;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Scanner;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean//inject app dao
	public CommandLineRunner commandLineRunner(AppDao appDao)
	{

		return runner->

				//findInstructorDetail(appDao);
				createInstructor(appDao);

				//findInstructorById(appDao);
				//deleteInstructorById(appDao);
	}

	private void createInstructor(AppDao appDao)
	{
		Scanner sc = new Scanner(System.in);


		//input the instructor Details
		System.out.println("Enter firstname of a instructor : ");
		String firstName=sc.next();
		System.out.println("Enter lastname of a instructor : ");
		String lastName=sc.next();
		System.out.println("Enter Email of a instructor : ");
		String email=sc.next();


		//create the instructor
		instructor instruct= new instructor(firstName,lastName,email);
		//save it

		//create the instructor detail
		instructor_details instructorDetails = new instructor_details("CodeWith"+firstName,"sports");

		instruct.setInstructorDetails(instructorDetails);
		System.out.println("saving instructor"+instruct);

		//save the instruct
		// NOTE: this will save the detail
		// objet aswell bacuse of cascadeType.ALL


		appDao.save(instruct);
	}

	public void findInstructorById(AppDao appDao)
	{
		int theId=1;
		System.out.println("finding instructor by id "+theId);
		instructor tempInstructor = appDao.findById(theId);
		System.out.println("temp instructor "+tempInstructor);
		System.out.println("the aassociated details : "+tempInstructor.getInstructorDetails());
	}

	public void deleteInstructorById(AppDao appDao)
	{
		int theId=1;
		System.out.println("deleting the instructor Id "+theId);
		appDao.deleteByUId(theId);

	}

	public void findInstructorDetail(AppDao appDao)
	{
		int theId=1;
		//get the instructor detail
		  instructor_details tempInstructor =appDao.findInstructorDetailById(theId);

		  //print the instructor detail
		System.out.println("temp instructor Detail : " + tempInstructor);

		//print the associated instructor
		System.out.println("associated instructpr "+tempInstructor.getInstructor());

		System.out.println("done");
	}

}




