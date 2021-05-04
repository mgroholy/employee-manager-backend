package com.codecool.employeemanager.controller;

import com.codecool.employeemanager.model.ClearanceLevel;
import com.codecool.employeemanager.model.UserDto;
import com.codecool.employeemanager.service.UserService;
import com.codecool.employeemanager.security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private final UserService userService;
    private JwtTokenServices jwtTokenServices;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity signIn(@RequestBody UserDto userDto, HttpServletResponse httpServletResponse){
        try{
            String email = userDto.getEmail();
            String password = userDto.getPassword();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            List<ClearanceLevel> levels = authentication.getAuthorities().stream().map(level -> ClearanceLevel.valueOf(level.getAuthority())).collect(Collectors.toList());
            String jwt = jwtTokenServices.createToken(email, levels);
            Cookie cookie = new Cookie("jwt", jwt);
//            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException authenticationException){
            throw new BadCredentialsException("Invalid email/password.");
        }
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDto userData) {
        userService.addUser(userData);
    }

    @GetMapping("/user")
    public String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

}
