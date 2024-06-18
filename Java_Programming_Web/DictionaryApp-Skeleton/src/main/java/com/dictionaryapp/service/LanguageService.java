package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import com.dictionaryapp.repo.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    public LanguageService(LanguageRepository languageRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    public Language findLanguage(LanguageNameEnum language) {
        final LanguageNameEnum lang = LanguageNameEnum.valueOf(language.name());
        return languageRepository.findByName(lang).orElse(null);
    }

    public List<Word> findAllWordsByLanguage(String language) {

        Language foundLanguage = this.languageRepository
                .findByName(LanguageNameEnum.valueOf(language))
                .orElseThrow(() -> new RuntimeException("Language not found"));

        return foundLanguage.getWords()
                .stream()
                .sorted(Comparator.comparing(Word::getInputDate))
                .map(w -> modelMapper.map(w, Word.class))
                .toList();
    }

}



