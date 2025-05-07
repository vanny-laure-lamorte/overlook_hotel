package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void assignRoleToUser(String username, String role) {
        System.out.println("Role " + role + " assigned to user " + username);
    }

    public void removeRoleFromUser(String username, String role) {
        System.out.println("Role " + role + " removed from user " + username);
    }

    public void getUserRoles(String username) {
        System.out.println("Roles for user " + username + ": [Admin, User]");
    }
}
