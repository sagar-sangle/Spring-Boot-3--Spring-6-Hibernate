package demo.learn.hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name="instrctor")
public class instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;


    // give relation between instructor and instructorDetails
    /*
    below instructor_detail_id defined in instructor table in database foreign key is configured to referance id field
     in instructor_details table
     */
    @OneToOne(cascade = CascadeType.ALL)//cascade will update the associated object (relation given to all)
    @JoinColumn(name="instructor_detail_id")//this is name given in a database in instrctor_detail table which is automatically created in our instructor table
    private instructor_details instructorDetails;


    public void setInstructorDetails(instructor_details instructorDetails) {
        this.instructorDetails = instructorDetails;
    }


    public instructor_details getInstructorDetails() {
        return instructorDetails;
    }


    public instructor() {
    }

    public instructor(String firstName, String lastName, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetails=" + instructorDetails +
                '}';
    }
}
