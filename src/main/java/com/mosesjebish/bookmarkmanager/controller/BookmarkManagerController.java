package com.mosesjebish.bookmarkmanager.controller;

import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.CardDetailsResponseDto;
import com.mosesjebish.bookmarkmanager.dto.GroupCardDetailsResponseDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.service.CardDetailService;
import com.mosesjebish.bookmarkmanager.service.GroupDetailService;
import com.mosesjebish.bookmarkmanager.service.URLShortener;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("/api/bookmarks")
@CrossOrigin
public class BookmarkManagerController {

    private final CardDetailService cardDetailService;

    private final GroupDetailService groupDetailService;

    public BookmarkManagerController(CardDetailService cardDetailService, GroupDetailService groupDetailService) {
        this.cardDetailService = cardDetailService;
        this.groupDetailService = groupDetailService;
    }

    @GetMapping("/fetchAllCards")
    @ApiResponse(responseCode = "200", description = "Fetches Cards", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CardDetailsResponseDto.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error")
    public ResponseEntity<CardDetailsResponseDto> fetchAllCards() {
        List<CardDetailDto> resultDtos = cardDetailService.fetchApprovedCards(true);
        if (CollectionUtils.isEmpty(resultDtos)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CardDetailsResponseDto responseDto = new CardDetailsResponseDto();
        responseDto.setCardDetailDtoList(resultDtos);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/fetchCardsGroupBy")
    @ApiResponse(responseCode = "200", description = "Fetches a map of Group along with its corresponding card details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CardDetailsResponseDto.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error")
    public ResponseEntity<GroupCardDetailsResponseDto> fetchCardsGroupBy(@RequestParam(value = "groupBy") String groupBy) {

        Map<String, List<CardDetailDto>> map = cardDetailService.fetchCardsByGroup(groupBy);

        if (CollectionUtils.isEmpty(map)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GroupCardDetailsResponseDto responseDto = new GroupCardDetailsResponseDto();
        responseDto.setGroupCardDetails(map);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("shortenUrl")
    @ApiResponse(responseCode = "200", description = "Fetches Cards", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "500", description = "Internal Error")
    public ResponseEntity<String> shortenUrl(@RequestParam(value = "url") String url, @RequestParam(value = "type") String type) {
        URLShortener u = new URLShortener(5, "www.mosesjebish.com/" + type);
        return new ResponseEntity<>(u.shortenURL(url), HttpStatus.OK);
    }

    @GetMapping("/fetchGroups")
    @ApiResponse(responseCode = "200", description = "Fetches Groups", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error")
    public ResponseEntity<String> fetchGroups() {
        return new ResponseEntity<>("Shortly you will receive the created groups through this end point :)", HttpStatus.OK);
    }

    @PostMapping("/createCard")
    @ApiResponse(responseCode = "200", description = "Creates Cards", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CardDetailsResponseDto.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error")
    public ResponseEntity<CardDetailsResponseDto> createCard(@RequestBody List<CardDetailDto> cardDetailDtos) {
        List<CardDetailDto> resultDtos = cardDetailService.persist(cardDetailDtos);
        if (CollectionUtils.isEmpty(resultDtos)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CardDetailsResponseDto responseDto = new CardDetailsResponseDto();
        responseDto.setCardDetailDtoList(resultDtos);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/createGroup")
    @ApiResponse(responseCode = "200", description = "Creates Groups", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Internal Error")
    public ResponseEntity<String> createGroup(@RequestBody List<GroupDetailDto> groupDetails) {
        List<GroupDetailDto> groupDetailDtos = groupDetailService.persist(groupDetails);
        return new ResponseEntity<>("Shortly you will be able to create groups through this end point :)", HttpStatus.OK);
    }
}
