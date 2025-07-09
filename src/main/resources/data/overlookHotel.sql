-- DROP DATABASE overlookhotel;
-- CREATE DATABASE hotel;
-- USE hotel;

-- Drop tables in the correct order
DROP TABLE IF EXISTS feedback CASCADE;
DROP TABLE IF EXISTS user_notification CASCADE;
DROP TABLE IF EXISTS booking CASCADE;
DROP TABLE IF EXISTS room CASCADE;
DROP TABLE IF EXISTS hotel_user CASCADE;
DROP TABLE IF EXISTS "role" CASCADE;

-- Drop types
DROP TYPE IF EXISTS room_type CASCADE;
DROP TYPE IF EXISTS state_reservation CASCADE;


-- Role
CREATE TABLE IF NOT EXISTS "role" (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(255)
);

INSERT INTO "role" (role_name) VALUES
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

-- Admins
INSERT INTO hotel_user (first_name, last_name, dob, user_address, phone_number, email, user_password, role_id) VALUES
('Admin', 'AdminLN', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'a', 'a', 3),
('Test', 'TestLN', '1995-12-22', '1 Rue des Champs, Paris', '0123456789', 't', 't', 3),
('Lucas', 'Martinie', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'alice.durand@example.com', 'password123', 3),
('Vanny', 'Lamorte', '1993-12-25', '2 Boulevard de la Republique, Paris', '0987654321', 'bob.martin@example.com', 'password123', 3);
-- Employees
INSERT INTO hotel_user (first_name, last_name, dob, user_address, phone_number, email, user_password, role_id) VALUES
('Employee', 'EmployeeLN', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'e', 'e', 2),
('Frank', 'Meyer', '1992-04-12', '6 Rue de l eglise, Lille', '0612345679', 'frank.meyer@example.com', 'password123', 2),
('Gina', 'Petit', '1988-07-08', '7 Place du Marche, Nice', '0623456789', 'gina.petit@example.com', 'password123', 2),
('Hugo', 'Bernard', '1993-01-22', '8 Rue de la Gare, Bordeaux', '0687654321', 'hugo.bernard@example.com', 'password123', 2);
-- Customers
INSERT INTO hotel_user (first_name, last_name, dob, user_address, phone_number, email, user_password, role_id) VALUES
('Customer', 'CustomerLastName', '1995-12-24', '1 Rue des Champs, Paris', '0123456789', 'c', 'c', 1),
('Charlie', 'Dupont', '1982-03-30', '3 Avenue des Tuileries, Lyon', '0601020304', 'charlie.dupont@example.com', 'password123', 1),
('Diana', 'Lemoine', '1995-09-10', '4 Rue de la Paix, Marseille', '0712345678', 'diana.lemoine@example.com', 'password123', 1),
('Eve', 'Blanchard', '1987-11-05', '5 Place de la Liberte, Toulouse', '0812345678', 'eve.blanchard@example.com', 'password123', 1),
('Alice', 'Martin', '1990-04-12', '10 Rue de la Republique, Paris', '0611223344', 'alice.martin@example.com', 'password123', 1),
('Benoît', 'Girard', '1985-08-19', '21 Boulevard Victor Hugo, Nice', '0622334455', 'benoit.girard@example.com', 'password123', 1),
('Claire', 'Perrot', '1992-02-28', '33 Avenue Jean Jaures, Lille', '0633445566', 'claire.perrot@example.com', 'password123', 1),
('Damien', 'Roux', '1988-06-17', '5 Rue Nationale, Nantes', '0644556677', 'damien.roux@example.com', 'password123', 1),
('Elodie', 'Marchand', '1993-10-09', '8 Place Bellecour, Lyon', '0655667788', 'elodie.marchand@example.com', 'password123', 1),
('François', 'Leclerc', '1980-12-03', '14 Rue des Remparts, Bordeaux', '0666778899', 'francois.leclerc@example.com', 'password123', 1),
('Gabrielle', 'Picard', '1996-07-25', '2 Allee des Lilas, Rennes', '0677889900', 'gabrielle.picard@example.com', 'password123', 1),
('Hugo', 'Meyer', '1983-11-14', '18 Avenue de Strasbourg, Strasbourg', '0688990011', 'hugo.meyer@example.com', 'password123', 1),
('Isabelle', 'Renard', '1991-05-05', '7 Rue Saint-Michel, Montpellier', '0699001122', 'isabelle.renard@example.com', 'password123', 1),
('Julien', 'Barbier', '1986-09-21', '12 Boulevard Haussmann, Paris', '0600112233', 'julien.barbier@example.com', 'password123', 1);

-- room_type ENUM type for rooms
CREATE TYPE ROOM_TYPE AS ENUM ('Room', 'Meeting', 'Spa');

-- Room
CREATE TABLE IF NOT EXISTS room (
    id SERIAL PRIMARY KEY,
    accommodation_type ROOM_TYPE,
    price INT,
    capacity INT,
    room_description TEXT,
    amenities TEXT,
    room_title VARCHAR(255) DEFAULT 'Room',
    room_image VARCHAR(255) DEFAULT 'room.jpg'
);

INSERT INTO room (accommodation_type, price, capacity, room_description, amenities, room_title, room_image) VALUES
('Room', 250, 2, 'A cozy standard room featuring a comfortable queen-sized bed for restful nights. Perfect for couples or solo travelers seeking a relaxing stay.  Enjoy modern comforts in a bright and welcoming atmosphere.', 'Wi-Fi, Air conditioning, Private bathroom, TV', 'Standard Queen Room', 'standard-room-'),
('Room', 390, 2, 'Our superior room offers a spacious layout with a luxurious king-sized bed.  Take in the breathtaking sea view from your private balcony. Ideal for guests looking for elegance, comfort, and serenity.', 'Wi-Fi, King bed, Sea view, Balcony, Mini-bar, Flat-screen TV', 'Superior Sea View', 'superior-room-'),
('Room', 210, 1, 'Simple yet functional, this basic room includes a single bed for solo travelers. Perfect for short stays or business trips on a budget.', 'Wi-Fi, Single bed, Shared bathroom, Desk', 'Basic Single Room', 'basic-room-'),
('Room', 320, 6, 'Designed for families, this spacious room includes a queen-sized bed and bunk beds. Fun, safe, and functional – perfect for parents traveling with kids. All the essentials plus thoughtful touches for a family-friendly stay.', 'Wi-Fi, Family bed setup, Private bathroom, TV, Kids-friendly', 'Family Room', 'family-room-'),
('Room', 450, 2, 'Experience luxury in our deluxe room with a plush king-sized bed and private balcony. Unwind with a drink from the mini-bar or soak in the elegant bath tub. Ideal for romantic getaways or special occasions.', 'Wi-Fi, King bed, Balcony, Mini-bar, Bath tub, Coffee maker', 'Deluxe King Room', 'deluxe-room-'),
('Room', 240, 1, 'A charming single room offering a stunning city view from your window. Equipped with a cozy bed and warm atmosphere to help you relax. Perfect for solo travelers looking for comfort and peace.', 'Wi-Fi, City view, Single bed, Private bathroom, Heating', 'Cozy City Room', 'cosy-room-');

-- Meeting rooms
INSERT INTO room (accommodation_type, price, capacity, room_description, room_title, room_image) VALUES
('Meeting', 250, 20, 'Conference room with projector and seating for 20 people', 'Conference Room', 'conference.jpg'),
('Meeting', 300, 30, 'Large meeting room with a video conferencing system', 'Large Meeting Room', 'meeting_large.jpg');

-- Spa
INSERT INTO room (accommodation_type, price, capacity, room_description, room_title, room_image) VALUES
('Spa', 200, 2, 'Luxury spa room with a private jacuzzi', 'Spa with Jacuzzi', 'spa1.jpg'),
('Spa', 220, 2, 'Exclusive spa room with sauna and massage table', 'Spa Sauna & Massage', 'spa2.jpg');

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
    adults INT,
    children INT,
    bill INT,
    FOREIGN KEY (user_id) REFERENCES hotel_user(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
);

INSERT INTO booking (user_id, room_id, arriving_date, departure_date, booking_status, confirmation_number, adults, children, bill) VALUES
(11, 1, '2025-06-10', '2025-06-12', 'PENDING', 1001, 2, 1, 200),
(12, 2, '2025-06-15', '2025-06-16', 'ACCEPTED', 1002, 10, 2, 2500),
(15, 3, '2025-06-20', '2025-06-22', 'PENDING', 1003, 4, 0, 600),
(10, 4, '2025-06-05', '2025-06-07', 'DECLINED', 1004, 2, 0, 240),
(13, 5, '2025-06-25', '2025-06-28', 'PENDING', 1005, 2, 0, 360),
(16, 6, '2025-06-30', '2025-07-02', 'ACCEPTED', 1006, 2, 3, 400);

-- Feedback
CREATE TABLE IF NOT EXISTS feedback (
    id SERIAL PRIMARY KEY,
    user_id INT,
    booking_id INT,
    rating INT,
    user_comment TEXT,
    response TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES hotel_user(id),
    FOREIGN KEY (booking_id) REFERENCES booking(id)
);

INSERT INTO feedback (user_id, booking_id, rating, user_comment, response, created_at) VALUES
(10, 1, 4, 'Great room, very clean and comfortable.', 'Thank you for your feedback!', '2025-06-12 10:00:00'),
(9, 2, 5, 'Perfect for our team meeting, very spacious.', 'We are glad to hear it was helpful!', '2025-06-16 15:00:00'),
(15, 3, 3, 'Room was decent, but a bit too small for 4 people.', 'We will work on improving space.', '2025-06-22 12:00:00'),
(12, 4, 2, 'Room was not available when we arrived, very disappointing.', 'We apologize for the inconvenience.', '2025-06-07 09:00:00'),
(7, 5, 4, 'Nice room, a bit more attention to detail would make it perfect.', 'We appreciate the feedback!', '2025-06-28 14:00:00');

-- Notification
CREATE TABLE IF NOT EXISTS user_notification (
    id SERIAL PRIMARY KEY,
    user_id INT,
    user_message TEXT,
    created_at DATE,
    FOREIGN KEY (user_id) REFERENCES hotel_user(id)
);

INSERT INTO user_notification (user_id, user_message, created_at) VALUES
(1, 'Your booking confirmation is pending. Please check back later.', '2025-06-10'),
(2, 'Your booking has been confirmed. We look forward to seeing you!', '2025-06-15'),
(3, 'Your booking has been accepted. Please enjoy your stay!', '2025-06-20'),
(4, 'Your booking was declined. We apologize for any inconvenience.', '2025-06-05'),
(5, 'Your booking confirmation is pending. Please check back later.', '2025-06-25'),
(6, 'Your booking has been confirmed. Looking forward to your visit!', '2025-06-30');
