INSERT INTO USERS VALUES (1, 1, 'test@test.test', true, false, false, '$2y$12$8FQh44IZr4zSUoslsL0e/Osy7/eeYhcPGat00UtFvmbjom4MztDrO', 'admin');
INSERT INTO USERS VALUES (2, 1, 'test@test.test', true, false, false, '$2y$12$8FQh44IZr4zSUoslsL0e/Osy7/eeYhcPGat00UtFvmbjom4MztDrO', 'buy');
INSERT INTO USERS VALUES (3, 1, 'test@test.test', true, false, false, '$2y$12$8FQh44IZr4zSUoslsL0e/Osy7/eeYhcPGat00UtFvmbjom4MztDrO', 'sell');
INSERT INTO ROLES VALUES (1, 'admin');
INSERT INTO ROLES VALUES (2, 'buy');
INSERT INTO ROLES VALUES (3, 'sell');
INSERT INTO USER_ROLE VALUES (1, 1);
INSERT INTO USER_ROLE VALUES (2, 2);
INSERT INTO USER_ROLE VALUES (3, 3);