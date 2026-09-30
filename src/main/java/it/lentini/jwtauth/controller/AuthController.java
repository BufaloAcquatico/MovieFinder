package it.lentini.jwtauth.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.lentini.jwtauth.config.JwtConfig;
import it.lentini.jwtauth.domain.Role;
import it.lentini.jwtauth.domain.User;
import it.lentini.jwtauth.dto.request.LoginRequest;
import it.lentini.jwtauth.dto.request.RegisterRequest;
import it.lentini.jwtauth.dto.response.TokenResponse;
import it.lentini.jwtauth.exception.EmailAlreadyExistsException;
import it.lentini.jwtauth.exception.ResourceNotFoundException;
import it.lentini.jwtauth.repository.UserRepository;
import it.lentini.jwtauth.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name="Authentication")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;


    @ApiResponses( value= {
            @ApiResponse(responseCode = "204", description = "User registered successfully"),
            @ApiResponse(responseCode = "409", description = "Registration failed due to email duplicate")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterRequest request
    ) {
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(
                encoder.encode(request.password())
        );
        user.setEnabled(true);
        user.setRole(Role.USER);
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email " + request.email() + " is already registered");
        }

        repository.save(user);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses( value= {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Wrong credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        var user = repository.findByEmail(request.email()).orElseThrow();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setPath("/auth/refresh");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        //cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new TokenResponse(accessToken));
    }

    @ApiResponses( value= {
            @ApiResponse(responseCode = "200", description = "Refresh token returned successfully"),
            @ApiResponse(responseCode = "401", description = "Failed to refresh due to credentials invalid or token expired")
    })
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(
            @CookieValue(value="refreshToken") String refreshToken,
            HttpServletResponse response
    ){
        if (!jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long id = Long.valueOf( jwtService.extractSubject(refreshToken) );
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String accessToken = jwtService.generateAccessToken(user);
        return ResponseEntity.ok(new TokenResponse(accessToken));
    }
}