package com.project.vinpong.service.UserService;

import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.apiPayload.exception.handler.StyleHandler;
import com.project.vinpong.converter.UserConverter;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.User;
import com.project.vinpong.domain.mapping.UserStyle;
import com.project.vinpong.repository.StyleRepository;
import com.project.vinpong.repository.UserRepository;
import com.project.vinpong.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService{
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDTO request) {
        User user = UserConverter.toUser(request);
        List<Style> styleList = request.getPreferStyles().stream()
                .map(styleId -> {return styleRepository.findById(styleId).orElseThrow(() -> new StyleHandler(ErrorStatus.STYLE_NOT_FOUND));
                }).collect(Collectors.toList());

        //List<UserStyle> userStyleList = UserStyleConverter
        return null;
    }
}
