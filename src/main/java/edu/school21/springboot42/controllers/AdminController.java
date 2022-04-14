
package edu.school21.springboot42.controllers;

import edu.school21.springboot42.models.*;
import edu.school21.springboot42.services.FilmsService;
import edu.school21.springboot42.services.HallsService;
import edu.school21.springboot42.services.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private HallsService hallsService;

    @Autowired
    private FilmsService filmsService;

    @Autowired
    private SessionsService sessionsService;

    @GetMapping( "/admin/panel")
    public String index() {
        return "admin-panel";
    }

    @GetMapping("/admin/panel/halls")
    public String addHallsForm(Model model) {
        model.addAttribute("hall", new Hall());

        return "add-halls";
    }

    @PostMapping("/admin/panel/halls")
    public String addHallsSave(@ModelAttribute("hall") Hall hall) {
        hallsService.saveHall(hall);
        return "redirect:/admin/panel";
    }

    @GetMapping("/admin/panel/films")
    public String addFilmsForm(Model model) {
        model.addAttribute("film", new Film());

        return "add-films";
    }

    @PostMapping("/admin/panel/films")
    public String addFilmsSave(
            @RequestParam MultipartFile file,
            @ModelAttribute("film") Film film
    ) throws Exception {

        String path = "./src/main/resources/posters";
        String uniqueName = ImageController.saveImage(path, file, file.getContentType());
        assert uniqueName != null;

        filmsService.saveFilm(new Film(
                film.getTitle(),
                film.getYear(),
                film.getAgeLimit(),
                film.getDescription(),
                uniqueName
        ));

        return "redirect:/admin/panel";
    }

    @GetMapping("/admin/panel/sessions")
    public String addSessions(Model model) {
        model.addAttribute("halls", hallsService.findAllHalls());
        model.addAttribute("films", filmsService.findAllFilms());

        return "add-sessions";
    }

    @PostMapping("/admin/panel/sessions")
    public String submit(@ModelAttribute("selectedHall") String selectedHall,
                         @ModelAttribute("selectedFilm") String selectedFilm,
                         @ModelAttribute("selectedDate") String selectedDate,
                         @ModelAttribute("selectedPrice") String selectedPrice
    ) throws ParseException {

        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        DateFormat targetFormat = new SimpleDateFormat("MMMM dd/HH:mm");
        Date date = originalFormat.parse(selectedDate);
        String[] splittedDate = targetFormat.format(date).split("/");

        Session session = new Session(
                filmsService.findAllFilms().stream()
                        .filter(s -> s.getTitle().equals(selectedFilm))
                        .findAny().orElse(null),
                hallsService.findAllHalls().stream()
                        .filter(s -> s.getId() == Integer.parseInt(selectedHall
                                .substring(0, selectedHall.indexOf(' '))))
                        .findAny().orElse(null),
                splittedDate[0],
                splittedDate[1],
                selectedPrice);
        sessionsService.saveSession(session);

        return "redirect:/admin/panel";
    }

}