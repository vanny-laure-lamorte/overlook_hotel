package projetb2.overlook_hotel.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projetb2.overlook_hotel.model.HotelUser;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    private final HotelUser user;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(HotelUser user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getUserAddress() {
        return user.getUserAddress();
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public java.util.Date getDob() {
        return user.getDob();
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
