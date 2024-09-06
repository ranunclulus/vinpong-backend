package com.project.vinpong.controller;

import com.project.vinpong.apiPayload.ApiResponse;
import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.converter.UserConverter;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.User;
import com.project.vinpong.repository.StyleRepository;
import com.project.vinpong.service.UserService.UserCommandService;
import com.project.vinpong.web.dto.UserRequestDTO;
import com.project.vinpong.web.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Transactional(readOnly = true)
public class UserController {
    private final UserCommandService userCommandService;
    private final StyleRepository styleRepository;

    @PostMapping("/signup/general")
    @Transactional
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request) {
        User user = userCommandService.joinUser(request);
        return null;
    }
}
