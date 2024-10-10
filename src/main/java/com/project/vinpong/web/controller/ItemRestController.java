package com.project.vinpong.web.controller;

import com.project.vinpong.apiPayload.ApiResponse;
import com.project.vinpong.converter.ItemConverter;
import com.project.vinpong.converter.MemberConverter;
import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Member;
import com.project.vinpong.service.ItemService.ItemCommandService;
import com.project.vinpong.web.dto.ItemRequestDTO;
import com.project.vinpong.web.dto.ItemResponseDTO;
import com.project.vinpong.web.dto.MemberRequestDTO;
import com.project.vinpong.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
public class ItemRestController {
    private final ItemCommandService itemCommandService;

    @PostMapping()
    public ApiResponse<ItemResponseDTO.JoinResultDTO> join(@RequestBody @Valid ItemRequestDTO.JoinDTO request) {
        Item item = itemCommandService.joinItem(request);
        return ApiResponse.onSuccess(ItemConverter.toJoinResultDTO(item));
    }
}
