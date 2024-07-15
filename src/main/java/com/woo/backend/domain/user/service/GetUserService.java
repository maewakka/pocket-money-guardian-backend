package com.woo.backend.domain.user.service;

import com.woo.backend.domain.user.dto.req.SignInReq;
import com.woo.backend.domain.user.dto.resp.UserResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.backend.global.minio.util.MinioUtil;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MinioUtil minioUtil;

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new BizException("user_not_found"));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BizException("user_not_found"));
    }
    public User findUserByEmailAndPassword(SignInReq req) {
        User user = userRepository.findUserByEmail(req.getEmail()).orElseThrow(() -> new BizException("login_fail"));
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) throw new BizException("login_fail");

        return user;
    }

    public List<UserResp> findAllUserByNickName(String nickName, User currentUser) {
        return userRepository.findAllUserByNickName(nickName).stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(UserResp::of).collect(Collectors.toList());
    }
}
