package com.example.DummyTalk.Chat.Channel.Service;

import com.example.DummyTalk.Chat.Channel.Dto.ChannelParticipantDto;
import com.example.DummyTalk.Chat.Channel.Entity.ChannelEntity;
import com.example.DummyTalk.Chat.Channel.Repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelPartiServiceImpl implements ChannelPartiService {

    private final ChannelRepository channelRepository;

    @Override
    public void joinChannel(ChannelParticipantDto participantDto) {
        ChannelEntity channel = channelRepository.findById(participantDto.getChannelId()).orElseThrow();
        if (channel.getChannelCount() < channel.getMaxUserCnt().getValue()) {
            channel.setChannelCount(channel.getChannelCount() + 1);
            channelRepository.save(channel);
        }
        // 추가 로직: 채널에 사용자 추가
    }

    @Override
    public void leaveChannel(ChannelParticipantDto participantDto) {
        ChannelEntity channel = channelRepository.findById(participantDto.getChannelId()).orElseThrow();
        channel.setChannelCount(Math.max(0, channel.getChannelCount() - 1));
        channelRepository.save(channel);
        // 추가 로직: 채널에서 사용자 제거
    }
}