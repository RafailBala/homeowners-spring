package com.example.homeownersspring.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "topic")
    private String topic;

    @NotNull
    @Column(name = "content")
    private String content;

    @Column(name = "file_address")
    private String fileAddress;

    @Column(name = "status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_User", referencedColumnName = "id")
    private User id_User;

    public Request() {
    }
    public Request(String topic, String content, String fileAddress, int status, User id_User) {
        this.status=status;
        this.topic = topic;
        this.content = content;
        this.fileAddress = fileAddress;
        this.id_User = id_User;
    }
}
