package com.server.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.server.app.config.JsonWebToken;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserUpdateDto;
import com.server.app.entities.User;
import com.server.app.services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JsonWebToken jwt;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "username no puede estar vacío"));
        }

        if (password == null || password.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "password no puede estar vacío"));
        }

        User user = userService.login(username, password);

        String token = jwt.createToken(user);

        System.out.println("TOKEN GENERADO: " + token);


        return ResponseEntity.ok(Map.of(
                "token", token,
                "data", user
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserCreateDto dto) {

        if (dto.getUsername() == null || dto.getUsername().length() < 3) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "username mínimo 3 caracteres"));
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 8) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "password mínimo 8 caracteres"));
        }

        User user = userService.signUp(dto);

        String token = jwt.createToken(user);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "data", user
        ));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/profile")
    public ResponseEntity<?> updateProfile(
            Authentication authentication,
            @RequestBody UserUpdateDto dto
    ) {

        User current = (User) authentication.getPrincipal();

        User updated = userService.updateUser(current.getId(), dto);

        String token = jwt.createToken(updated);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "data", updated
        ));
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(
            Authentication authentication,
            @RequestBody Map<String, String> body
    ) {

        User current = (User) authentication.getPrincipal();

        String oldPassword = body.get("oldpassword");
        String newPassword = body.get("newpassword");
        String confirmPassword = body.get("confirmpassword");

        if (oldPassword == null || newPassword == null || confirmPassword == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "Todos los campos son obligatorios"));
        }

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "Las contraseñas no coinciden"));
        }

        if (newPassword.length() < 8) {
            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "La contraseña debe tener mínimo 8 caracteres"));
        }

        userService.updatePassword(current.getId(), oldPassword, newPassword);

        return ResponseEntity.ok(current);
    }
}
