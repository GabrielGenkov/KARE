package io.kare.backend.service.impl;

import io.kare.backend.component.user.UserJwtTokenGenerator;
import io.kare.backend.entity.UserEntity;
import io.kare.backend.exception.*;
import io.kare.backend.mapper.UserMapper;
import io.kare.backend.payload.request.*;
import io.kare.backend.payload.response.*;
import io.kare.backend.repository.UserRepository;
import io.kare.backend.service.UserService;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserJwtTokenGenerator userJwtTokenGenerator;

	public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
		PasswordEncoder passwordEncoder,
		UserJwtTokenGenerator userJwtTokenGenerator
	) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userJwtTokenGenerator = userJwtTokenGenerator;
	}

	@Override
	public RegisterUserResponse register(RegisterUserRequest request) {
		if (this.userRepository.existsByEmail(request.email())) {
			throw new UserAlreadyExistsException(request.email());
		}
		String password = this.passwordEncoder.encode(request.password());
		UserEntity userEntity = this.userMapper.map(request, password);
		this.userRepository.save(userEntity);
		String token = this.userJwtTokenGenerator.generateToken(userEntity);
		return this.userMapper.mapRegisterUserResponse(userEntity, token);
	}

	@Override
	public LoginUserResponse login(LoginUserRequest request) {
		Optional<UserEntity> optionalUserEntity = this.userRepository.findByEmail(request.email());
		UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new UserNotExistsException(request.email()));
		if (!this.passwordEncoder.matches(request.password(), userEntity.getPassword())) {
			throw new UserPasswordIncorrectException(request.email());
		}
		String token = this.userJwtTokenGenerator.generateToken(userEntity);
		return this.userMapper.mapLoginUserResponse(userEntity, token);
	}


	@Override
	public UserEntity getUserById(String id) {
		return this.userRepository.findById(id).orElse(null);

	}

}
