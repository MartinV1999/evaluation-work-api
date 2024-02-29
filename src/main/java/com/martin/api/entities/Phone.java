package com.martin.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El campo numero telefonico no puede estar vacio")
    @Positive(message = "El campo numero telefonico debe ser un numero positivo")
    @Min(value = 0, message = "El campo numero telefonico debe ser mayor a 0")
    private Integer number;
    @NotNull(message = "El campo codigo de ciudad no puede estar vacio")
    @Positive(message = "El campo codigo de ciudad debe ser un numero positivo")
    @Min(value = 0,  message = "El campo codigo de ciudad debe ser mayor a 0")
    private Integer cityCode;
    @NotNull(message = "El campo codigo de pais no puede estar vacio")
    @Positive(message = "El campo codigo de pais debe ser un numero positivo")
    @Min(value = 0, message = "El campo codigo de pais debe ser mayor a 0")
    private Integer countryCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
