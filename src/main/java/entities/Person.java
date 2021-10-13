package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    private String email;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    private List<Hobby> hobbies = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Phone> phones = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public Person() {
    }

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String email, String firstName, String lastName, List<Hobby> hobbies, List<Phone> phones, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
        this.phones = phones;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addHobby(Hobby hobby) {
        if (hobby != null) {
            this.hobbies.add(hobby);
            hobby.getPersons().add(this);
        }
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        if (phone != null) {
            phone.setPerson(this);
        }
    }
}
