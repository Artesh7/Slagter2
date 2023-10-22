package com.example.slagter2.Repository;

import com.example.slagter2.Model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public class AnimalRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnimalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void registerAnimal(Animal animal) {
        animal.setDate(new Date());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = outputDateFormat.format(animal.getDate());

        String sql = "INSERT INTO sdj.animal (date, weight, origin, name) VALUES (CAST(? AS DATE), ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                formattedDate,
                animal.getWeight(),
                animal.getOrigin(),
                animal.getName()
        );
    }

    public List<Animal> getAllAnimals() {
        String sql = "SELECT * FROM sdj.animal";
        return jdbcTemplate.query(sql, this::mapAnimalRow);
    }

    public Animal getAnimal(Integer registrationNumber) {
        String sql = "SELECT * FROM sdj.animal WHERE animalId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{registrationNumber}, this::mapAnimalRow);
    }

    public List<Animal> getAnimalsByDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse(date);
        String sql = "SELECT * FROM sdj.animal WHERE date = ?";
        return jdbcTemplate.query(sql, new Object[]{parsedDate}, this::mapAnimalRow);
    }

    public List<Animal> getAnimalsByOrigin(String origin) {
        String sql = "SELECT * FROM sdj.animal WHERE origin = ?";
        return jdbcTemplate.query(sql, new Object[]{origin}, this::mapAnimalRow);
    }

    private Animal mapAnimalRow(ResultSet rs, int rowNum) throws SQLException {
        Animal animal = new Animal();
        animal.setName(rs.getString("name"));
        animal.setAnimalID(rs.getInt("animalId"));
        animal.setDate(rs.getDate("date"));
        animal.setWeight(rs.getDouble("weight"));
        animal.setOrigin(rs.getString("origin"));
        return animal;
    }

    public boolean deleteAnimal(int registrationNumber) {
        String sql = "DELETE FROM sdj.animal WHERE animalId = ?";
        int affectedRows = jdbcTemplate.update(sql, registrationNumber);
        return affectedRows > 0;
    }
}
