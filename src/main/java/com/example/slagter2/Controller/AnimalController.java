package com.example.slagter2.Controller;

import com.example.slagter2.Model.Animal;
import org.springframework.web.bind.annotation.*;

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

    private final List<Animal> animals = new ArrayList<>();
    private int counter = 1;

    @PostMapping
    public Animal registerAnimal(@RequestBody Animal animal) {
        animal.setRegistrationNumber(counter++);
        animal.setDate(new Date());
        animals.add(animal);
        return animal;
    }

    @GetMapping("/{registrationNumber}")
    public Animal getAnimal(@PathVariable Integer registrationNumber) {
        return animals.stream()
                .filter(a -> a.getRegistrationNumber().equals(registrationNumber))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/date/{date}")
    public List<Animal> getAnimalsByDate(@PathVariable String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return animals.stream()
                .filter(a -> sdf.format(a.getDate()).equals(date))
                .collect(Collectors.toList());
    }

    @GetMapping("/origin/{origin}")
    public List<Animal> getAnimalsByOrigin(@PathVariable String origin) {
        return animals.stream()
                .filter(a -> a.getOrigin().equals(origin))
                .collect(Collectors.toList());
    }
}
