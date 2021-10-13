package dtos.Person;

import dtos.AddressDTO;
import dtos.Hobby.HobbyDTO;
import dtos.Phone.PhoneDTO;
import entities.Person;

import java.util.List;
import java.util.Objects;



public class PersonDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<HobbyDTO> hobbies;
    private List<PhoneDTO> phones;
    private AddressDTO address;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.hobbies = HobbyDTO.getFromList(person.getHobbies());
        this.phones = PhoneDTO.getFromList(person.getPhones());
        this.address = new AddressDTO(person.getAddress());
    }

    public PersonDTO() {
    }

    public PersonDTO(String email, String firstName, String lastName, List<HobbyDTO> hobbies, List<PhoneDTO> phones, AddressDTO address) {
        this.id = -1L;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
        this.phones = phones;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(email, personDTO.email) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(hobbies, personDTO.hobbies) && Objects.equals(phones, personDTO.phones) && Objects.equals(address, personDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, hobbies, phones, address);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hobbies=" + hobbies +
                ", phones=" + phones +
                ", address=" + address +
                '}';
    }
}
