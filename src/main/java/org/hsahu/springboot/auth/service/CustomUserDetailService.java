package org.hsahu.springboot.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * user details service class that will fetch user from database and construct the user object
 */
@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Value("#{${security.inbuilt.users}}")
    private Map<String,String> users;

    @PostConstruct
    private void postConstruct() {
        log.info(users.toString());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: this is analogous to fetching users from database
        if (!users.containsKey(username)) {
            String errorMessage = String.format("User with username %s could not be found!!", username);
            log.error(errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        }
        // create user
        return User.builder()
                .username(username)
                .password(users.get(username))
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .disabled(false)
                .credentialsExpired(false)
                .accountExpired(false)
                .build();
    }
}
