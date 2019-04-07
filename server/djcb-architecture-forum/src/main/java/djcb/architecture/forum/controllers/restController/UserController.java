package djcb.architecture.forum.controllers.restController;

import djcb.architecture.forum.beans.security.JwtTokenProvider;
import djcb.architecture.forum.beans.view.UserLoginModel;
import djcb.architecture.forum.beans.view.UserRegisterModel;
import djcb.architecture.forum.services.UserDetailsServiceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserRegisterModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDetailsService.registerUser(user);
    }

    @PostMapping("/sign-in")
    public ResponseEntity signin(@RequestBody UserLoginModel data) {
        try {
            String username = data.getUsername();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(userDetails);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @RequestMapping("/current-user")
    public Principal user(Principal principal) {
        return principal;
    }
}

