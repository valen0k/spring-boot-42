package edu.school21.springboot42.controllers;

import edu.school21.springboot42.models.Confirmation;
import edu.school21.springboot42.models.User;
import edu.school21.springboot42.models.UserConfirm;
import edu.school21.springboot42.services.EmailService;
import edu.school21.springboot42.services.UserConfirmService;
import edu.school21.springboot42.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
public class SignUpController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserConfirmService userConfirmService;

    @GetMapping("/signUp")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String postSignUp(
            @Valid User user,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "signUp";
        }

        Locale locale = LocaleContextHolder.getLocale();
        String uuid = UUID.randomUUID().toString();
        String link = request.getRequestURL().toString()
                .replace(request.getRequestURI(), "/confirm/") + uuid;

        usersService.signUp(user);
        userConfirmService.saveUserConfirm(new UserConfirm(user.getEmail(), uuid));

        emailService.sendEmail(
                user.getEmail(),
                messageSource.getMessage("mail.subject", new Object[0], locale),
                messageSource.getMessage("mail.greeting", new Object[0], locale).replace("username", user.getFirstName())
                        + link
                        + messageSource.getMessage("mail.footer", new Object[0], locale)
        );

        model.addAttribute("registered", true);
        return "signIn";
    }

    @GetMapping("/confirm/{uuid}")
    public String userEnabled(@PathVariable(value="uuid") String uuid) {
        Optional<UserConfirm> byUserUuid = userConfirmService.findByUserUuid(uuid);
        if (byUserUuid.isPresent()) {
            UserConfirm userConfirm = byUserUuid.get();
            usersService.setUserConfirmation(userConfirm.getEmail(), Confirmation.CONFIRMED);
            userConfirmService.deleteUserConfirm(userConfirm);
        }
        return "redirect:/signIn";
    }

}
