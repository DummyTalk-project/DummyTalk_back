package com.example.DummyTalk.Chat.Channel.Controller;

import com.example.DummyTalk.Chat.Channel.Dto.ChannelParticipantDto;
import com.example.DummyTalk.Chat.Channel.Service.ChannelPartiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/channelParti")
public class ChannelPartiController {


    private final ChannelPartiService channelPartiService;

    @PostMapping("/join")
    public ResponseEntity<?> joinChannel(@RequestBody ChannelParticipantDto participantDto) {
        channelPartiService.joinChannel(participantDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leaveChannel(@RequestBody ChannelParticipantDto participantDto) {
        channelPartiService.leaveChannel(participantDto);
        return ResponseEntity.ok().build();
    }
}