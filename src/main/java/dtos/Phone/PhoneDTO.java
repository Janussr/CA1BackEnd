package dtos.Phone;

import entities.Phone;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhoneDTO {
    private Long id;
    private String number;
    private String description;

    public static List<PhoneDTO> getFromList(List<Phone> phones) {
        return phones.stream()
                .map(phone -> new PhoneDTO(phone))
                .collect(Collectors.toList());
    }

    public PhoneDTO() {
    }

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public PhoneDTO(String number, String description) {
        this.id = -1L;
        this.number = number;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDTO phoneDTO = (PhoneDTO) o;
        return Objects.equals(id, phoneDTO.id) && Objects.equals(number, phoneDTO.number) && Objects.equals(description, phoneDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, description);
    }
}
