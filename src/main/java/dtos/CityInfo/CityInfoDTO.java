package dtos.CityInfo;

import entities.Address;
import entities.CityInfo;

import java.util.List;
import java.util.Objects;

public class CityInfoDTO {
    private Long id;
    private int zipcode;
    private String city;

    public CityInfoDTO() {
    }

    public CityInfoDTO(CityInfo cityInfo) {
        this.id = cityInfo.getId();
        this.zipcode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();
    }

    public CityInfoDTO(int zipcode, String city) {
        this.id = -1L;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfoDTO that = (CityInfoDTO) o;
        return zipcode == that.zipcode && Objects.equals(id, that.id) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zipcode, city);
    }
}
