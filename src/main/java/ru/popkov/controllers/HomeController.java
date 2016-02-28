package ru.popkov.controllers;

import org.slf4j.Logger;
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
    private final Logger logger;

    @Autowired
    public HomeController(ILogReader logReader, Logger logger) {
        this.logReader = logReader;
        this.logger = logger;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws IOException {
        String log;

        try {
            log = logReader.read();
        } catch (Exception e) {
            log = "Не удалось прочитать лог.";
            logger.error("Unable to read file. Exception: ", e);
        }

        model.addAttribute("logs", log);

        return "index";
    }
}
