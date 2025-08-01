package com.wcs.travel_blog.user.controller;

import com.wcs.travel_blog.user.dto.UpsertUserDTO;
import com.wcs.travel_blog.user.dto.UserDTO;
import com.wcs.travel_blog.user.dto.UserWithDiariesDTO;
import com.wcs.travel_blog.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserWithDiariesDTO> getUserById(@PathVariable Long userId){
        UserWithDiariesDTO user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/email")
    public ResponseEntity<UserWithDiariesDTO> getUserByEmail(@RequestParam  String email) {
        UserWithDiariesDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/username")
    public ResponseEntity<UserWithDiariesDTO> getUserByUsername(@RequestParam  String username) {
        UserWithDiariesDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser( @PathVariable Long userId, @Valid @RequestBody UpsertUserDTO upsertUserDTO){
       UserDTO updatedUser = userService.updateUser(userId, upsertUserDTO);
       return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("Utilisateur supprimé avec succès.");
    }


}
