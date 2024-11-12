package com.project.vinpong.service.ItemService;

import com.project.vinpong.apiPayload.code.status.ErrorStatus;
import com.project.vinpong.apiPayload.exception.handler.CategoryHandler;
import com.project.vinpong.apiPayload.exception.handler.ItemHandler;
import com.project.vinpong.apiPayload.exception.handler.MemberHandler;
import com.project.vinpong.apiPayload.exception.handler.StyleHandler;
import com.project.vinpong.converter.ItemCategoryConverter;
import com.project.vinpong.converter.ItemConverter;
import com.project.vinpong.converter.ItemStyleConverter;
import com.project.vinpong.domain.Category;
import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Member;
import com.project.vinpong.domain.Style;
import com.project.vinpong.domain.mapping.ItemCategory;
import com.project.vinpong.domain.mapping.ItemStyle;
import com.project.vinpong.repository.CategoryRepository;
import com.project.vinpong.repository.ItemRepository;
import com.project.vinpong.repository.MemberRepository;
import com.project.vinpong.repository.StyleRepository;
import com.project.vinpong.web.dto.ItemRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemCommandServiceImpl implements ItemCommandService{
    private final StyleRepository styleRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Item joinItem(ItemRequestDTO.JoinDTO joinDTO, String sellerName) {
        Optional<Member> optionalMember = memberRepository.findByUsernamae(sellerName);
        if (optionalMember.isEmpty()) throw new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND);
        Member seller = optionalMember.get();

        Item newItem = ItemConverter.toItem(joinDTO, seller);

        List<Style> styleList = joinDTO.getItemStyleList().stream()
                .map(styleId -> {return styleRepository.findById(styleId).orElseThrow(() -> new StyleHandler(ErrorStatus.STYLE_NOT_FOUND));
                }).collect(Collectors.toList());

        List<Category> categoryList = joinDTO.getItemCategoryList().stream()
                .map(categoryId -> {return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<ItemStyle> itemStyleList = ItemStyleConverter.toItemStyleList(styleList);
        List<ItemCategory> itemCategoryList = ItemCategoryConverter.toItemCategoryList(categoryList);

        itemStyleList.forEach(itemStyle -> itemStyle.setItem(newItem));
        itemCategoryList.forEach(itemCategory -> itemCategory.setItem(newItem));
        return itemRepository.save(newItem);
    }

    @Override
    public List<Item> searchByStyleAndCategory(ItemRequestDTO.searchDTO request) {
        List<Item> allItems = itemRepository.findAll();

        List<Style> styleList = Optional.ofNullable(request.getItemStyleList())
                .map(list -> list.stream()
                        .map(styleId -> styleRepository.findById(styleId)
                                .orElseThrow(() -> new StyleHandler(ErrorStatus.STYLE_NOT_FOUND)))
                        .collect(Collectors.toList()))
                .orElse(null);

        List<Category> categoryList = Optional.ofNullable(request.getItemCategoryList())
                .map(list -> list.stream()
                        .map(categoryId -> categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND)))
                        .collect(Collectors.toList()))
                .orElse(null);

        return allItems.stream()
                .filter(item -> styleList == null ||
                        item.getItemStyleList().stream()
                                .anyMatch(itemStyle -> styleList.contains(itemStyle.getStyle())))
                .filter(item -> categoryList == null ||
                        item.getItemCategoryList().stream()
                                .anyMatch(itemCategory -> styleList.contains(itemCategory.getCategory())))
                .filter(item -> request.getSearchKeyword() == null ||
                        (item.getItemName() != null) && item.getItemName().contains(request.getSearchKeyword()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllItemsByShop(Long shopId) {
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream()
                .filter(item -> item.getSeller().getMemberId() == shopId)
                .collect(Collectors.toList());
    }

    @Override
    public Item searchById(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isEmpty()) throw new ItemHandler(ErrorStatus.ITEM_NOT_FOUND);
        return item.get();
    }
}
