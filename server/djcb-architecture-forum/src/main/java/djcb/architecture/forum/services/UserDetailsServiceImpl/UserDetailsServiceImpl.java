package djcb.architecture.forum.services.UserDetailsServiceImpl;


import com.fasterxml.jackson.databind.ObjectMapper;
import djcb.architecture.forum.beans.entities.User;
import djcb.architecture.forum.beans.security.CustomUserDetails;
import djcb.architecture.forum.beans.view.UserRegisterModel;
import djcb.architecture.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public User registerUser(UserRegisterModel userRegisterModel){
        User user = objectMapper.convertValue(userRegisterModel, User.class);
        user.setCreationDate(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }

}
