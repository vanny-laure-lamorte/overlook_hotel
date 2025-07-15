package projetb2.overlook_hotel.controller.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projetb2.overlook_hotel.dto.BookingDTO;
import projetb2.overlook_hotel.model.BookingStatus;
import projetb2.overlook_hotel.model.HotelUser;
import projetb2.overlook_hotel.service.DashboardService;

@Controller
@RequestMapping("/view/dashboard")
public class DashboardViewController {

    private final DashboardService dashboardService;

    public DashboardViewController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /*
     * Displays the list of employees in the admin panel.
     * The list is fetched from the admin service and added to the model.
     */
    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        List<HotelUser> employees = dashboardService.getAllEmployees();
        model.addAttribute("employeeList", employees);

        model.addAttribute("fragmentPath", "fragments/employee-list");
        model.addAttribute("fragmentName", "fgt-employee-list");

        return "layout/connectedLayout";
    }

    /*
     * Displays the list of employees in the admin panel.
     * The list is fetched from the admin service and added to the model.
     */
    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        List<HotelUser> customers = dashboardService.getAllCustomers();
        model.addAttribute("customersList", customers);

        model.addAttribute("fragmentPath", "fragments/customer-list");
        model.addAttribute("fragmentName", "fgt-customer-list");

        return "layout/connectedLayout";
    }

    /*
     * Displays the list of bookings in the admin panel.
     * The list is fetched from the admin service and added to the model.
     */
    @GetMapping("/bookings")
    public String showBookings(Model model) {
        List<BookingDTO> bookings = dashboardService.getAllBookingInfos();
        model.addAttribute("bookings", bookings);
        model.addAttribute("rooms", dashboardService.getAllRooms());
        model.addAttribute("status", BookingStatus.values());

        model.addAttribute("PENDING", BookingStatus.PENDING);
        model.addAttribute("ACCEPTED", BookingStatus.ACCEPTED);
        model.addAttribute("ACCEPTED", BookingStatus.CANCELLED);
        model.addAttribute("DECLINED", BookingStatus.DECLINED);

        model.addAttribute("fragmentPath", "fragments/bookings-list");
        model.addAttribute("fragmentName", "fgt-booking-list");

        return "layout/connectedLayout";
    }
}