package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final LanguageService languageService;
    private final WordService wordService;
    private final UserService userService;

    public HomeController(LanguageService languageService, WordService wordService, UserService userService) {
        this.languageService = languageService;
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String notLogged() {
        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView) {
        if (!userService.isLoggedIn()) {
            modelAndView.setViewName("redirect:/home");
        }

        Map<LanguageNameEnum, List<Word>> wordsByLanguage = new EnumMap<>(LanguageNameEnum.class);

        for (LanguageNameEnum language : LanguageNameEnum.values()) {
            List<Word> words = this.languageService.findAllWordsByLanguage(language.name());
            wordsByLanguage.put(language, words);
        }

        modelAndView.addObject("wordsByLanguage", wordsByLanguage);
        modelAndView.addObject("totalWordsCount", this.wordService.findAllWordsCount());
        modelAndView.setViewName("home");

        return modelAndView;
    }
}