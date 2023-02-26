package com.api.project.data;

import com.api.project.model.RoleModel;
import com.api.project.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDetailsData implements UserDetails {

    private final Optional<UserModel> user;

    public UserDetailsData(Optional<UserModel> user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.get().getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.orElse(new UserModel()).getPass();
    }

    @Override
    public String getUsername() {
        return user.orElse(new UserModel()).getEmail();
    }

    public int getAttempts() {
        return user.orElse(new UserModel()).getAttempts();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
