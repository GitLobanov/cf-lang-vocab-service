package com.conscifora.vocab.domain.entity;

import com.conscifora.vocab.domain.constant.LanguageCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "vocab")
@NoArgsConstructor
@AllArgsConstructor
public class Vocab {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private LanguageCode languageCode;

    @Column(unique = true)
    private String word;

    @ManyToMany(mappedBy = "vocabs")
    private Set<VocabExamples> vocabExamples = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vocab", orphanRemoval = true)
    private Set<VocabDefinitions> vocabDefinitions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vocabSource", orphanRemoval = true)
    private Set<VocabTranslation> vocabTranslationsSource = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vocabTarget", orphanRemoval = true)
    private Set<VocabTranslation> vocabTranslationsTarget = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Vocab vocab = (Vocab) object;
        return getId() != null && Objects.equals(getId(), vocab.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}