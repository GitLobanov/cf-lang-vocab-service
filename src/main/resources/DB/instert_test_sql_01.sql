-- Вставка данных в таблицу cv_schema.vocab
INSERT INTO cv_schema.vocab (id, language_code, word)
VALUES (1, 'EN', 'Apple'),
       (2, 'RU', 'Яблоко'),
       (3, 'JP', 'リンゴ'),
       (4, 'ZH', '苹果'),
       (5, 'DE', 'Apfel'),
       (6, 'KO', '사과'),
       (7, 'ES', 'Manzana'),
       (8, 'EN', 'Banana'),
       (9, 'RU', 'Банан'),
       (10, 'JP', 'バナナ'),
       (11, 'ZH', '香蕉'),
       (12, 'DE', 'Banane'),
       (13, 'KO', '바나나'),
       (14, 'ES', 'Banana');

-- Вставка данных в таблицу cv_schema.vocab_defintions
INSERT INTO cv_schema.vocab_defintions (id_vocab_defintions, vocab_id, definition)
VALUES (1, 1, 'A round fruit with red or green skin and a crisp flesh.'),
       (2, 2, 'Круглый фрукт с красной или зеленой кожурой и хрустящей мякотью.'),
       (3, 3, '赤または緑の皮とシャキッとした果肉を持つ丸い果物。'),
       (4, 4, '一种具有红色或绿色皮肤和脆肉的圆形水果。'),
       (5, 5, 'Eine runde Frucht mit roter oder grüner Schale und knackigem Fruchtfleisch.'),
       (6, 6, '빨간색 또는 초록색 껍질과 아삭한 과육을 가진 둥근 과일입니다.'),
       (7, 7, 'Una fruta redonda con piel roja o verde y pulpa crujiente.');

-- Вставка данных в таблицу cv_schema.vocab_examples
INSERT INTO cv_schema.vocab_examples (id, text)
VALUES (1, 'An apple a day keeps the doctor away.'),
       (2, 'Яблоко в день — и врач не нужен.'),
       (3, 'リンゴを毎日食べれば、医者はいらない。'),
       (4, '每天吃一个苹果，医生远离我。'),
       (5, 'Ein Apfel am Tag hält den Arzt fern.'),
       (6, '하루에 사과 하나면 의사가 필요 없다.'),
       (7, 'Una manzana al día aleja al médico.');

-- Вставка данных в таблицу cv_schema.vocab_examples_vocabs
INSERT INTO cv_schema.vocab_examples_vocabs (vocab_examples_id, vocabs_id)
VALUES (1, 1), -- Example of Apple
       (1, 2), -- Example of Яблоко
       (1, 3), -- Example of リンゴ
       (1, 4), -- Example of 苹果
       (2, 5), -- Example of Apfel
       (2, 6), -- Example of 사과
       (2, 7);
-- Example of Manzana

-- Вставка данных в таблицу cv_schema.vocab_translation
INSERT INTO cv_schema.vocab_translation (id, vocab_source_id, vocab_target_id, translation_type)
VALUES (1, 1, 2, 'DIRECT'),   -- Apple - Яблоко
       (2, 1, 3, 'DIRECT'),   -- Apple - リンゴ
       (3, 1, 4, 'DIRECT'),   -- Apple - 苹果
       (4, 1, 5, 'DIRECT'),   -- Apple - Apfel
       (5, 1, 6, 'DIRECT'),   -- Apple - 사과
       (6, 1, 7, 'DIRECT'),   -- Apple - Manzana
       (7, 8, 9, 'DIRECT'),   -- Banana - Банан
       (8, 8, 10, 'DIRECT'),  -- Banana - バナナ
       (9, 8, 11, 'DIRECT'),  -- Banana - 香蕉
       (10, 8, 12, 'DIRECT'), -- Banana - Banane
       (11, 8, 13, 'DIRECT'), -- Banana - 바나나
       (12, 8, 14, 'DIRECT');
-- Banana - Banana

-- Вставка данных в таблицу cv_schema.vocab_translation_source_vocab
INSERT INTO cv_schema.vocab_translation_source_vocab (vocab_translation_id, source_vocab_id)
VALUES (1, 1),  -- Source for Apple
       (2, 1),  -- Source for Apple
       (3, 1),  -- Source for Apple
       (4, 1),  -- Source for Apple
       (5, 1),  -- Source for Apple
       (6, 1),  -- Source for Apple
       (7, 8),  -- Source for Banana
       (8, 8),  -- Source for Banana
       (9, 8),  -- Source for Banana
       (10, 8), -- Source for Banana
       (11, 8), -- Source for Banana
       (12, 8);
-- Source for Banana

-- Вставка данных в таблицу cv_schema.vocab_translation_vocab_target
INSERT INTO cv_schema.vocab_translation_vocab_target (vocab_translation_id, vocab_target_id)
VALUES (1, 2),   -- Translation target for Apple to Яблоко
       (2, 3),   -- Translation target for Apple to リンゴ
       (3, 4),   -- Translation target for Apple to 苹果
       (4, 5),   -- Translation target for Apple to Apfel
       (5, 6),   -- Translation target for Apple to 사과
       (6, 7),   -- Translation target for Apple to Manzana
       (7, 9),   -- Translation target for Banana to Банан
       (8, 10),  -- Translation target for Banana to バナナ
       (9, 11),  -- Translation target for Banana to 香蕉
       (10, 12), -- Translation target for Banana to Banane
       (11, 13), -- Translation target for Banana to 바나나
       (12, 14); -- Translation target for Banana to Banana
