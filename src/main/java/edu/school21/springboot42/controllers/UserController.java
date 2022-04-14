package edu.school21.springboot42.controllers;

import edu.school21.springboot42.models.Film;
import edu.school21.springboot42.models.Role;
import edu.school21.springboot42.models.Session;
import edu.school21.springboot42.services.FilmsService;
import edu.school21.springboot42.services.MessagesService;
import edu.school21.springboot42.services.SessionsService;
import edu.school21.springboot42.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class UserController {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private FilmsService filmsService;

    @Autowired
    private SessionsService sessionsService;

    @Autowired
    private UsersService usersService;

    private List<Film> filmsList;
    private List<Session> sessionsList;

    @GetMapping("/films")
    public String getFilmsPage(Model model) {
        model.addAttribute("isAdmin", usersService.getCurrentUser().getRole().equals(Role.ADMIN));
        filmsList = filmsService.findAllFilms();
        model.addAttribute("films", filmsList);
        return "films";
    }

    @GetMapping("/sessions")
    public String getSessionsPage(Model model) {
        sessionsList = sessionsService.findAllSessions();
        model.addAttribute("isAdmin", usersService.getCurrentUser().getRole().equals(Role.ADMIN));
        model.addAttribute("sessions", sessionsList);
        return "sessions";
    }

    @GetMapping("/sessions/{session}")
    public String getSessionsView(
            @PathVariable(value="session") String session,
            Model model) {
        try {
            Film film = filmsService.findFilmByName(session);
            model.addAttribute("film", film);
        } catch (NoResultException e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        return "session-view";
    }


    @RequestMapping(value = "/films/search", params = "filmName", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Map<String, Object>> getFilmsJson(@RequestParam("filmName") String filmName) {

        List<Map<String, Object>> json = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w*" + filmName + "\\w*)", Pattern.CASE_INSENSITIVE);


        for (Film film : filmsList) {
            if (pattern.matcher(film.getTitle()).find()) {
                Map<String, Object> filmObj = new LinkedHashMap<>();
                filmObj.put("id", film.getId());
                filmObj.put("title", film.getTitle());
                filmObj.put("year", film.getYear());
                filmObj.put("image", film.getImage());
                json.add(filmObj);
            }
        }

        return json;
    }

    @RequestMapping(value = "/sessions/search", params = "filmName", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Map<String, Object>> getSessionsJson(@RequestParam("filmName") String filmName) {

        List<Map<String, Object>> json = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\w*" + filmName + "\\w*)", Pattern.CASE_INSENSITIVE);

        for (Session session : sessionsList) {
            if (pattern.matcher(session.getFilm().getTitle()).find()) {
                Map<String, Object> sessionObj = new LinkedHashMap<>();
                sessionObj.put("id", session.getId());
                sessionObj.put("date", session.getDate());
                sessionObj.put("time", session.getTime());
                sessionObj.put("price", session.getPrice());
                Map<String, String> nestedMap = new LinkedHashMap<>();
                nestedMap.put("title", session.getFilm().getTitle());
                nestedMap.put("year", session.getFilm().getYear());
                nestedMap.put("ageLimit", session.getFilm().getAgeLimit());
                nestedMap.put("description", session.getFilm().getDescription());
                nestedMap.put("posterUrl", session.getFilm().getImage());
                sessionObj.put("film", nestedMap);
                json.add(sessionObj);
            }
        }

        return json;
    }

    @GetMapping("/films/{filmTitle}/chat")
    public String getChatPage(
            Model model,
            @PathVariable(value="filmTitle") String filmTitle
    ) {

        Film film;
        try {
            film = filmsService.findFilmByName(filmTitle);
            model.addAttribute("film", film);
        } catch (NoResultException e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        model.addAttribute("user", usersService.getCurrentUser());
        model.addAttribute("messages", messagesService.getMessagesByFilmId(film));

        return "chat";
    }

}
