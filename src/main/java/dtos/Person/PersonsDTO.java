package dtos.Person;

import dtos.Person.PersonDTO;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonsDTO {
    List<PersonDTO> all = new ArrayList();

    public PersonsDTO(List<Person> personEntities) {
        personEntities.forEach((p) -> {
            all.add(new PersonDTO(p));
        });
    }

    public int getSize() {
        int counter = 0;
        for (PersonDTO p : all) {
            counter++;
        }
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonsDTO that = (PersonsDTO) o;
        return Objects.equals(all, that.all);
    }

    @Override
    public int hashCode() {
        return Objects.hash(all);
    }
}
