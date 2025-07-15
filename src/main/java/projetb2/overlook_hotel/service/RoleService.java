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
     * Sets the user role based on the provided role name.
     *
     * @param roleName the name of the role to set
     * @return the Role object corresponding to the given role name
     * @throws IllegalArgumentException if the role is not found
     */
    public Role setUserRole(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }
}
