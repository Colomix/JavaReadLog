package ru.popkov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.popkov.services.ILogReader;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Олег on 25.02.2016.
 */
@Controller
public class HomeController {

    private final ILogReader logReader;

    @Autowired
    public HomeController(ILogReader logReader) {
        this.logReader = logReader;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws IOException {
        String log;

        try {
            log = logReader.read();
        } catch (Exception e) {
            log = "Не удалось прочитать лог.";
        }

        model.addAttribute("logs", log);

        return "index";
    }
}
