package it.lentini.jwtauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.lentini.moviefinder.config.JwtConfig;
import it.lentini.moviefinder.config.ObjectMapperConfig;
import it.lentini.moviefinder.controller.AuthController;
import it.lentini.moviefinder.domain.User;
import it.lentini.moviefinder.dto.request.LoginRequest;
import it.lentini.moviefinder.dto.request.RegisterRequest;
import it.lentini.moviefinder.exception.RestAccessDeniedHandler;
import it.lentini.moviefinder.exception.RestAuthenticationEntryPoint;
import it.lentini.moviefinder.repository.UserRepository;
import it.lentini.moviefinder.security.JwtAuthenticationFilter;
import it.lentini.moviefinder.security.JwtService;
import it.lentini.moviefinder.security.SecurityConfig;
import it.lentini.moviefinder.service.UserService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(
        {
                ObjectMapperConfig.class,
                SecurityConfig.class,
                JwtAuthenticationFilter.class})
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserRepository repository;

    @MockitoBean
    private PasswordEncoder encoder;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtConfig jwtConfig;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    RestAuthenticationEntryPoint authenticationEntryPoint;

    @MockitoBean
    RestAccessDeniedHandler accessDeniedHandler;


    @Test
    void register_shouldCreateUser() throws Exception {

        RegisterRequest request =
                new RegisterRequest("test@test.com", "password");


        when(repository.existsByEmail(request.email()))
                .thenReturn(false);

        when(encoder.encode(request.password()))
                .thenReturn("encodedPassword");


        mockMvc.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isNoContent());


        verify(repository).save(any(User.class));

        verify(encoder)
                .encode("password");
    }


    @Test
    void register_shouldReturnConflictWhenEmailExists() throws Exception {

        RegisterRequest request =
                new RegisterRequest("test@test.com", "password");


        when(repository.existsByEmail(request.email()))
                .thenReturn(true);


        mockMvc.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isConflict());


        verify(repository, never())
                .save(any());
    }


    @Test
    void login_shouldReturnAccessTokenAndRefreshCookie()
            throws Exception {


        LoginRequest request =
                new LoginRequest("test@test.com", "password");


        User user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");


        when(repository.findByEmail(request.email()))
                .thenReturn(Optional.of(user));


        when(jwtService.generateAccessToken(user))
                .thenReturn("access-token");


        when(jwtService.generateRefreshToken(user))
                .thenReturn("refresh-token");


        when(jwtConfig.getRefreshTokenExpiration())
                .thenReturn(3600);


        when(authenticationManager.authenticate(any()))
                .thenReturn(
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null
                        )
                );


        mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token")
                        .value("access-token"))
                .andExpect(cookie()
                        .value("refreshToken", "refresh-token"));


        verify(authenticationManager)
                .authenticate(any());
    }


    @Test
    void refresh_shouldReturnNewAccessToken()
            throws Exception {


        User user = new User();
        user.setId(1L);


        when(jwtService.validateToken("refresh-token"))
                .thenReturn(true);


        when(jwtService.extractSubject("refresh-token"))
                .thenReturn("1");


        when(repository.findById(1L))
                .thenReturn(Optional.of(user));


        when(jwtService.generateAccessToken(user))
                .thenReturn("new-access-token");


        mockMvc.perform(
                        post("/auth/refresh")
                                .cookie(
                                        new Cookie(
                                                "refreshToken",
                                                "refresh-token"
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token")
                        .value("new-access-token"));
    }


    @Test
    void refresh_shouldReturnUnauthorizedWhenTokenInvalid()
            throws Exception {


        when(jwtService.validateToken("bad-token"))
                .thenReturn(false);


        mockMvc.perform(
                        post("/auth/refresh")
                                .cookie(
                                        new Cookie(
                                                "refreshToken",
                                                "bad-token"
                                        )
                                )
                )
                .andExpect(status().isUnauthorized());


        verify(repository, never())
                .findById(any());
    }
}