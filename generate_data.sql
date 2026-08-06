USE dbinfox;

DROP PROCEDURE IF EXISTS generate_data;

DELIMITER $$
CREATE PROCEDURE generate_data()
BEGIN
    DECLARE i INT DEFAULT 1;

    -- Generate 10 Users
    WHILE i <= 10 DO
        INSERT IGNORE INTO tbusuarios(iduser, usuario, fone, login, senha, perfil) 
        VALUES (i + 10, CONCAT('Usuario ', i), '11999999999', CONCAT('user', i), '123456', IF(i % 2 = 0, 'admin', 'user'));
        SET i = i + 1;
    END WHILE;
    
    -- Generate 200 Clients
    SET i = 1;
    WHILE i <= 200 DO
        INSERT IGNORE INTO tbclientes(idcli, nomecli, endcli, fonecli, emailcli) 
        VALUES (i + 10, CONCAT('Cliente ', i), CONCAT('Rua ', i, ' Número ', i), '11999999999', CONCAT('cliente', i, '@teste.com'));
        SET i = i + 1;
    END WHILE;
    
    -- Generate 500 OS
    SET i = 1;
    WHILE i <= 500 DO
        INSERT INTO tbos(tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) 
        VALUES (
            IF(i % 2 = 0, 'OS', 'Orçamento'), 
            IF(i % 3 = 0, 'Na bancada', 'Entrega OK'), 
            CONCAT('Equipamento ', i), 
            'Não liga', 
            'Troca de fonte', 
            'admin', 
            ROUND(RAND() * 1000, 2), 
            (i % 200) + 11
        );
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL generate_data();
