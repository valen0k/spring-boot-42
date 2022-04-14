package edu.school21.springboot42.services;

import edu.school21.springboot42.models.Confirmation;
import edu.school21.springboot42.models.User;
import edu.school21.springboot42.models.UserDetailsImpl;
import edu.school21.springboot42.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsersService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Optional<User> convertFindByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            return (Optional.ofNullable(user));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void signUp(User user) {
        userRepository.saveAndFlush(
                new User(
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhoneNumber().replaceAll("[+)( ]",""),
                        passwordEncoder.encode(user.getPassword())
                ));
    }

    public String getPicName(String email) {
        Optional<User> optionalUser = convertFindByEmail(email);
        return optionalUser.get().getPicName();
    }

    public void setPicName(String email, String picName) {
        userRepository.setUserPic(email, picName);
    }

    public boolean findEmail(String email) {
        return userRepository.findEmail(email);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetailsImpl) authentication.getPrincipal()).getUser();
    }

    public void setUserConfirmation(String email, Confirmation confirmation) {
        userRepository.setUserConfirmation(email, confirmation);
    }

}
