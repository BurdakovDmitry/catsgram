package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User create(User user) {
        if (user.getEmail() == null) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

        if (!users.isEmpty()) {
            for (User mapUser : users.values()) {
                if (mapUser.getEmail().equals(user.getEmail())) {
                    throw new DuplicatedDataException("Этот имейл уже используется");
                }
            }
        }

        user.setId(getNextId());
        user.setRegistrationDate(Instant.now());
        users.put(user.getId(), user);
        return user;
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

        if (users.containsKey(user.getId())) {
            User oldUser = users.get(user.getId());
            if (oldUser.getEmail().equals(user.getEmail())) {
                throw new DuplicatedDataException("Этот имейл уже используется");
            }

            if (user.getEmail() != null) {
                oldUser.setEmail(user.getEmail());
            }

            if (user.getUsername() != null) {
                oldUser.setUsername(user.getUsername());
            }

            if (user.getPassword() != null) {
                oldUser.setPassword(user.getPassword());
            }
            return oldUser;
        }

        throw new NotFoundException("Пользователь с id = " + user.getId() + " не найден");
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    protected Optional<User> findUserById(long id) {
        for (Long userId : users.keySet()) {
            if (userId == id) {
                return Optional.of(users.get(id));
            }
        }
        return Optional.empty();
    }
}
