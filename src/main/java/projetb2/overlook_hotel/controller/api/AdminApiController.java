package projetb2.overlook_hotel.controller.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.AdminService;
@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private final AdminService adminService;

    public AdminApiController(AdminService adminBookingService) {
        this.adminService = adminBookingService;
    }

    @GetMapping("/employee-list")
    public List<HotelUser> getAllEmployee() {
        return adminService.getAllEmployees();
    }
}

