package com.maveric.authentication.auth;

import com.maveric.authentication.entity.User;
import com.maveric.authentication.repository.UserRepository;
import com.maveric.authentication.request.AuthenticationRequest;
import com.maveric.authentication.request.AuthenticationResponse;
import com.maveric.authentication.request.RegisterRequest;
import com.maveric.authentication.service.AuthenticationService;
import com.maveric.authentication.service.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
//private  UserAlreadyExistsException existsException;
  private final AuthenticationService service;
private final UserRepository userRepository;

  private RestTemplate restTemplate;
private final String ERROR="USER ALREADY EXIST";
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email Id is already taken!"));
    }
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
@GetMapping("/validate")
public String validateToken(@RequestParam("token")String token){
    service.validateToken(token);
    return "Token is valid";
}

@GetMapping("/all")
public String getMessage(){
    return "Communication is working";
}
@GetMapping("/getUsers")
  public ResponseEntity<List<User>> getData() {
    return ResponseEntity.ok().body(userRepository.findAll());
  }
}
