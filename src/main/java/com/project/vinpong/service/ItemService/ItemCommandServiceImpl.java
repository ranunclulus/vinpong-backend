package com.project.vinpong.service.ItemService;

import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.apiPayload.exception.handler.CategoryHandler;
import com.project.vinpong.apiPayload.exception.handler.StyleHandler;
import com.project.vinpong.converter.ItemCategoryConverter;
import com.project.vinpong.converter.ItemConverter;
import com.project.vinpong.converter.ItemStyleConverter;
import com.project.vinpong.domain.Category;
import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.mapping.ItemCategory;
import com.project.vinpong.domain.mapping.ItemStyle;
import com.project.vinpong.repository.CategoryRepository;
import com.project.vinpong.repository.StyleRepository;
import com.project.vinpong.web.dto.ItemRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemCommandServiceImpl implements ItemCommandService{
    private final StyleRepository styleRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Item joinItem(ItemRequestDTO.JoinDTO request) {
        Item newItem = ItemConverter.toItem(request);

        List<Style> styleList = request.getItemStyleList().stream()
                .map(styleId -> {return styleRepository.findById(styleId).orElseThrow(() -> new StyleHandler(ErrorStatus.STYLE_NOT_FOUND));
                }).collect(Collectors.toList());

        List<Category> categoryList = request.getItemCategoryList().stream()
                .map(categoryId -> {return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<ItemStyle> itemStyleList = ItemStyleConverter.toItemStyleList(styleList);
        List<ItemCategory> itemCategoryList = ItemCategoryConverter.toItemCategoryList(categoryList);

        itemStyleList.forEach(itemStyle -> itemStyle.setItem(newItem));
        itemCategoryList.forEach(itemCategory -> itemCategory.setItem(newItem));
        return null;
    }
}
