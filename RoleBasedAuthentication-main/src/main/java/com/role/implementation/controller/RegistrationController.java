package com.role.implementation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.role.implementation.DTO.UserRegisteredDTO;
import com.role.implementation.service.DefaultUserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	 private DefaultUserService userService;

	    public RegistrationController(DefaultUserService userService) {
	        super();
	        this.userService = userService;
	    }

	    @ModelAttribute("user")
	    public UserRegisteredDTO userRegistrationDto() {
	        return new UserRegisteredDTO();
	    }

	    @GetMapping
	    public String showRegistrationForm() {
	        return "register";
	    }

	    @PostMapping
	    
            public String registerUserAccount(@ModelAttribute("user") UserRegisteredDTO registrationDto, 
                                  Model model, 
                                  RedirectAttributes redirectAttributes) {
                try {
                // Asigna el rol por defecto al DTO de registro
                registrationDto.setRole("USER");
        
                // Guarda el usuario con el rol predeterminado
                userService.save(registrationDto);
        
                return "redirect:/login";
                } catch (IllegalArgumentException e) {
                // Añadir el mensaje de error al modelo para mostrar en el formulario
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/registration";
    }
}
}
