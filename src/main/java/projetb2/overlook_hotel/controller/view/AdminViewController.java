package projetb2.overlook_hotel.controller.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projetb2.overlook_hotel.dto.BookingInfoDTO;
import projetb2.overlook_hotel.model.BookingStatus;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final AdminService adminService;

    public AdminViewController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        List<HotelUser> employees = adminService.getAllEmployees();
        model.addAttribute("employeeList", employees);

        model.addAttribute("fragmentPath", "fragments/employee-list");
        model.addAttribute("fragmentName", "fgt-employee-list");

        return "layout/connectedLayout";
    }

    @GetMapping("/bookings")
    public String showBookings(Model model) {
        List<BookingInfoDTO> bookings = adminService.getAllBookingInfos();
        model.addAttribute("bookings", bookings);

        // Injecter les valeurs d'enum directement pour Thymeleaf
        model.addAttribute("PENDING", BookingStatus.PENDING);
        model.addAttribute("ACCEPTED", BookingStatus.ACCEPTED);
        model.addAttribute("DECLINED", BookingStatus.DECLINED);

        model.addAttribute("rooms", adminService.getAllRooms());
        model.addAttribute("status", BookingStatus.values());

        model.addAttribute("fragmentPath", "fragments/bookings-list");
        model.addAttribute("fragmentName", "fgt-booking-list");

        return "layout/connectedLayout";
    }
}
