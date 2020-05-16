package org.hsahu.springboot.auth.controller;

import org.hsahu.springboot.auth.jwt.util.JwtTokenUtil;
import org.hsahu.springboot.auth.service.CustomUserDetailService;
import org.hsahu.springboot.dto.auth.AuthenticationRequest;
import org.hsahu.springboot.dto.auth.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that exposes basic authentication endpoint
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/access_token")
    public AuthenticationResponse authenticate(@RequestBody  AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails  userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken);
    }
}
