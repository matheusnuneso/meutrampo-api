INSERT INTO TB_PERSON VALUES (1 ,'12345678900', 'wallace@gmail.com', 'Wallace Junior', '123', 'wallaceJr'); -- id, cpf, email, full_name, password, user_name
INSERT INTO TB_PERSON VALUES (2 ,'00123456789', 'matheus@gmail.com', 'Matheus Sobrinho', 'portao', 'MatSobrinho');
INSERT INTO TB_PERSON VALUES (3 ,'00123456789', 'junin@gmail.com', 'Alexandro', 'agua123', 'TheLittleJunior');
INSERT INTO TB_JOB VALUES (1, 2, 250.90, 'Mecanico'); -- id, id_person, price, title
INSERT INTO TB_JOB VALUES (2, 2, 150.99, 'Eletricista Automotivo');
INSERT INTO TB_JOB VALUES (3, 3, 50.47 , 'Tecnico de Infomatica');
INSERT INTO TB_JOB VALUES (4, 3, 30.25 , 'Borracheiro');
INSERT INTO TB_JOB VALUES (5, 1, 40.99 , 'Fazedor de Gambiarra');
INSERT INTO TB_JOB VALUES (6, 1, 99.90 , 'Especialista em Embromation');
INSERT INTO TB_JOB VALUES (7, 1, 299.90, 'Encanador');
INSERT INTO TB_JOB VALUES (8, 2, 450.90, 'Pintura');
INSERT INTO TB_JOB VALUES (9, 3, 47.90 , 'Vou te Enrolar');
INSERT INTO TB_JOB_SIGNED VALUES (1, '2022-12-21', 250.90, 1, 1, 3, '2022-12-24'); -- id , contract_date, final_price, id_client, id_job, id_person, job_date
INSERT INTO TB_JOB_SIGNED VALUES (2, '2022-12-24', 50.47 , 2, 2, 2, '2023-01-02');
INSERT INTO TB_JOB_SIGNED VALUES (3, '2022-12-28', 50.47 , 3, 3, 1, '2023-01-07');
INSERT INTO TB_JOB_SIGNED VALUES (4, '2023-01-05', 50.47 , 1, 4, 3, '2023-01-21');
INSERT INTO TB_JOB_SIGNED VALUES (5, '2023-01-10', 540.00, 2, 5, 2, '2023-01-14');
INSERT INTO TB_JOB_SIGNED VALUES (6, '2023-01-11', 299.90, 3, 6, 1, '2023-01-19');
INSERT INTO TB_JOB_SIGNED VALUES (7, '2023-01-14', 299.90, 1, 7, 3, '2023-02-05');
INSERT INTO TB_JOB_SIGNED VALUES (8, '2023-01-20', 47.90 , 2, 8, 2, '2023-02-15');
INSERT INTO TB_JOB_SIGNED VALUES (9, '2023-01-21', 47.90 , 3, 9, 1, '2023-02-01');