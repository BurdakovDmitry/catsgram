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
@EqualsAndHashCode(of = { "email" })
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Instant registrationDate;
}