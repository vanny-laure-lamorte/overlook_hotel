DROP DATABASE overlookhotel;

CREATE DATABASE hotel;
USE hotel;

-- Drop tables in the correct order
DROP TABLE IF EXISTS feedbacks CASCADE;
DROP TABLE IF EXISTS notifications CASCADE;
DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS hotel_users CASCADE;
DROP TABLE IF EXISTS "roles" CASCADE;

-- Drop types
DROP TYPE IF EXISTS room_type CASCADE;
DROP TYPE IF EXISTS state_reservation CASCADE;


-- Role
CREATE TABLE IF NOT EXISTS "roles" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO "roles" (name) VALUES
('customer'),
('employee'),
('admin');

-- Hotel user
CREATE TABLE IF NOT EXISTS hotel_user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    dob DATE,
    user_address VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    user_password VARCHAR(255),
    role_id INT,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES "role"(id)
);

INSERT INTO hotel_user (first_name, last_name, dob, user_address, phone_number, email, user_password, role_id) VALUES
('Prenom', 'Nom', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'c', 'c', 1),
('Prenom', 'Nom', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'e', 'e', 2),
('Prenom', 'Nom', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'a', 'a', 3),
('Charlie', 'Dupont', '1982-03-30', '3 Avenue des Tuileries, Lyon', '0601020304', 'charlie.dupont@example.com', 'password123', 1),
('Diana', 'Lemoine', '1995-09-10', '4 Rue de la Paix, Marseille', '0712345678', 'diana.lemoine@example.com', 'password123', 1),
('Eve', 'Blanchard', '1987-11-05', '5 Place de la Liberté, Toulouse', '0812345678', 'eve.blanchard@example.com', 'password123', 1);

INSERT INTO hotel_user (first_name, last_name, dob, user_address, phone_number, email, user_password, role_id) VALUES
('Frank', 'Meyer', '1992-04-12', '6 Rue de l Église, Lille', '0612345679', 'frank.meyer@example.com', 'password123', 2),
('Gina', 'Petit', '1988-07-08', '7 Place du Marché, Nice', '0623456789', 'gina.petit@example.com', 'password123', 2),
('Hugo', 'Bernard', '1993-01-22', '8 Rue de la Gare, Bordeaux', '0687654321', 'hugo.bernard@example.com', 'password123', 2);

INSERT INTO hotel_user (first_name, last_name, dob, user_address, phone_number, email, user_password, role_id) VALUES
('Lucas', 'Martinie', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'alice.durand@example.com', 'password123', 3),
('Vanny', 'Lamorte', '1993-12-25', '2 Boulevard de la République, Paris', '0987654321', 'bob.martin@example.com', 'password123', 3);

-- room_type ENUM type for rooms
CREATE TYPE room_type AS ENUM ('Room', 'Meeting', 'Spa');

-- Rooms
CREATE TABLE IF NOT EXISTS room (
    id SERIAL PRIMARY KEY,
    type room_type,
    price INT,
    capacity INT,
    description TEXT
);

INSERT INTO room (type, price, capacity, description) VALUES
('Room', 120, 2, 'Standard room with a queen-sized bed'),
('Room', 150, 2, 'Superior room with a king-sized bed and a sea view'),
('Room', 100, 1, 'Basic room with a single bed'),
('Room', 130, 3, 'Family room with a queen-sized bed and a bunk bed'),
('Room', 180, 2, 'Deluxe room with a king-sized bed and balcony'),
('Room', 110, 1, 'Cozy room with a single bed and city view');

-- Meeting rooms
INSERT INTO room (type, price, capacity, description) VALUES
('Meeting', 250, 20, 'Conference room with projector and seating for 20 people'),
('Meeting', 300, 30, 'Large meeting room with a video conferencing system');

-- Spa rooms
INSERT INTO room (type, price, capacity, description) VALUES
('Spa', 200, 2, 'Luxury spa room with a private jacuzzi'),
('Spa', 220, 2, 'Exclusive spa room with sauna and massage table');

-- state_reservation ENUM type for booking status
CREATE TYPE state_reservation AS ENUM ('PENDING', 'ACCEPTED', 'DECLINED', 'CANCELLED', 'COMPLETED');

-- Bookings
CREATE TABLE IF NOT EXISTS booking (
    id SERIAL PRIMARY KEY,
    user_id INT,
    room_id INT,
    arriving_date DATE,
    departure_date DATE,
    booking_status state_reservation DEFAULT 'PENDING',
    confirmation_number INT,
    guests INT,
    bill INT,
    FOREIGN KEY (user_id) REFERENCES hotel_users(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);

INSERT INTO booking (user_id, room_id, arriving_date, departure_date, booking_status, confirmation_number, guests, bill) VALUES
(1, 1, '2025-06-10', '2025-06-12', 'PENDING', 1001, 2, 200),
(2, 2, '2025-06-15', '2025-06-16', 'ACCEPTED', 1002, 10, 2500),
(3, 3, '2025-06-20', '2025-06-22', 'PENDING', 1003, 4, 600),
(4, 4, '2025-06-05', '2025-06-07', 'DECLINED', 1004, 2, 240),
(5, 5, '2025-06-25', '2025-06-28', 'PENDING', 1005, 2, 360),
(6, 6, '2025-06-30', '2025-07-02', 'ACCEPTED', 1006, 2, 400);

-- Feedback
CREATE TABLE IF NOT EXISTS feedback (
    id SERIAL PRIMARY KEY,
    user_id INT,
    booking_id INT,
    rating INT,
    user_comment TEXT,
    response TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES hotel_users(id),
    FOREIGN KEY (booking_id) REFERENCES booking(id)
);

INSERT INTO feedback (user_id, booking_id, rating, user_comment, response, created_at) VALUES
(1, 1, 4, 'Great room, very clean and comfortable.', 'Thank you for your feedback!', '2025-06-12 10:00:00'),
(2, 2, 5, 'Perfect for our team meeting, very spacious.', 'We are glad to hear it was helpful!', '2025-06-16 15:00:00'),
(3, 3, 3, 'Room was decent, but a bit too small for 4 people.', 'We will work on improving space.', '2025-06-22 12:00:00'),
(4, 4, 2, 'Room was not available when we arrived, very disappointing.', 'We apologize for the inconvenience.', '2025-06-07 09:00:00'),
(5, 5, 4, 'Nice room, a bit more attention to detail would make it perfect.', 'We appreciate the feedback!', '2025-06-28 14:00:00');

-- Notification
CREATE TABLE IF NOT EXISTS notification (
    id SERIAL PRIMARY KEY,
    user_id INT,
    user_message TEXT,
    created_at DATE,
    FOREIGN KEY (user_id) REFERENCES hotel_users(id)
);

INSERT INTO notification (user_id, user_message, created_at) VALUES
(1, 'Your booking confirmation is pending. Please check back later.', '2025-06-10'),
(2, 'Your booking has been confirmed. We look forward to seeing you!', '2025-06-15'),
(3, 'Your booking has been accepted. Please enjoy your stay!', '2025-06-20'),
(4, 'Your booking was declined. We apologize for any inconvenience.', '2025-06-05'),
(5, 'Your booking confirmation is pending. Please check back later.', '2025-06-25'),
(6, 'Your booking has been confirmed. Looking forward to your visit!', '2025-06-30');
