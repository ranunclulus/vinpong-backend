package com.project.vinpong.web.controller;

import com.project.vinpong.apiPayload.ApiResponse;
import com.project.vinpong.converter.ItemConverter;
import com.project.vinpong.domain.Item;
import com.project.vinpong.domain.Member;
import com.project.vinpong.jwt.JwtSecurityUtil;
import com.project.vinpong.jwt.JwtTokenProvider;
import com.project.vinpong.service.ItemService.ItemCommandService;
import com.project.vinpong.service.MemberService.CustomUserDetailService;
import com.project.vinpong.service.MemberService.MemberCommandService;
import com.project.vinpong.web.dto.ItemRequestDTO;
import com.project.vinpong.web.dto.ItemResponseDTO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemRestController {
    private final ItemCommandService itemCommandService;
    private final MemberCommandService memberCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ApiResponse<ItemResponseDTO.JoinResultDTO> join(@RequestBody @Valid ItemRequestDTO.JoinDTO joinDTO) {
        String sellerName = JwtSecurityUtil.getCurrentMemberId();
        Item item = itemCommandService.joinItem(joinDTO, sellerName);
        return ApiResponse.onSuccess(ItemConverter.toJoinResultDTO(item));
    }

    @GetMapping()
    public ApiResponse<List<ItemResponseDTO.ReadResultDTO>> searchByStyleAndCategory(@RequestBody @Valid ItemRequestDTO.searchDTO searchDTO) {
        List<Item> items = itemCommandService.searchByStyleAndCategory(searchDTO);
        return ApiResponse.onSuccess(ItemConverter.toSearchItemsResultDTO(items));
    }

    @GetMapping("/itemlist/{shopId}")
    public ApiResponse<List<ItemResponseDTO.ReadResultDTO>> getAllItemsByShop(@PathVariable("shopId") Long shopId) {

        List<Item> items = itemCommandService.getAllItemsByShop(shopId);

        return ApiResponse.onSuccess(ItemConverter.toSearchItemsResultDTO(items));
    }

    @GetMapping("/{itemId}")
    public ApiResponse<ItemResponseDTO.ReadDetailResultDTO> searchByItemid(@PathVariable("itemId") Long itemId) {
        Item item = itemCommandService.searchById(itemId);
        return ApiResponse.onSuccess(ItemConverter.toItemtDTO(item));
    }

    @DeleteMapping("/{itemId}")
    public ApiResponse<ItemResponseDTO.DeleteResultDTO> deleteByItemid(@PathVariable("itemId") Long itemId,
                                                                       HttpServletRequest request) {
        String username = extractUserName(request);
        itemCommandService.deleteById(itemId, username);
        return ApiResponse.onSuccess(ItemResponseDTO.DeleteResultDTO.builder().itemId(itemId).build());
    }

    private String extractUserName(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Claims claims = jwtTokenProvider.parseClaims(token);
        return (String) claims.get("sub");
    }

}

