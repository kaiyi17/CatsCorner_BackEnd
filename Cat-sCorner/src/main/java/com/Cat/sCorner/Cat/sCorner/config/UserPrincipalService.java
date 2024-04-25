package com.Cat.sCorner.Cat.sCorner.config;

import com.Cat.sCorner.Cat.sCorner.entity.Role;
import com.Cat.sCorner.Cat.sCorner.entity.User;
import com.Cat.sCorner.Cat.sCorner.entity.UserPrincipal;
import com.Cat.sCorner.Cat.sCorner.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@RequiredArgsConstructor
@Component
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<GrantedAuthority> authorities = new HashSet<>();
        if (!user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return new UserPrincipal(user,authorities);
    }
}
