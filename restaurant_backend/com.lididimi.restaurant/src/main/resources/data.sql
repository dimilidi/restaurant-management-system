INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO users (name, contact_number, email, password, status) VALUES ('John Doe', '1234567890', 'john@example.com', '$2a$10$BOraonjZ5IyehbATzupffO3ggFHJJZ8W2i6LvNolHGa.ikU7ICyTe', 'ACTIVE');
INSERT INTO users (name, contact_number, email, password, status) VALUES ('Tim Funk', '1234567890', 'tim@example.com', '$2a$10$BOraonjZ5IyehbATzupffO3ggFHJJZ8W2i6LvNolHGa.ikU7ICyTe', 'ACTIVE');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO products (name, category_id, description, price, status) VALUES ('Cheese Cake', 1, 'Description for Cheese Cake', 10.00, 'ACTIVE');
INSERT INTO products (name, category_id, description, price, status) VALUES ('Classic Burger', 2, 'Description for Classic burger', 20.00, 'ACTIVE');

INSERT INTO bills (uuid, name, email, contact_number, payment_method, total, product_details, created_by, created_date)
VALUES (
           'BILL-685405244096951',
           'John Doe',
           'some@example.com',
           '1234567890',
           'CASH',
           30.00,
           '[{\"id\": 1, \"name\": \"Cheese Cake\", \"price\": 10.00, \"total\": 20.00, \"category\": \"Cake\", \"quantity\": \"2\"}]',
           'john@example.com',
           NOW()
       );





