package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enumaration.LanguageNameEnum;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name =  "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LanguageNameEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private Set<Word> words;

    public Language() {
        words = new HashSet<>();
    }

    public Language(LanguageNameEnum name, String description) {
        super();

        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageNameEnum getName() {
        return name;
    }

    public void setName(LanguageNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
