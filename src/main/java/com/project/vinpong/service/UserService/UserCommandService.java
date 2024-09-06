package com.project.vinpong.service.UserService;

import com.project.vinpong.domain.User;
import com.project.vinpong.web.dto.UserRequestDTO;

public interface UserCommandService {

    public User joinUser(UserRequestDTO.JoinDTO request);
}
