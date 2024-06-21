package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findByLanguageAndAddedBy(Language language, User addedBy);

    Optional<Word> findByIdAndAddedBy(Long id, User user);

    @Query("SELECT COUNT(w) FROM Word w")
    int countAllWords();

    @Query("SELECT COUNT(w) FROM Word w WHERE w.addedBy.username = :username")
    int countWordsByUser(@Param("username") String username);
}
