-- Database seeding for USERS
insert into users (id, email, name, birth_date, gender) values ('29081928619', 'renataluziaporto@asconinternet.com.br', 'Renata Luzia Francisca Porto', '1957-04-08', 'F');
insert into users (id, email, name, birth_date, gender) values ('25276887560', 'caroline_larissa_assuncao@infonet.com.br', 'Caroline Larissa Assunção', '1983-02-09', 'F');
insert into users (id, email, name, birth_date, gender) values ('76290569236', 'elza_daconceicao@flir.com.br', 'Elza Flávia da Conceição', '2003-04-19', 'F');
insert into users (id, email, name, birth_date, gender) values ('29436331002', 'diego.ruan.viana@bhcervejas.com.br', 'Diego Ruan Viana', '1983-04-09', 'M');
insert into users (id, email, name, birth_date, gender) values ('69690635425', 'caua_dias@kmspublicidade.com.br', 'Cauã Roberto Samuel Dias', '1955-03-03', 'M');
insert into users (id, email, name, birth_date, gender) values ('28352828312', 'raul_figueiredo@victorseguros.com.br', 'Raul Breno Vinicius Figueiredo', '1954-02-05', 'M');
insert into users (id, email, name, birth_date, gender) values ('05088017294', 'alice_carvalho@vivalle.com.br', 'Alice Brenda Carvalho', '1965-03-14', 'F');
insert into users (id, email, name, birth_date, gender) values ('65989791453', 'filipe_pinto@pmi.com', 'Filipe Breno Pinto', '1959-04-18', 'M');
insert into users (id, email, name, birth_date, gender) values ('58802370605', 'marcos_nascimento@oul.com.br', 'Marcos Vinicius Theo Nascimento', '2005-04-16', 'M');
insert into users (id, email, name, birth_date, gender) values ('03979611701', 'leonardoisaacmoura@mls.com.br', 'Leonardo Isaac Carlos Eduardo Moura', '1965-04-06', 'M');
insert into users (id, email, name, birth_date, gender) values ('58009452335', 'marcia.mariane.dossantos@edu.uniso.br', 'Márcia Mariane dos Santos', '1969-02-11', 'F');
insert into users (id, email, name, birth_date, gender) values ('51032295287', 'isabellybrendalima@munhozengenharia.com.br', 'Isabelly Brenda Rebeca Lima', '1995-01-07', 'F');
insert into users (id, email, name, birth_date, gender) values ('01201028116', 'juan-drumond75@haldex.com', 'Juan Luan Pedro Drumond', '1977-02-23', 'M');
insert into users (id, email, name, birth_date, gender) values ('14358166415', 'lizcristianealves@alvesbarcelos.com.br', 'Liz Cristiane Alves', '1943-03-21', 'F');
insert into users (id, email, name, birth_date, gender) values ('36927710612', 'luizjorgepeixoto@omnibrasil.com.br', 'Luiz Jorge Osvaldo Peixoto', '1971-05-10', 'M');

-- Database seeding for USER_RELATIONS
insert into user_relations (child_id, owner_id, relation_type) values ('29081928619', '76290569236', 'Daughter');
insert into user_relations (child_id, owner_id, relation_type) values ('29081928619', '36927710612', 'Husband');
insert into user_relations (child_id, owner_id, relation_type) values ('29081928619', '29436331002', 'Son');
insert into user_relations (child_id, owner_id, relation_type) values ('03979611701', '05088017294', 'Wife');
insert into user_relations (child_id, owner_id, relation_type) values ('03979611701', '58802370605', 'Son');
insert into user_relations (child_id, owner_id, relation_type) values ('36927710612', '01201028116', 'Cousin');

-- Database seeding for ADDRESSES
insert into addresses (zip_code, street, number, city, state) values ('03701010', 'Avenida Gabriela Mistral', '670', 'São Paulo', 'SP');
insert into addresses (zip_code, street, number, city, state) values ('03654010', 'Rua Alicante', '966', 'São Paulo', 'SP');
insert into addresses (zip_code, street, number, city, state) values ('03642000', 'Rua José Fláviol', '268', 'São Paulo', 'SP');


-- Database seeding for ADDRESS_RELATIONS
insert into address_relations (parent_id, address_id) values ('29436331002', 1);
insert into address_relations (parent_id, address_id) values ('58009452335', 2);
insert into address_relations (parent_id, address_id) values ('36927710612', 3);

-- Database seeding for APPLIANCES
insert into appliances (name, brand, model, power) values ('Ar Condicionado', 'Samsung', 'AR12BVHZCWK', 'V110');
insert into appliances (name, brand, model, power) values ('Geladeira', 'Electrolux', 'TF39S', 'V220');
insert into appliances (name, brand, model, power) values ('Fogão', 'Atlas', 'Mônaco', 'V110');

-- Database seeding for APPLIANCE_RELATIONS
insert into appliance_relations (address_id, appliance_id) values (1, 3);
insert into appliance_relations (address_id, appliance_id) values (2, 2);
insert into appliance_relations (address_id, appliance_id) values (3, 1);

