package dtos.Phone;

import dtos.Person.PersonDTO;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhonesDTO {
    List<PhoneDTO> all = new ArrayList();

    public PhonesDTO(List<Phone> phoneEntities) {
        phoneEntities.forEach((p) -> {
            all.add(new PhoneDTO(p));
        });
    }

    public int getSize() {
        int counter = 0;
        for (PhoneDTO p : all) {
            counter++;
        }
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhonesDTO phonesDTO = (PhonesDTO) o;
        return Objects.equals(all, phonesDTO.all);
    }

    @Override
    public int hashCode() {
        return Objects.hash(all);

    }
}