package projetb2.overlook_hotel.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {
        model.addAttribute("fragmentPath", "fragments/errors.html");
        model.addAttribute("fragmentName", "errors");
        model.addAttribute("fragmentNumber", "500");
        return "layout/connectedLayout";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDenied(AccessDeniedException e, Model model) {
        model.addAttribute("fragmentPath", "fragments/errors.html");
        model.addAttribute("fragmentName", "errors");
        model.addAttribute("fragmentNumber", "403");
        return "layout/connectedLayout";
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ResponseStatusException e, Model model) {
        model.addAttribute("fragmentPath", "fragments/errors.html");
        model.addAttribute("fragmentName", "errors");
        model.addAttribute("fragmentNumber", "404");
        return "layout/connectedLayout";
    }
}
