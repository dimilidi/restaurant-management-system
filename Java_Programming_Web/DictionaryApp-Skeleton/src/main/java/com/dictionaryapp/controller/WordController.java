package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {
    private final WordService wordService;
    private final UserService userService;

    public WordController(WordService wordService, UserService userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @ModelAttribute("addWordData")
    public AddWordDTO initAddWordDTO() {
        return new AddWordDTO();
    }

    @ModelAttribute("languages")
    public LanguageNameEnum[] categories() {
        return LanguageNameEnum.values();
    }

    @GetMapping("/words")
    public String viewAddWord() {
        return "word-add";
    }

    @PostMapping("/words")
    public String doAddWord(@Valid AddWordDTO data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("addWordData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordData", bindingResult);

            return "redirect:/words";
        }

        wordService.add(data);

        return "redirect:/home";
    }


    @DeleteMapping("/remove/{id}")
    public String removeWord(@PathVariable("id") Long id) {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.wordService.removeWord(id);

        return "redirect:/home";
    }

    @DeleteMapping("/remove-all")
    public String removeAllWords() {
        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        this.wordService.removeAllWords();

        return "redirect:/home";
    }
}
