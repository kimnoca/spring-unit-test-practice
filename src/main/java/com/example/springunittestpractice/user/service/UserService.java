package com.example.springunittestpractice.user.service;


import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;
import com.example.springunittestpractice.user.dto.UserCreateDto;
import com.example.springunittestpractice.user.dto.UserResponseDto;
import com.example.springunittestpractice.user.dto.UserUpdateDto;
import com.example.springunittestpractice.user.exception.AlreadyExistUserException;
import com.example.springunittestpractice.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserResponseDto createUser(UserCreateDto userCreateDto) {

        if (userRepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            throw new AlreadyExistUserException("이미 존재하는 email 입니다.");
        }

        User user = User.builder()
                .email(userCreateDto.getEmail())
                .nickname(userCreateDto.getNickname())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .email(savedUser.getEmail())
                .nickname(savedUser.getNickname())
                .build();
    }

    public UserResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 User 입니다. " + userId));

        user.changeUserNickname(userUpdateDto.getNewUserNickname());

        return UserResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 User 입니다. " + userId));

        return UserResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
