package com.app.skillbox_laba4.controller;

import com.app.skillbox_laba4.domain.User;
import com.app.skillbox_laba4.dto.UserDto;
import com.app.skillbox_laba4.mapper.UserMapper;
import com.app.skillbox_laba4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable UUID id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        user = userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> find(@PathVariable UUID id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findById(id).get();
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(@RequestParam(name = "page", required = false) Integer page,
                                                 @RequestParam(name = "size", required = false) Integer size) {
        Pageable pageable;
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        } else if (page == null && size != null) {
            pageable = PageRequest.ofSize(size);
        } else {
            pageable = Pageable.unpaged();
        }
        List<User> users = userRepository.findAll(pageable).toList();
        return ResponseEntity.ok(users.stream()
                .map(userMapper::toDto).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
