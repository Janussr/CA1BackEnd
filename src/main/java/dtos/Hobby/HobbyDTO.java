package dtos.Hobby;

import entities.Hobby;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HobbyDTO {

    private Long id;
    private String name;
    private String wikiLink;
    private String category;
    private String type;

    public static List<HobbyDTO> getFromList(List<Hobby> hobbies) {
        return hobbies.stream()
                .map(hobby -> new HobbyDTO(hobby))
                .collect(Collectors.toList());
    }

    public HobbyDTO() {
    }

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.wikiLink = hobby.getWikiLink();
        this.category = hobby.getCategory();
        this.type = hobby.getType();
    }

    public HobbyDTO(String name, String wikiLink, String category, String type) {
        this.id = -1L;
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyDTO hobbyDTO = (HobbyDTO) o;
        return Objects.equals(id, hobbyDTO.id) && Objects.equals(name, hobbyDTO.name) && Objects.equals(wikiLink, hobbyDTO.wikiLink) && Objects.equals(category, hobbyDTO.category) && Objects.equals(type, hobbyDTO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, wikiLink, category, type);
    }
}
