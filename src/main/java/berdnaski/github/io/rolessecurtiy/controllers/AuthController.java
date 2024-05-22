package berdnaski.github.io.rolessecurtiy.controllers;

import berdnaski.github.io.rolessecurtiy.domain.user.User;
import berdnaski.github.io.rolessecurtiy.dto.LoginRequestDTO;
import berdnaski.github.io.rolessecurtiy.dto.RegisterRequestDTO;
import berdnaski.github.io.rolessecurtiy.dto.ResponseDTO;
import berdnaski.github.io.rolessecurtiy.infra.security.TokenService;
import berdnaski.github.io.rolessecurtiy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = this.repository.findByEmail(loginRequestDTO.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO registerRequestDTO)
}
