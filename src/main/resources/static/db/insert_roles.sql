INSERT INTO `role` VALUES (1,'ADMIN');      -- Admin      - 1 - Kierownik
INSERT INTO `role` VALUES (2,'RECORDER');   -- Recorder   - 2 - Rejestrator
INSERT INTO `role` VALUES (3,'LAWYER');     -- Lawyer     - 3 - Adwokat
INSERT INTO `role` VALUES (4,'ACCOUNTANT'); -- Accountant - 4 - Ksiegowa

-- TEST USERS with ROLE --
-- Password for users: 12345 --
INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (7, '1', 'joe@gmail.com', 'Smith', 'Joe', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('7', '1');

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (8, '1', 'bob@gmail.com', 'Smith', 'Bob', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('8', '2');

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES (9, '1', 'Stephen@gmail.com', 'Smith', 'Stephen', '$2a$10$TNilSEl6ePjGf1K9a5q4NuNB.vkaPzcP/XBRY.zwrnfNPMfF9KxAK');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('9', '3');