package semestrWork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.User;
import semestrWork.model.Worker;

import semestrWork.repository.UserRepository;
import semestrWork.repository.WorkerRepository;


@Service
public class UserDetailsServiseImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repository.findUserByUsername(username);
        if (user != null) {
            return new UserDetailImpl(user);
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
    }
}
