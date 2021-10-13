package dtos;

import dtos.CityInfo.CityInfoDTO;
import entities.Address;

import java.util.Objects;

public class AddressDTO {
    private String street;
    private String additionalInfo;
    private CityInfoDTO cityInfo;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfo = new CityInfoDTO(address.getCityInfo());
    }

    public AddressDTO(String street, String additionalInfo, CityInfoDTO cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfoDTO getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDTO cityInfo) {
        this.cityInfo = cityInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(street, that.street) && Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(cityInfo, that.cityInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, additionalInfo, cityInfo);
    }
}
