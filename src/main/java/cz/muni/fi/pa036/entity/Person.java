package cz.muni.fi.pa036.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author GGIB
 */

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long ssn;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public Person() {}

    public Person(Long ssn, String firstName, String lastName) {
      this.ssn = ssn;
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public Long getSsn() {
        return ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" + "ssn=" + ssn + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}
