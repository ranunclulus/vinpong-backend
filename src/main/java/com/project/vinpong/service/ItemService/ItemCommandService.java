package com.project.vinpong.service.ItemService;

import com.project.vinpong.domain.Item;
import com.project.vinpong.web.dto.ItemRequestDTO;

public interface ItemCommandService {
    public Item joinItem(ItemRequestDTO.JoinDTO request);
}
