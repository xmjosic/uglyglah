package hr.xmjosic.uglyglah.controller;

import hr.xmjosic.uglyglah.dto.AuthenticationResponse;
import hr.xmjosic.uglyglah.dto.LoginRequest;
import hr.xmjosic.uglyglah.dto.RegisterRequest;
import hr.xmjosic.uglyglah.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification")
    public ResponseEntity<String> verifyAccount(@RequestParam(name = "token") String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
