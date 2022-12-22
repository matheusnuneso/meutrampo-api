INSERT INTO TB_PERSON VALUES (1 ,'12345678900', 'wallace@gmail.com', 'Wallace Junior', '123', 'wallaceJr'); -- id, cpf, email, full_name, password, user_name
INSERT INTO TB_PERSON VALUES (2 ,'00123456789', 'matheus@gmail.com', 'Matheus Sobrinho', 'portao', 'MatSobrinho');
INSERT INTO TB_PERSON VALUES (3 ,'00123456789', 'junin@gmail.com', 'Alexandro', 'agua123', 'TheLittleJunior');
INSERT INTO TB_JOB VALUES (1, 2, 250.00, 'Mecanico'); -- id, id_person, price, title
INSERT INTO TB_JOB VALUES (2, 2, 150.00, 'Eletricista Automotivo');
INSERT INTO TB_JOB VALUES (3, 3, 50.00, 'Tecnico de Infomatica');
INSERT INTO TB_JOB VALUES (4, 3, 30.00, 'Borracheiro');
INSERT INTO TB_JOB VALUES (5, 1, 40.00, 'Fazedor de Gambiarra');
INSERT INTO TB_JOB_SIGNED VALUES (1, '2022-12-21', 40.00, 1, 1, 1, '2022-12-22'); -- id , contract_date, final_price, id_client, id_job, id_person, job_date
INSERT INTO TB_JOB_SIGNED VALUES (2, '2023-02-15', 540.00, 3, 3, 2, '2023-02-22');