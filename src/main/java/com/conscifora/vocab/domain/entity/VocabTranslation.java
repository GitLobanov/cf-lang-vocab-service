package com.conscifora.vocab.domain.entity;

import com.conscifora.vocab.domain.constant.TranslationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "vocab_translation")
@NoArgsConstructor
@AllArgsConstructor
public class VocabTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TranslationType translationType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vocab_source_id")
    private Vocab vocabSource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vocab_target_id")
    private Vocab vocabTarget;

}