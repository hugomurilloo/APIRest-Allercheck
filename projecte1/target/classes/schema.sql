

DROP TABLE IF EXISTS restaurant;

CREATE TABLE restaurant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    rating DOUBLE,
    is_verified BOOLEAN,
    sense_gluten BOOLEAN,
    sense_lactosa BOOLEAN,
    sense_fruits_secs BOOLEAN,
    sense_marisc BOOLEAN,
    es_vega BOOLEAN,
    es_halal BOOLEAN,
    es_kosher BOOLEAN,
    te_ou BOOLEAN,
    tipus_cuina VARCHAR(255),
    rang_preu VARCHAR(50),
    ubicacio VARCHAR(255),
    telefon INT,
    data_created DATETIME,
    data_updated DATETIME
);