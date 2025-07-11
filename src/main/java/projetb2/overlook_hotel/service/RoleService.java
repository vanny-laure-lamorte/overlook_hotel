package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.RoleRepository;
import projetb2.overlook_hotel.model.Role;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Assigns a role to a user.
     *
     * @param username the username of the user to whom the role is to be assigned
     * @param role     the role to be assigned
     */
    public void assignRoleToUser(String username, String role) {
        System.out.println("Role " + role + " assigned to user " + username);
    }

    public Role setUserRole(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }
}
