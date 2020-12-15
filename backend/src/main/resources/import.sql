INSERT INTO admin (username, password, role) VALUES ('admin', 'admin', 'admin');
INSERT INTO user (id, name, email, password, role) VALUES (1, 'Demo User', 'demo@xerosoft.net', 'demo', 'user');

INSERT INTO contact (id, user_id, name, phone, email) VALUES (10, 1, 'Jenny Doe', '08133340884', 'jenny@xerosoft.net');