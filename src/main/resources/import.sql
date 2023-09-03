-- Database seeding for USERS
insert into users (id, cpf, email, name, birth_date, gender) values ('6f007644-5bdf-4483-bf42-fb7412f66a45', '29081928619', 'renataluziaporto@asconinternet.com.br', 'Renata Luzia Francisca Porto', '1957-04-08', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('ab8ea442-d9bb-466d-8d47-3853091d545d', '25276887560', 'caroline_larissa_assuncao@infonet.com.br', 'Caroline Larissa Assunção', '1983-02-09', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('67459848-3af5-4c99-9276-543c331adcc1', '76290569236', 'elza_daconceicao@flir.com.br', 'Elza Flávia da Conceição', '2003-04-19', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('6d25d927-0199-4123-ba73-877d161bf98e', '29436331002', 'diego.ruan.viana@bhcervejas.com.br', 'Diego Ruan Viana', '1983-04-09', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('8689e521-02bc-4b1a-94d4-38a06b4a8299', '69690635425', 'caua_dias@kmspublicidade.com.br', 'Cauã Roberto Samuel Dias', '1955-03-03', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('d23c0fbc-9207-48a1-beed-4b7e4b878570', '28352828312', 'raul_figueiredo@victorseguros.com.br', 'Raul Breno Vinicius Figueiredo', '1954-02-05', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('f9fde553-945f-483a-b38b-4e3c9968112c', '05088017294', 'alice_carvalho@vivalle.com.br', 'Alice Brenda Carvalho', '1965-03-14', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('5f914550-bf78-49be-8e25-ea6357a5876e', '65989791453', 'filipe_pinto@pmi.com', 'Filipe Breno Pinto', '1959-04-18', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('d2022974-1a2a-4005-a324-11a0614e2107', '58802370605', 'marcos_nascimento@oul.com.br', 'Marcos Vinicius Theo Nascimento', '2005-04-16', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('d8535003-7f96-448e-9bbd-7d9026a696f6', '03979611701', 'leonardoisaacmoura@mls.com.br', 'Leonardo Isaac Carlos Eduardo Moura', '1965-04-06', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('40647a63-e84b-42a6-a6c2-eba4ee6bf600', '58009452335', 'marcia.mariane.dossantos@edu.uniso.br', 'Márcia Mariane dos Santos', '1969-02-11', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('9072068f-2a55-4db0-8578-e4d73fc4f668', '51032295287', 'isabellybrendalima@munhozengenharia.com.br', 'Isabelly Brenda Rebeca Lima', '1995-01-07', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('efeddbec-07d1-4cea-bbea-4b1bf96b9e8d', '01201028116', 'juan-drumond75@haldex.com', 'Juan Luan Pedro Drumond', '1977-02-23', 'M');
insert into users (id, cpf, email, name, birth_date, gender) values ('85b9a0b6-6cb1-4f18-8d95-f0a0bc99c20a', '14358166415', 'lizcristianealves@alvesbarcelos.com.br', 'Liz Cristiane Alves', '1943-03-21', 'F');
insert into users (id, cpf, email, name, birth_date, gender) values ('7049f9a7-05ec-4c4f-9bbf-44648d87e7a8', '36927710612', 'luizjorgepeixoto@omnibrasil.com.br', 'Luiz Jorge Osvaldo Peixoto', '1971-05-10', 'M');

-- Database seeding for USER_RELATIONS
insert into user_relations (child_id, owner_id, relation_type) values ('67459848-3af5-4c99-9276-543c331adcc1','6f007644-5bdf-4483-bf42-fb7412f66a45', 'Daughter');
insert into user_relations (child_id, owner_id, relation_type) values ('85b9a0b6-6cb1-4f18-8d95-f0a0bc99c20a','6f007644-5bdf-4483-bf42-fb7412f66a45', 'Husband');
insert into user_relations (child_id, owner_id, relation_type) values ('6d25d927-0199-4123-ba73-877d161bf98e','6f007644-5bdf-4483-bf42-fb7412f66a45', 'Son');
insert into user_relations (child_id, owner_id, relation_type) values ('f9fde553-945f-483a-b38b-4e3c9968112c','d8535003-7f96-448e-9bbd-7d9026a696f6', 'Wife');
insert into user_relations (child_id, owner_id, relation_type) values ('d2022974-1a2a-4005-a324-11a0614e2107','d8535003-7f96-448e-9bbd-7d9026a696f6', 'Son');
insert into user_relations (child_id, owner_id, relation_type) values ('7049f9a7-05ec-4c4f-9bbf-44648d87e7a8', 'efeddbec-07d1-4cea-bbea-4b1bf96b9e8d', 'Cousin');

-- Database seeding for ADDRESSES
insert into addresses (id, zip_code, street, number, city, state) values ('26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb', '03701010', 'Avenida Gabriela Mistral', '670', 'São Paulo', 'SP');
insert into addresses (id, zip_code, street, number, city, state) values ('db403e6d-4023-46ba-95cb-c09001ab37a2', '03654010', 'Rua Alicante', '966', 'São Paulo', 'SP');
insert into addresses (id, zip_code, street, number, city, state) values ('da3b763e-ac55-40ed-ba59-5c8a792aec63', '03642000', 'Rua José Fláviol', '268', 'São Paulo', 'SP');

-- Database seeding for ADDRESS_RELATIONS
insert into address_relations (parent_id, address_id) values ('6d25d927-0199-4123-ba73-877d161bf98e', '26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb');
insert into address_relations (parent_id, address_id) values ('40647a63-e84b-42a6-a6c2-eba4ee6bf600', 'db403e6d-4023-46ba-95cb-c09001ab37a2');
insert into address_relations (parent_id, address_id) values ('7049f9a7-05ec-4c4f-9bbf-44648d87e7a8', 'da3b763e-ac55-40ed-ba59-5c8a792aec63');

-- Database seeding for APPLIANCES
insert into appliances (id, name, brand, model, power) values ('a818055c-24db-43a4-a6fc-7f02808fe1bb', 'Ar Condicionado', 'Samsung', 'AR12BVHZCWK', 'V110');
insert into appliances (id, name, brand, model, power) values ('6706bfbf-c6ee-41b1-b18b-9960bd47ac0e', 'Geladeira', 'Electrolux', 'TF39S', 'V220');
insert into appliances (id, name, brand, model, power) values ('0415be05-b5e9-49b1-a51b-60ac820fb2e6', 'Fogão', 'Atlas', 'Mônaco', 'V110');

-- Database seeding for APPLIANCE_RELATIONS
insert into appliance_relations (address_id, appliance_id) values ('26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb', '0415be05-b5e9-49b1-a51b-60ac820fb2e6');
insert into appliance_relations (address_id, appliance_id) values ('db403e6d-4023-46ba-95cb-c09001ab37a2', '6706bfbf-c6ee-41b1-b18b-9960bd47ac0e');
insert into appliance_relations (address_id, appliance_id) values ('da3b763e-ac55-40ed-ba59-5c8a792aec63', 'a818055c-24db-43a4-a6fc-7f02808fe1bb');
