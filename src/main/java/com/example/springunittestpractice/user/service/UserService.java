package com.example.springunittestpractice.user.service;


import com.example.springunittestpractice.global.exception.BusinessLogicException;
import com.example.springunittestpractice.user.dao.UserRepository;
import com.example.springunittestpractice.user.domain.User;
import com.example.springunittestpractice.user.dto.UserCreateDto;
import com.example.springunittestpractice.user.dto.UserResponseDto;
import com.example.springunittestpractice.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public UserResponseDto createUser(UserCreateDto userCreateDto) {

        if (userRepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            throw new BusinessLogicException("이미 존재하는 email 입니다.", HttpStatus.BAD_REQUEST.value());
        }

        User user = User.builder()
            .email(userCreateDto.getEmail())
            .nickname(userCreateDto.getNickname())
            .build();

        User savedUser = userRepository.save(user);

        return UserResponseDto.of(savedUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BusinessLogicException("존재하지 않는 User 입니다. " + userId,
                HttpStatus.BAD_REQUEST.value()));

        user.changeUserNickname(userUpdateDto.getNewUserNickname());

        return UserResponseDto.of(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BusinessLogicException("존재하지 않는 User 입니다. " + userId,
                HttpStatus.BAD_REQUEST.value()));

        return UserResponseDto.of(user);
    }
}
