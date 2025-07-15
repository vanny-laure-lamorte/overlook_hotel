package projetb2.overlook_hotel.controller.api;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.text.SimpleDateFormat;
import java.util.Date;


import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.HotelUserService;

@RestController
public class ProfileApiController {

    @Autowired
    private HotelUserService hotelUserService;

        @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * Updates the profile of the currently authenticated user.
     * This method accepts a HotelUser object with updated details and saves it.
     * If the user is successfully updated, it redirects to the profile page.
     * @param updatedUser The HotelUser object containing updated user details
     * @param currentUser The currently authenticated user
     * @return RedirectView to the profile page
     */
    @PostMapping("/api/profile/update")
    public RedirectView updateProfile(
        @ModelAttribute("user") HotelUser updatedUser,
        @AuthenticationPrincipal UserDetails currentUser) {

        Integer userId = updatedUser.getId();
        HotelUser existingUser = hotelUserService.findById(userId);
        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setDob(updatedUser.getDob());
            existingUser.setUserAddress(updatedUser.getUserAddress());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            hotelUserService.saveUser(existingUser);
        }
        return new RedirectView("/view/profile");
    }
}





