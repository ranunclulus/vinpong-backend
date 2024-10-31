package com.project.vinpong.service.ItemService;

import com.project.vinpong.domain.Item;
import com.project.vinpong.web.dto.ItemRequestDTO;

import java.util.List;

public interface ItemCommandService {
    public Item joinItem(ItemRequestDTO.JoinDTO request, String sellerName);

    List<Item> searchByStyleAndCategory(ItemRequestDTO.searchDTO request);

    List<Item> getAllItemsByShop(Long shopId);

    Item searchById(Long itemId);
}
