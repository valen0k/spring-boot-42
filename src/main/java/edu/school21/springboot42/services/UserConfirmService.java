package edu.school21.springboot42.services;

import edu.school21.springboot42.models.UserConfirm;
import edu.school21.springboot42.repositories.UserConfirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserConfirmService {

    @Autowired
    UserConfirmRepository userConfirmRepository;

    public void saveUserConfirm(UserConfirm userConfirm) {
        userConfirmRepository.saveAndFlush(userConfirm);
    }

    public void deleteUserConfirm(UserConfirm userConfirm) {
        userConfirmRepository.delete(userConfirm);
    }

    public Optional<UserConfirm> findByUserUuid(String uuid) {
        return userConfirmRepository.findByUserUuid(uuid);
    }

}
