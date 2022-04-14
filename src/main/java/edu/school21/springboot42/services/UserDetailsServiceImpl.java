package edu.school21.springboot42.services;

import edu.school21.springboot42.models.User;
import edu.school21.springboot42.models.UserDetailsImpl;
import edu.school21.springboot42.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("Could not find user");
        return new UserDetailsImpl(user);
    }

}
