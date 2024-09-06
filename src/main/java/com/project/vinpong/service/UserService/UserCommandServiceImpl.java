package com.project.vinpong.service.UserService;

import com.project.vinpong.domain.User;
import com.project.vinpong.repository.UserRepository;
import com.project.vinpong.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{
    private final UserRepository userRepository;

    @Override
    public User joinUser(UserRequestDTO.JoinDTO request) {
        return null;
    }
}
