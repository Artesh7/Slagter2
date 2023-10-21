package com.example.slagter2.Controller;

import com.example.slagter2.Model.Animal;
import com.example.slagter2.Repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.getAllAnimals();
    }

    @PostMapping
    public void registerAnimal(@RequestBody Animal animal) {
        animalRepository.registerAnimal(animal);
    }

    @GetMapping("/{registrationNumber}")
    public Animal getAnimal(@PathVariable Integer registrationNumber) {
        return animalRepository.getAnimal(registrationNumber);
    }

    @GetMapping("/date/{date}")
    public List<Animal> getAnimalsByDate(@PathVariable String date) throws ParseException {
        return animalRepository.getAnimalsByDate(date);
    }

    @GetMapping("/origin/{origin}")
    public List<Animal> getAnimalsByOrigin(@PathVariable String origin) {
        return animalRepository.getAnimalsByOrigin(origin);
    }
}
