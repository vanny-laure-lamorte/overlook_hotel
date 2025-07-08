package projetb2.overlook_hotel.controller.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import projetb2.overlook_hotel.dto.BookingInfoDTO;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.AdminService;

@Controller
public class AdminViewController {

    private final AdminService adminService;

    public AdminViewController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/employees")
    public String showEmployeeList(Model model) {
        List<HotelUser> employees = adminService.getAllEmployees();
        model.addAttribute("employeeList", employees);

        model.addAttribute("fragmentPath", "fragments/employee-list");
        model.addAttribute("fragmentName", "fgt-employee-list");

        return "layout/connectedLayout";
    }

    @GetMapping("/admin/bookings")
    public String showBookings(Model model) {
        List<BookingInfoDTO> bookings = adminService.getAllBookingInfos();
        model.addAttribute("fragmentPath", "fragments/bookings-list");
        model.addAttribute("fragmentName", "fgt-booking-list");
        model.addAttribute("bookings", bookings);
        return "layout/connectedLayout";
    }
}
