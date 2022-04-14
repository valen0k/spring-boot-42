package edu.school21.springboot42.services;

import edu.school21.springboot42.models.Film;
import edu.school21.springboot42.repositories.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("filmsService")
public class FilmsService {

    @Autowired
    FilmsRepository filmsRepository;

    public List<Film> findAllFilms() {
        return filmsRepository.findAll();
    }

    public void saveFilm(Film film) {
        filmsRepository.saveAndFlush(film);
    }

    public Film findFilmByName(String title) {
        return filmsRepository.findByTitle(title);
    }

    public Film findFilmById(int id) {
        return filmsRepository.getById(id);
    }
}
