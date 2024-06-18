package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AddWordDTO {

    @NotBlank(message = "Term must not be empty!")
    @Size(min = 2, max = 40, message = "Term must be between 2 and 40 characters!")
    private String term;

    @NotBlank(message = "Translation must not be empty!")
    @Size(min = 2, max = 80, message = "Translation must be between 2 and 80 characters!")
    private String translation;

    @NotBlank(message = "Example must not be empty!")
    @Size(min = 2, max = 200, message = "Example must be between 2 and 200 characters!")
    private String example;

    @PastOrPresent(message = "The input date must be in the past or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputDate;

    @NotNull(message = "Language must be selected!")
    private LanguageNameEnum language;


    public AddWordDTO() {}

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageNameEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageNameEnum language) {
        this.language = language;
    }


}
