package dtos.Hobby;

import entities.Hobby;

import java.util.List;
import java.util.Objects;

public class HobbiesDTO {

    private List<HobbyDTO> hobbies;

    public HobbiesDTO(List<Hobby> hobbies) {
        this.hobbies = HobbyDTO.getFromList(hobbies);
    }

    public List<HobbyDTO> getAll() {
        return hobbies;
    }

    public void setAll(List<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "HobbiesDTO{" +
                "hobbies=" + hobbies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbiesDTO that = (HobbiesDTO) o;
        return Objects.equals(hobbies, that.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hobbies);
    }
}
