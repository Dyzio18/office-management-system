INSERT INTO `role` VALUES (1,'ADMIN');      -- Admin      - 1 - Kierownik
INSERT INTO `role` VALUES (2,'RECORDER');   -- Recorder   - 2 - Rejestrator
INSERT INTO `role` VALUES (3,'LAWYER');     -- Lawyer     - 3 - Adwokat
INSERT INTO `role` VALUES (4,'ACCOUNTANT'); -- Accountant - 4 - Ksiegowa

-- TEST USERS with ROLE --
-- Password for users: 12345 --
INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (1, '1', 'admin@gmail.com', 'Admin', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (2, '1', 'rejestrator@gmail.com', 'Rejestrator', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('2', '2');

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (3, '1', 'adwokat@gmail.com', 'Adwokat', 'Pan', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('3', '3');

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (4, '1', 'ksiegowa@gmail.com', 'Ksiegowa', 'Pani', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('4', '4');

INSERT INTO `service` (`service_id`, `service_name`, `service_price`, service_inner_price) VALUES (1,"Konsultacja", 150.50, 45.12);

INSERT INTO `case` (`case_id`, `client_name`, `client_surname`, `case_date`, `case_time`, `case_type`, `case_lawyer`, `case_note`, `case_price`) VALUES (NULL, 'Marian', 'Kowalski', '11-11-2018', '11:00', 'kryminalna', '7', 'Prosba o adwokata ws. marszu niepodleglosci', '500');
INSERT INTO `case` (`case_id`, `client_name`, `client_surname`, `case_date`, `case_time`, `case_type`, `case_lawyer`, `case_note`, `case_price`) VALUES (NULL, 'Ryszard', 'Kalisz', '12-06-2018', '16:00', 'konsultacje', '7', 'Pan prosi o porade prawną w sprawie konstytucji', '100');

INSERT INTO `client` (`client_id`, `client_name`, `client_surname`, `client_phone`) VALUES (NULL, 'Adam', 'Kowalski', '788996411'), (NULL, 'Beata', 'Tyszkiewicz', '964789125');