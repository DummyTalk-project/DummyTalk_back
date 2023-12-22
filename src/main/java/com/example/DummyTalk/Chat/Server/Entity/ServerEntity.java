package com.example.DummyTalk.Chat.Server.Entity;

import com.example.DummyTalk.Chat.Channel.Entity.ChannelEntity;
import com.example.DummyTalk.Common.Entity.BaseTimeEntity;
import com.example.DummyTalk.User.Entity.User;
import com.example.DummyTalk.User.Entity.UserChat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "server")
public class ServerEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverName;
    private String userName;
    private String invitedUser;

    @Column(nullable = false)
    private int maxUsers;

    @Column(nullable = false)
    private int currentUsers;

    private String filePath;

    private String fileName;


    /* 유저와 서버의 관계 */
    @OneToMany(mappedBy = "server", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserChat> userChats = new ArrayList<>();

    /* 채널과의 연관관계 (부모) */
    @JsonIgnore
    @OneToMany(mappedBy = "serverEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChannelEntity> channelEntityList = new ArrayList<>();

    /* 서버와 서버롤의 연관관계 */
    @OneToMany(mappedBy = "server", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServerRoleEntity> serverRoleEntityList = new ArrayList<>();


}
