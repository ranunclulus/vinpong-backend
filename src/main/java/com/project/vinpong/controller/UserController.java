package com.project.vinpong.controller;

import com.project.vinpong.apiPayload.ApiResponse;
import com.project.vinpong.converter.UserConverter;
import com.project.vinpong.domain.User;
import com.project.vinpong.service.UserService.UserCommandService;
import com.project.vinpong.web.dto.UserRequestDTO;
import com.project.vinpong.web.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserCommandService userCommandService;


    @PostMapping("/signup/general")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request) {
        User user = userCommandService.joinUser(request);
        return null;
    }
}
