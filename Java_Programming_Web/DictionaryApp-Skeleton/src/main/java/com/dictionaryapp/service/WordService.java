package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class WordService {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final LanguageService languageService;
    private final WordRepository wordRepository;

    public WordService(UserService userService, CurrentUser currentUser, LanguageService languageService, WordRepository wordRepository) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.languageService = languageService;
        this.wordRepository = wordRepository;
    }

    public void add(AddWordDTO data) {

        Word word = new Word();

        word.setTerm(data.getTerm());
        word.setTranslation(data.getTranslation());
        word.setExample(data.getExample());
        word.setInputDate(data.getInputDate());
        Language language = languageService.findLanguage(data.getLanguage());
        word.setLanguage(language);

        final User addedBy = userService.findByUsername(currentUser.getUsername());
        word.setAddedBy(addedBy);

        wordRepository.save(word);
    }

    public int findAllWordsCount() {

        return this.wordRepository
                .findAll()
                .size();
    }

    public void removeWord(Long id) {

        this.wordRepository.deleteById(id);
    }

    public void removeAllWords() {

        this.wordRepository.deleteAll();
    }
}
