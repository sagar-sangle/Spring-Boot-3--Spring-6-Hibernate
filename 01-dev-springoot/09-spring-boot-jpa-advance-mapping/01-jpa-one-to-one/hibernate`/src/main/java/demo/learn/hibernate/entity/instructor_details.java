package demo.learn.hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name="instructor_details")
public class instructor_details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="youtube_channel")
    private String youtube;
    @Column(name="hobby")
    private String hobby;



    public instructor_details() {
    }

    public instructor_details(String youtube, String hobby) {

        this.youtube = youtube;
        this.hobby = hobby;
    }
    //add mapping here to instrcutor class

    //following refers to instructorDetails property in instructor class
    //were telling hibenrate that this instructor filed is actually mapped by the instructor detail property in the instructor class
    @OneToOne(mappedBy="instructor_details",cascade = CascadeType.ALL)

    /*
    mapped by-tells the hibernate to look at the instructorDetail property in the instructor class
    so hibernate can actually use the information from the instructor class's join column
    to help find the associated instructor


    cascade -> cascades all the operation to the associated instructor
    
     */
    private instructor inst;

    //getter and setters
    public void setInstructor(instructor inst) {
        this.inst = inst;
    }

    public  instructor getInstructor()
    {
        return this.inst;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "instructor_details{" +
                "id=" + id +
                ", youtube='" + youtube + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
