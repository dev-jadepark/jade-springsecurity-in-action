INSERT IGNORE INTO user(id, username, password, algorithm) VALUES(1, 'john', '$2a$12$HaR3oyPDF20v8r0Qdk4O8.iYdr3wwBTqZdEJtG8lWNb6hAuCR2Da2', 'BCRYPT');
INSERT IGNORE INTO authority(id, name, user) VALUES(1, 'READ', 1);
INSERT IGNORE INTO authority(id, name, user) VALUES(2, 'WRITE', 1);
INSERT IGNORE INTO product(id, name, price, currency) VALUES(1, 'Chocolate', '10', 'USD');