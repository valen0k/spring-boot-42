package edu.school21.springboot42.services;

import edu.school21.springboot42.models.Session;
import edu.school21.springboot42.repositories.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sessionsService")
public class SessionsService {

    @Autowired
    SessionsRepository sessionsRepository;

    public void saveSession(Session session) {
        sessionsRepository.saveAndFlush(session);
    }

    public List<Session> findAllSessions() {
        return sessionsRepository.findAllSessions();
    }

}
