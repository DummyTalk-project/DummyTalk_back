package com.example.DummyTalk.Chat.Channel.Controller;

import com.example.DummyTalk.Chat.Channel.Dto.ImageChatDto;
import com.example.DummyTalk.Chat.Channel.Dto.ImageDto;
import com.example.DummyTalk.Chat.Channel.Dto.ImageEmbeddingRequestDto;
import com.example.DummyTalk.Chat.Channel.Dto.MessageRequest;
import com.example.DummyTalk.Common.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DummyTalk.Chat.Channel.Service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.List;

@RequestMapping("/img")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    /***
     * 이미지 저장
     * @param imageDto : channelId, userId, imageUrl, Multipart[], nickname
     * 1. 로컬에 저장
     * 2. DB에 값 저장
     * 3. 추후 성공적으로 저장되면 로컬은 삭제
     */
    @PostMapping("/save")
    public MessageResponse saveImage(@ModelAttribute ImageChatDto imageDto) {

        /* AWS S3 및 DB에 이미지 저장 */
        List<ImageEmbeddingRequestDto> saveImageList = imageService.saveImage(imageDto);
        log.info("\nImageUploadController saveImage    : {}", saveImageList.get(0).getImageId());

        /* fastapi로 이미지 임베딩 요청 */
        imageService.imageEmbedded(saveImageList);

        /* chat data로 저장 */
        List<MessageRequest> messageResponse = imageService.saveImageToChat(saveImageList, imageDto);
        log.info("\nImageUploadController saveImage    : {}", messageResponse);

        /* 클라이언트로 응답 */
        return new MessageResponse(imageDto.getNickname(), "이미지 임베딩 실패", messageResponse);
    }

    @GetMapping("/list/{channelId}")
    public ResponseEntity<ResponseDTO> getImageList(@PathVariable Long channelId) {
        try {
            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "이미지 리스트 조회 성공", imageService.getImageList(channelId)));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}

