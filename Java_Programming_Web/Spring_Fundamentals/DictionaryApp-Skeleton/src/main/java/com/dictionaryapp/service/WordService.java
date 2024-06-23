package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final LanguageService languageService;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;

    public WordService(UserService userService, CurrentUser currentUser, LanguageService languageService, WordRepository wordRepository, UserRepository userRepository, LanguageRepository languageRepository) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.languageService = languageService;
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
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

    public List<Word> findWordByLanguageAndUser(LanguageNameEnum languageNameEnum) {
        Optional<User> user = userRepository.findById(currentUser.getId());

        if (user.isEmpty()) {
            return new ArrayList<>();
        }

        Language language = languageRepository.findByName(languageNameEnum);

        return wordRepository.findByLanguageAndAddedBy(language, user.get());
    }


    public int findAllWordsCount() {
        /*return this.wordRepository
                .findAll()
                .size();*/

        return wordRepository.countWordsByUser(currentUser.getUsername());
    }

    public void removeWord(Long id) {
        userRepository.findById(currentUser.getId())
                .flatMap(user -> wordRepository.findByIdAndAddedBy(id, user))
                .ifPresent(wordRepository::delete);
    }

    public void removeAllWords() {
        userRepository.findById(currentUser.getId())
                .map(wordRepository::findAllByAddedBy)
                .ifPresent(wordRepository::deleteAll);
    }
}
