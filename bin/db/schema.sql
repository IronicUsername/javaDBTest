CREATE TABLE IF NOT EXISTS question_types(
    id int NOT NULL AUTO_INCREMENT,
    type VARCHAR(100),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS questions(
    id int NOT NULL AUTO_INCREMENT,
    qestion VARCHAR(255),
    solution VARCHAR(1),
    weight int,
    q_type int NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (q_type) REFERENCES question_types(id)
);
