package com.test.service;

import com.test.config.UserRole;
import com.test.domain.CustomUser;
import com.test.dto.incoming.CustomUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.repository.CustomUserRepository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
public class JpaUserDetailsService implements UserDetailsService {

    private final CustomUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JpaUserDetailsService(CustomUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser customUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("E-mail not found"));

        String[] roles = customUser.getUserRoles().stream()
                .map(Enum::toString)
                .toArray(String[]::new);

        return User
                .withUsername(customUser.getEmail())
                .authorities(AuthorityUtils.createAuthorityList(roles))
                .password(customUser.getPassword())
                .build();

    }

    public void register(CustomUserCommand customUserCommand) {
        CustomUser customUser = userRepository.findByEmail(customUserCommand.getEmail()).orElse(null);

        if (customUser == null) {
            customUser = new CustomUser();
            customUser.setEmail(customUserCommand.getEmail());
            customUser.setPassword(passwordEncoder.encode(customUserCommand.getPassword()));
            customUser.setName(customUserCommand.getName());
            customUser.setDeletedAt(LocalDateTime.now());
            customUser.setUserRoles(customUserCommand.getUserRoles().stream().map(UserRole::valueOf).collect(Collectors.toList()));
            userRepository.save(customUser);
        }
    }
}
