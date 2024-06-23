package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
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
    private final CurrentUser currentUser;

    public HomeController(LanguageService languageService, WordService wordService, UserService userService, CurrentUser currentUser) {
        this.languageService = languageService;
        this.wordService = wordService;
        this.userService = userService;
        this.currentUser = currentUser;
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
            List<Word> words = this.wordService.findWordByLanguageAndUser(language);
            wordsByLanguage.put(language, words);
        }

        modelAndView.addObject("wordsByLanguage", wordsByLanguage);
        modelAndView.addObject("totalWordsCount", this.wordService.findAllWordsCount());
        modelAndView.setViewName("home");

        return modelAndView;
    }
}