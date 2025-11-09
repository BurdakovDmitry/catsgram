package ru.yandex.practicum.catsgram.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = { "id" })
@RequiredArgsConstructor
public class Image {
    private Long id;
    private long postId;
    private String originalFileName;
    private String filePath;
}