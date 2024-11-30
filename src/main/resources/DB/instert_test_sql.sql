-- Insert into vocab table
INSERT INTO cv_schema.vocab (id, language_code, word)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'EN', 'hello'),
    ('22222222-2222-2222-2222-222222222222', 'RU', 'привет'),
    ('33333333-3333-3333-3333-333333333333', 'JP', 'こんにちは');

-- Insert into vocab_defintions table
INSERT INTO cv_schema.vocab_defintions (id, definition, vocab_id)
VALUES
    ('44444444-4444-4444-4444-444444444444', 'A greeting in English.', '11111111-1111-1111-1111-111111111111'),
    ('55555555-5555-5555-5555-555555555555', 'A greeting in Russian.', '22222222-2222-2222-2222-222222222222'),
    ('66666666-6666-6666-6666-666666666666', 'A greeting in Japanese.', '33333333-3333-3333-3333-333333333333');

-- Insert into vocab_examples table
INSERT INTO cv_schema.vocab_examples (id, text)
VALUES
    ('77777777-7777-7777-7777-777777777777', 'Hello, how are you?'),
    ('88888888-8888-8888-8888-888888888888', 'Привет, как дела?'),
    ('99999999-9999-9999-9999-999999999999', 'こんにちは、お元気ですか？');

-- Insert into vocab_examples_vocabs table
INSERT INTO cv_schema.vocab_examples_vocabs (vocab_examples_id, vocabs_id)
VALUES
    ('77777777-7777-7777-7777-777777777777', '11111111-1111-1111-1111-111111111111'),
    ('88888888-8888-8888-8888-888888888888', '22222222-2222-2222-2222-222222222222'),
    ('99999999-9999-9999-9999-999999999999', '33333333-3333-3333-3333-333333333333');

-- Insert into vocab_translation table
INSERT INTO cv_schema.vocab_translation (id, translation_type, vocab_source_id, vocab_target_id)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'DIRECT', '11111111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222222'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'DIRECT', '11111111-1111-1111-1111-111111111111', '33333333-3333-3333-3333-333333333333'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'DIRECT', '22222222-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333333');
