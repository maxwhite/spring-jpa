package com.robotdreams.country.controller;

import com.robotdreams.country.model.City;
import com.robotdreams.country.model.Country;
import com.robotdreams.country.repository.CityRepository;
import com.robotdreams.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @GetMapping("/api/country/initialize")
    public void init() {
        if(countryRepository.findAll().size() == 0) {
            Country ukraine = Country.builder().name("Ukraine").isoCode("UKR").build();
            Country italy = Country.builder().name("Italy").isoCode("ITA").build();
            Country france = Country.builder().name("France").isoCode("FRN").build();

            City dnipro = City.builder().name("Dnipro").country(ukraine).build();
            City kyiv = City.builder().name("Kyiv").country(ukraine).build();
            City paris = City.builder().name("Paris").country(france).build();
            City milan = City.builder().name("Milan").country(italy).build();
            City marsele = City.builder().name("Marsele").country(france).build();

            countryRepository.save(ukraine);
            countryRepository.save(italy);
            countryRepository.save(france);

            cityRepository.save(dnipro);
            cityRepository.save(kyiv);
            cityRepository.save(paris);
            cityRepository.save(milan);
            cityRepository.save(marsele);
        }
    }

    @GetMapping("/api/city")
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @GetMapping("api/country")
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }
}
