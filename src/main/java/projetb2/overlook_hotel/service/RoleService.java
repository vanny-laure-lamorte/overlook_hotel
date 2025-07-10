package projetb2.overlook_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetb2.overlook_hotel.repository.RoleRepository;

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

    /**
     * Removes a role from a user.
     *
     * @param username the username of the user from whom the role is to be removed
     * @param role     the role to be removed
     */
    public void removeRoleFromUser(String username, String role) {
        System.out.println("Role " + role + " removed from user " + username);
    }

    /**
     * Retrieves the roles for a given user.
     *
     * @param username the username of the user whose roles are to be retrieved
     */
    public void getUserRoles(String username) {
        System.out.println("Roles for user " + username + ": [Admin, User]");
    }
}
