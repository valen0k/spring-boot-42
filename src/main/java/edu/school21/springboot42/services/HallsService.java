package edu.school21.springboot42.services;

import edu.school21.springboot42.models.Hall;
import edu.school21.springboot42.repositories.HallsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hallsService")
public class HallsService {

    @Autowired
    HallsRepository hallsRepository;

    public List<Hall> findAllHalls() {
        return hallsRepository.findAll();
    }

    public void saveHall(Hall hall) {
        hallsRepository.saveAndFlush(hall);
    }

}
