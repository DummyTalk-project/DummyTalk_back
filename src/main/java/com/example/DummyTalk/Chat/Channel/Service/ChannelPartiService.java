package com.example.DummyTalk.Chat.Channel.Service;

import com.example.DummyTalk.Chat.Channel.Dto.ChannelParticipantDto;

public interface ChannelPartiService {
    void joinChannel(ChannelParticipantDto participantDto);
    void leaveChannel(ChannelParticipantDto participantDto);
}