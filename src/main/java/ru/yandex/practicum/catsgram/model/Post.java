package ru.yandex.practicum.catsgram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = { "id" })
@RequiredArgsConstructor
public class Post {
    private Long id;
    private long authorId;
    private String description;
    private Instant postDate;
}