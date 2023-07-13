-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema aste_onlineDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema aste_onlineDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aste_onlineDB` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `aste_onlineDB` ;

-- -----------------------------------------------------
-- Table `aste_onlineDB`.`amministratori`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aste_onlineDB`.`amministratori` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aste_onlineDB`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aste_onlineDB`.`categoria` (
  `nome` VARCHAR(45) NOT NULL,
  `macrocategoria` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`nome`),
  INDEX `macrotegoria_idx` (`macrocategoria` ASC) VISIBLE,
  CONSTRAINT `macrotegoria`
    FOREIGN KEY (`macrocategoria`)
    REFERENCES `aste_onlineDB`.`categoria` (`nome`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aste_onlineDB`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aste_onlineDB`.`utente` (
  `CF` VARCHAR(16) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `data_di_nascita` DATE NOT NULL,
  `città_di_nascita` VARCHAR(45) NOT NULL,
  `numero_carta_di_credito` VARCHAR(16) NOT NULL,
  `data_di_scadenza_carta_di_credito` DATE NOT NULL,
  `cvv` VARCHAR(3) NOT NULL,
  `via_di_consegna` VARCHAR(45) NOT NULL,
  `comune_di_consegna` VARCHAR(45) NOT NULL,
  `cap_di_consegna` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `numero_civico_di_consegna` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CF`),
  UNIQUE INDEX `numero_carta_di_credito_UNIQUE` (`numero_carta_di_credito` ASC) VISIBLE,
  UNIQUE INDEX `cvv_UNIQUE` (`cvv` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aste_onlineDB`.`oggetto_in_asta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aste_onlineDB`.`oggetto_in_asta` (
  `codice` VARCHAR(10) NOT NULL,
  `descrizione` VARCHAR(45) NOT NULL,
  `stato` VARCHAR(45) NOT NULL,
  `prezzo_di_base` FLOAT NOT NULL,
  `descrizione_dimensioni` VARCHAR(45) NOT NULL,
  `tipo` ENUM('oggetto in asta', 'oggetto non venduto', 'oggetto venduto') NOT NULL,
  `numero_offerte` INT NULL DEFAULT NULL,
  `data_inizio_asta` DATE NOT NULL,
  `data_fine_asta` DATE NOT NULL,
  `orario_inizio_asta` TIME NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `prezzo_di_vendita` FLOAT NULL DEFAULT NULL,
  `utente` VARCHAR(16) NULL DEFAULT NULL,
  `durata_asta` INT NOT NULL,
  `valore_massima_offerta` FLOAT NULL,
  PRIMARY KEY (`codice`),
  INDEX `data_fine_asta` USING BTREE (`data_fine_asta`) VISIBLE,
  INDEX `categoria_idx` (`categoria` ASC) VISIBLE,
  INDEX `fk_oggetto_in_asta_utente_idx` (`utente` ASC) VISIBLE,
  CONSTRAINT `categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `aste_onlineDB`.`categoria` (`nome`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_oggetto_in_asta_utente`
    FOREIGN KEY (`utente`)
    REFERENCES `aste_onlineDB`.`utente` (`CF`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aste_onlineDB`.`offerta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aste_onlineDB`.`offerta` (
  `utente` VARCHAR(16) NOT NULL,
  `oggetto_in_asta` VARCHAR(10) NOT NULL,
  `importo` FLOAT NOT NULL,
  `data_inserimento` DATE NOT NULL,
  `ora_inserimento` TIME NOT NULL,
  `tipo` ENUM('offerta', 'controfferta automatica') NOT NULL,
  `importo_massimo` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`utente`, `oggetto_in_asta`, `importo`),
  INDEX `oggetto_in_asta_idx` (`oggetto_in_asta` ASC) VISIBLE,
  CONSTRAINT `oggetto_in_asta`
    FOREIGN KEY (`oggetto_in_asta`)
    REFERENCES `aste_onlineDB`.`oggetto_in_asta` (`codice`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `utente`
    FOREIGN KEY (`utente`)
    REFERENCES `aste_onlineDB`.`utente` (`CF`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `aste_onlineDB` ;

-- -----------------------------------------------------
-- function controllo_cf
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_cf`(codice_fiscale varchar(16)) RETURNS tinyint(1)
DETERMINISTIC
BEGIN
	if codice_fiscale regexp '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$' then
		return true;
	else
		return false;
	end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure elimina_categoria
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `elimina_categoria`(
	in var_nome_categoria varchar(45)
)
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level repeatable read;
	start transaction;

	delete from categoria
    where nome = var_nome_categoria;

	commit;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure fai_offerta
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `fai_offerta`(
	in var_cf_utente varchar(16),
    in var_codice_oggetto varchar(10),
    in var_importo float
)
BEGIN

	-- valori per l'offerta da inserire
    -- e per i controlli 
	
	declare var_data_scadenza_carta date;
	declare var_data_fine_asta date;
    declare var_ora_fine_asta time(1);
    declare tipo_oggetto enum('oggetto in asta', 'oggetto non venduto', 'oggetto venduto');
    declare var_offerta_massima float;
    declare var_prezzo_base_oggetto float;
	declare var_data_inserimento_offerta date;
    declare var_orario_inserimento_offerta time(1);
    declare var_numero_offerte int;
    declare var_nuovo_numero_offerte int;
	declare var_tipo_offerta enum('offerta', 'controfferta automatica');	

	-- valori della nuova offerta generata da 
	-- controfferta automatica

    declare var_utente_controfferta varchar(16);
    declare var_oggetto_in_asta_controfferta varchar(10);
    declare var_importo_massimo_controfferta float;
    declare var_ora_inserimento_controfferta time(1);
    declare var_data_inserimento_controfferta date;


	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level serializable;
	start transaction;

		-- controllo i valori di inserimento
        
		select prezzo_di_base, numero_offerte 
		into var_prezzo_base_oggetto, var_numero_offerte
		from oggetto_in_asta 
		where codice = var_codice_oggetto;
        
        if(var_numero_offerte = 0) then 
			if(var_importo < var_prezzo_base_oggetto) then 
				signal sqlstate '45003' set message_text = "L'offerta non è valida, è la prima offerta e deve essre almeno pari al prezzo iniziale di base d'asta";
			end if;
		end if;
        
        if(var_importo % 0.50 <> 0) then
			SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = 'Il valore deve essere un multiplo di 0.50.';
		end if;
	
		select valore_massima_offerta into var_offerta_massima
		from oggetto_in_asta 
		where codice = var_codice_oggetto;
    
		if(var_importo <= var_offerta_massima) then 
			SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = "Il valore deve essere maggiore di tutte le offerte per l'oggetto specificato.";
		end if;

		select data_fine_asta, orario_inizio_asta, tipo
		from oggetto_in_asta
		where codice = var_codice_oggetto
		into var_data_fine_asta, var_ora_fine_asta, tipo_oggetto;
    
		if(tipo_oggetto <> 'oggetto in asta') then 
			signal sqlstate '45059' set message_text = "Non puoi fare offerte su questo oggetto.";
		end if;
    
		if(var_data_fine_asta < current_date()) then
			signal sqlstate '45059' set message_text = "Non puoi fare offerte su questo oggetto.";
		end if;
    
		if(var_data_fine_asta = current_date() and var_ora_fine_asta < current_time()) then
			signal sqlstate '45059' set message_text = "Non puoi fare offerte su questo oggetto.";
		end if;
    
		select data_di_scadenza_carta_di_credito
		from utente
		where cf = var_cf_utente
		into var_data_scadenza_carta;
        
        if(var_data_scadenza_carta < var_data_fine_asta) then
			signal sqlstate '45089' set message_text = "Non puoi fare un'offerta su questo oggetto, la tua carta di credito scade prima della fine dell'asta.";
		end if;
	         
		-- inzializzo i dati relativi all'offerta appena inserita

		set var_data_inserimento_offerta = current_date();
		set var_orario_inserimento_offerta = current_time();
		set var_tipo_offerta = 'offerta';
    
		select numero_offerte into var_numero_offerte
		from oggetto_in_asta 
		where codice = var_codice_oggetto;
    
		set var_nuovo_numero_offerte = var_numero_offerte + 1;
    
		-- inserisco la nuova offerta
		
		insert into offerta 
        (utente, oggetto_in_asta, importo, data_inserimento,
		ora_inserimento, tipo)
		values (var_cf_utente, var_codice_oggetto, var_importo, 
		var_data_inserimento_offerta, var_orario_inserimento_offerta, 
		var_tipo_offerta);

		-- aggiorno il numero delle offerte per l'oggetto

		update oggetto_in_asta
		set numero_offerte = var_nuovo_numero_offerte, 
		valore_massima_offerta = var_importo
		where codice = var_codice_oggetto;


		-- trovo l'utente e il valore dell'importo dell'offerta automatica 

		set var_oggetto_in_asta_controfferta = var_codice_oggetto;	

    	select utente, importo_massimo
    	from offerta 
    	where oggetto_in_asta = var_oggetto_in_asta_controfferta 
    	and tipo = 'controfferta automatica'
    	and utente <> var_cf_utente
        group by utente, importo_massimo
        having max(importo_massimo)
        order by importo_massimo desc
    	limit 1
    	into var_utente_controfferta, var_importo_massimo_controfferta; 

		if(var_utente_controfferta is not null and 
		(var_importo + 0.50) <=  var_importo_massimo_controfferta) then 
    
        	-- inizializzo i parametri per generare la nuova offerta

        	set  var_ora_inserimento_controfferta = var_orario_inserimento_offerta;
        	set  var_data_inserimento_controfferta = current_date();

	        -- genero la nuova offerta

	        insert into offerta 
	        (utente, oggetto_in_asta, importo, data_inserimento,
	        ora_inserimento, tipo, importo_massimo)
	        values
	        (var_utente_controfferta, var_oggetto_in_asta_controfferta,
	        var_importo + 0.50, var_data_inserimento_controfferta,
			var_ora_inserimento_controfferta, 'controfferta automatica', 
			var_importo_massimo_controfferta);

	        -- aggiorno i valori nella tabella oggetto_in_asta

	        update oggetto_in_asta
	        set numero_offerte = numero_offerte + 1, 
	        valore_massima_offerta = var_importo + 0.50 
	        where codice = var_oggetto_in_asta_controfferta; 

	    end if;
        
	commit;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisci_categoria_senza_macrocategoria
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_categoria_senza_macrocategoria`(
	in var_nome_categoria varchar(45)
)
BEGIN
	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level read uncommitted;
	start transaction;
		
        insert into categoria (nome) values (var_nome_categoria);
	
    commit;
        
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisci_oggetto_in_asta
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_oggetto_in_asta`(
	in var_descrizione varchar(45),
    in var_stato varchar(45),
    in var_prezzo_di_base float,
    in var_descrizione_dimensioni varchar(45),
    in var_durata_asta int,
    in var_categoria varchar(45)
)
BEGIN

	declare var_codice_oggetto varchar(10);
    declare var_tipo enum ('oggetto in asta', 'oggetto non venduto', 'oggetto venduto');
    declare var_numero_offerte int;
    declare var_data_inizio_asta date;
    declare var_data_fine_asta date;
	declare var_orario_inizio_asta time(1);
	
    declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level read uncommitted;
	start transaction;
	
    
    set var_codice_oggetto = substring(replace(uuid(), '-', ''), 1, 10);
    set var_tipo = 'oggetto in asta';
    set var_numero_offerte = 0;
    set var_data_inizio_asta = current_date();
    set var_data_fine_asta = date_add(var_data_inizio_asta, interval var_durata_asta day);
	set var_orario_inizio_asta = current_time();
    
    insert into oggetto_in_asta 
	(codice, descrizione, stato, prezzo_di_base, descrizione_dimensioni,
	tipo, numero_offerte, data_inizio_asta, durata_asta, data_fine_asta,
	orario_inizio_asta, categoria)
	values 
	(var_codice_oggetto, var_descrizione, var_stato, var_prezzo_di_base,
	var_descrizione_dimensioni, var_tipo, var_numero_offerte, var_data_inizio_asta,
	var_durata_asta, var_data_fine_asta, var_orario_inizio_asta, var_categoria);
    
    commit;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(in var_username
				VARCHAR(45), in var_password VARCHAR(45), out var_role INT)
BEGIN

-- var_role = 3 --> login
-- var_role = 1 --> utente
-- var_role = 2 --> amministratore

		set var_role = 3;
        if exists(
			select * from utente
			WHERE username = var_username AND
			password = md5(var_password))
		then set var_role = 1;
        end if;
        
        if exists(
			select * from amministratori
			WHERE username = var_username AND
			password = md5(var_password)) 
		then set var_role = 2;
        end if;
        
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifica_macrocategoria
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifica_macrocategoria`(
	in var_nome_categoria varchar(45),
    in var_nome_macrocategoria varchar(45)
)
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level repeatable read;
	start transaction;

	update categoria 
    set macrocategoria = var_nome_macrocategoria
    where nome = var_nome_categoria;

	commit;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifica_nome_categoria
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifica_nome_categoria`(
	in var_vecchio_nome varchar(45),
    in var_nuovo_nome varchar(45)
)
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level repeatable read;
	start transaction;

	update categoria 
	set nome = var_nuovo_nome
	where nome = var_vecchio_nome;

	commit;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure registrazione
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrazione`(
	in var_cf varchar(16),
    in var_nome varchar(45),
    in var_cognome varchar(45),
    in var_data_di_nascita date,
    in var_città_di_nascita varchar(45),
    in var_numero_carta_di_credito varchar(16),
    in var_data_di_scadenza_carta_di_credito date,
    in var_cvv varchar(3),
    in var_via_di_consegna varchar(45),
    in var_numero_civico_di_consegna varchar(45),
    in var_comune_di_consegna varchar(45),
    in var_cap_di_consegna varchar(45),
    in var_username varchar(45),
    in var_password varchar(45)
)
BEGIN
	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level read uncommitted;
	start transaction;
    
	if(`aste_onlineDB`.controllo_cf(var_cf) is false) then
		signal sqlstate '45001' set message_text = 'Il codice fiscale inseirto non è valido.';
	end if;
    
    if(`aste_onlineDB`.controllo_carta_di_credito(var_numero_carta_di_credito) is false) then
		signal sqlstate '45006' set message_text = "Il numero della carta di credito non è valido.";
     end if;   
     
	if(`aste_onlineDB`.controllo_scadenza_carta_di_credito(var_data_di_scadenza_carta_di_credito) is false) then
		signal sqlstate '45031' set message_text = 'La carta di credito è scaduta.';
	end if;
     
     if(`aste_onlineDB`.controllo_cvv(var_cvv) is false) then
		signal sqlstate '45007' set message_text = "Il valore di cvv inserito non è valido.";
	end if;
    
    if(`aste_onlineDB`.controllo_cap(var_cap_di_consegna) is false) then
		signal sqlstate '45030' set message_text = "Il cap inserito non è valido.";
	end if;

	insert into utente 
    (cf, nome, cognome, data_di_nascita, città_di_nascita, numero_carta_di_credito,
    data_di_scadenza_carta_di_credito, cvv, via_di_consegna, comune_di_consegna, cap_di_consegna,
    username, password, numero_civico_di_consegna)
    values (var_cf, var_nome, var_cognome, var_data_di_nascita,
			var_città_di_nascita, var_numero_carta_di_credito, var_data_di_scadenza_carta_di_credito,
            var_cvv, var_via_di_consegna, var_comune_di_consegna,
			var_cap_di_consegna, var_username, md5(var_password), var_numero_civico_di_consegna
	);
    
    commit;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure vedi_aste_aperte
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `vedi_aste_aperte`()
BEGIN
	
    declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    
	set transaction isolation level read uncommitted;
	start transaction;
	
		select codice, descrizione, stato, descrizione_dimensioni, 
        prezzo_di_base, numero_offerte, data_fine_asta,
		orario_inizio_asta, valore_massima_offerta, categoria
		from oggetto_in_asta
		where data_fine_asta > current_date()
		union
		select codice, descrizione, stato, descrizione_dimensioni, 
        prezzo_di_base, numero_offerte, data_fine_asta,
		orario_inizio_asta, valore_massima_offerta, categoria
		from oggetto_in_asta
		where data_fine_asta = current_date() and
        orario_inizio_asta - current_time() > 0;
    
    commit;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_oggetti_aggiudicati
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_oggetti_aggiudicati`(
	in var_cf_utente varchar(16)
)
BEGIN
	
    declare exit handler for sqlexception 
    begin
		rollback;
        resignal;
	end;
    
    set transaction isolation level read uncommitted;
    start transaction;
		
        select descrizione, stato, 
        descrizione_dimensioni as 'descrizione dimensioni', categoria,
        prezzo_di_vendita as 'prezzo di vendita'
		from oggetto_in_asta 
		where utente = var_cf_utente;
	
    commit;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_stato_aste_con_offerte
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_stato_aste_con_offerte`(
		in var_cf_utente varchar(45)
)
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
	
    set transaction isolation level read uncommitted;
    start transaction;
    
		select codice, descrizione, stato, 
        descrizione_dimensioni as 'descrizione dimensioni', 
        prezzo_di_base as 'prezzo di base',
        data_fine_asta as 'data fine asta', 
		orario_inizio_asta as 'orario inizio asta', 
        valore_massima_offerta as 'valore massima offerta'
		from oggetto_in_asta join offerta o on 
        oggetto_in_asta.codice = o.oggetto_in_asta
		where data_fine_asta > current_date() and
        oggetto_in_asta.tipo = 'oggetto in asta' and
        var_cf_utente in (select utente from offerta
								where offerta.utente = o.utente)
		union 
		select codice, descrizione, stato,
        descrizione_dimensioni as 'descrizione dimensioni',
        prezzo_di_base as 'prezzo di base', 
        data_fine_asta as 'data fine asta', 
		orario_inizio_asta as 'orario inizio asta', 
        valore_massima_offerta as 'valore massima offerta'
		from oggetto_in_asta join offerta o 
        on oggetto_in_asta.codice = o.oggetto_in_asta
		where data_fine_asta = current_date() and
        oggetto_in_asta.tipo = 'oggetto in asta' and
        orario_inizio_asta - current_time() > 0
		and var_cf_utente in (select utente from offerta
								where offerta.utente = o.utente);
	
    commit;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- function controllo_carta_di_credito
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_carta_di_credito`(carta_di_credito varchar(16)) RETURNS tinyint(1)
DETERMINISTIC
BEGIN
	if carta_di_credito regexp '[0-9]{16}$' then
		return true;
	else
		return false;
	end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function controllo_cvv
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_cvv`(cvv varchar(3)) RETURNS tinyint(1)
DETERMINISTIC
BEGIN
	if cvv regexp '[0-9]{3}$' then
		return true;
	else
		return false;
	end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure fai_prima_offerta_controfferta_automatica
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `fai_prima_offerta_controfferta_automatica`(
	in var_cf_utente varchar(16),
    in var_codice_oggetto varchar(10),
    in var_importo_offerta_iniziale float,
    in valore_importo_massimo float
)
BEGIN

	declare var_data_scadenza_carta date;
	declare var_data_fine_asta date;
    declare var_ora_fine_asta time(1);
    declare tipo_oggetto enum('oggetto in asta', 'oggetto non venduto', 'oggetto venduto');
    declare var_offerta_massima float;
    declare var_prezzo_base_oggetto float;
	declare var_tipo_offerta enum('offerta', 'controfferta automatica');
    declare var_data_inserimento date;
    declare var_orario_inserimento time(1);
    declare var_numero_offerte int;
    declare var_nuovo_numero_offerte int;
    
    -- valori della nuova offerta generata da 
	-- controfferta automatica

    declare var_utente_controfferta varchar(16);
    declare var_oggetto_in_asta_controfferta varchar(10);
    declare var_importo_massimo_controfferta float;
    declare var_ora_inserimento_controfferta time(1);
    declare var_data_inserimento_controfferta date;

	declare exit handler for sqlexception
    begin 
		rollback;
        resignal;
	end;
	
    set transaction isolation level serializable;
    start transaction;
    
		select prezzo_di_base, numero_offerte 
		into var_prezzo_base_oggetto, var_numero_offerte
		from oggetto_in_asta 
		where codice = var_codice_oggetto;
        
        if(var_numero_offerte = 0) then 
			if(var_importo_offerta_iniziale < var_prezzo_base_oggetto) then 
				signal sqlstate '45003' set message_text = "L'offerta non è valida, è la prima offerta e deve essre almeno pari al prezzo iniziale di base d'asta";
			end if;
		end if;
        
        if(var_importo_offerta_iniziale % 0.50 <> 0) then
			SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = 'Il valore deve essere un multiplo di 0.50.';
		end if;
	
		select valore_massima_offerta into var_offerta_massima
		from oggetto_in_asta 
		where codice = var_codice_oggetto;
    
		if(var_importo_offerta_iniziale <= var_offerta_massima) then 
			SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = "Il valore deve essere maggiore di tutte le offerte per l'oggetto specificato.";
		end if;

		select data_fine_asta, orario_inizio_asta, tipo
		from oggetto_in_asta
		where codice = var_codice_oggetto
		into var_data_fine_asta, var_ora_fine_asta, tipo_oggetto;
    
		if(tipo_oggetto <> 'oggetto in asta') then 
			signal sqlstate '45059' set message_text = "Non puoi fare offerte su questo oggetto.";
		end if;
    
		if(var_data_fine_asta < current_date()) then
			signal sqlstate '45059' set message_text = "Non puoi fare offerte su questo oggetto.";
		end if;
    
		if(var_data_fine_asta = current_date() and var_ora_fine_asta < current_time()) then
			signal sqlstate '45059' set message_text = "Non puoi fare offerte su questo oggetto.";
		end if;
    
		select data_di_scadenza_carta_di_credito
		from utente
		where cf = var_cf_utente
		into var_data_scadenza_carta;
        
        if(var_data_scadenza_carta < var_data_fine_asta) then
			signal sqlstate '45089' set message_text = "Non puoi fare un'offerta su questo oggetto, la tua carta di credito scade prima della fine dell'asta.";
		end if;

		set var_tipo_offerta = 'controfferta automatica';
		set var_data_inserimento = current_date();
		set var_orario_inserimento = current_time();
    
		select numero_offerte into var_numero_offerte
		from oggetto_in_asta 
		where codice = var_codice_oggetto;
    
		set var_nuovo_numero_offerte = var_numero_offerte + 1;
    
		insert into offerta 
        (utente, oggetto_in_asta, importo, data_inserimento,
		 ora_inserimento, tipo, importo_massimo)
		values (var_cf_utente, var_codice_oggetto, var_importo_offerta_iniziale,
				var_data_inserimento, var_orario_inserimento, var_tipo_offerta,
				valore_importo_massimo);
            
		update oggetto_in_asta 
		set numero_offerte = var_nuovo_numero_offerte, 
			valore_massima_offerta = var_importo_offerta_iniziale
		where codice = var_codice_oggetto;
    
    		-- trovo l'utente e il valore dell'importo dell'offerta automatica 

		set var_oggetto_in_asta_controfferta = var_codice_oggetto;	

    	select utente, importo_massimo
    	from offerta 
    	where oggetto_in_asta = var_oggetto_in_asta_controfferta 
    	and tipo = 'controfferta automatica'
    	and utente <> var_cf_utente
        group by utente, importo_massimo
        having max(importo_massimo) > valore_importo_massimo
		order by importo_massimo desc
    	limit 1
    	into var_utente_controfferta, var_importo_massimo_controfferta; 

		if(var_utente_controfferta is not null and 
		(var_importo_offerta_iniziale + 0.50) <=  var_importo_massimo_controfferta) then 
    
        	-- inizializzo i parametri per generare la nuova offerta

        	set  var_ora_inserimento_controfferta = var_orario_inserimento;
        	set  var_data_inserimento_controfferta = current_date();

	        -- genero la nuova offerta

	        insert into offerta 
	        (utente, oggetto_in_asta, importo, data_inserimento,
	        ora_inserimento, tipo, importo_massimo)
	        values
	        (var_utente_controfferta, var_oggetto_in_asta_controfferta,
	        var_importo_offerta_iniziale + 0.50, var_data_inserimento_controfferta,
			var_ora_inserimento_controfferta, 'controfferta automatica', 
			var_importo_massimo_controfferta);

	        -- aggiorno i valori nella tabella oggetto_in_asta

	        update oggetto_in_asta
	        set numero_offerte = numero_offerte + 1, 
	        valore_massima_offerta = var_importo_offerta_iniziale + 0.50 
	        where codice = var_oggetto_in_asta_controfferta; 

	    end if;
    
    commit;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisci_categoria_con_macrocategoria
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_categoria_con_macrocategoria`(
	in var_nome_categoria varchar(45),
    in var_nome_macrocategoria varchar(45)
)
BEGIN
	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level repeatable read;
	start transaction;
		
			insert into categoria values (var_nome_categoria, var_nome_macrocategoria);
	
    commit;
        
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function controllo_cap
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_cap`(cap varchar(5)) RETURNS tinyint(1)
DETERMINISTIC
BEGIN
	if cap regexp '[0-9]{5}$' then
		return true;
	else
		return false;
	end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure elimina_macrocategoria
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `elimina_macrocategoria`(
	in var_nome_categoria varchar(45)
)
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;

	set transaction isolation level repeatable read;
	start transaction;
	
	update categoria 
    set macrocategoria = NULL
    where nome = var_nome_categoria;
    
    commit;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ottieni_utente_by_username
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE PROCEDURE `ottieni_utente_by_username` (in var_username varchar(45))
BEGIN
	
    select cf, nome, cognome, data_di_nascita,
	città_di_nascita, numero_carta_di_credito,
	data_di_scadenza_carta_di_credito, cvv, via_di_consegna, 
	comune_di_consegna, cap_di_consegna, username, password,
	numero_civico_di_consegna
	from utente 
	where username = var_username;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure registrazione_amministratori
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrazione_amministratori`(in var_username varchar(45), in var_password varchar(45))
BEGIN
	insert into amministratori (username, password) values (var_username, md5(var_password));
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure mostra_categorie
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostra_categorie`()
BEGIN

	declare exit handler for sqlexception
    begin
		rollback;
        resignal;
	end;
    
	set transaction isolation level read committed;
	start transaction;
    
    select nome, macrocategoria 
    from categoria;
    
    commit;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function controllo_scadenza_carta_di_credito
-- -----------------------------------------------------

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `controllo_scadenza_carta_di_credito`(data_scadenza date) RETURNS tinyint(1)
DETERMINISTIC
BEGIN

	if(data_scadenza < current_date()) then 
		return false;
	else 
		return true;
	end if;

END$$

DELIMITER ;
USE `aste_onlineDB`;

DELIMITER $$
USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` 
TRIGGER `controllo_livello_gerarchico_categoria_inserimento` 
BEFORE INSERT ON `categoria` FOR EACH ROW 
BEGIN

    declare livello int;
    declare nome_macrocategoria varchar(45);
    set livello = 0;
    set nome_macrocategoria = new.macrocategoria;
    
	while nome_macrocategoria is not null do
			set livello = livello + 1;
            select macrocategoria into nome_macrocategoria 
            from categoria 
            where nome = nome_macrocategoria;
            if livello > 2 then
				signal sqlstate '45010' set message_text = "La categoria non può essere inserita, il livello gerarchico è già 3.";
			end if;
	end while;
END$$

USE `aste_onlineDB`$$
CREATE DEFINER=`root`@`localhost` 
TRIGGER `controllo_livello_gerarchico_categoria_aggiornamento` 
BEFORE UPDATE ON `categoria` FOR EACH ROW 
BEGIN

    declare livello int;
    declare nome_macrocategoria varchar(45);
    set livello = 0;
    set nome_macrocategoria = new.macrocategoria;
    
	while nome_macrocategoria is not null do
			set livello = livello + 1;
            select macrocategoria into nome_macrocategoria 
            from categoria 
            where nome = nome_macrocategoria;
            if livello > 2 then
				signal sqlstate '45010' set message_text = "La categoria non può essere inserita, il livello gerarchico è già 3.";
			end if;
	end while;
END$$

USE `aste_onlineDB`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `aste_onlineDB`.`controllo_durata_asta`
BEFORE INSERT ON `aste_onlineDB`.`oggetto_in_asta`
FOR EACH ROW
BEGIN
	if new.durata_asta < 1 or new.durata_asta > 7 then
		signal sqlstate '45002' set message_text = "La durata dell'asta non è compresa tra 1 e 7 giorni";
	end if;
END$$


DELIMITER ;
CREATE USER 'utente' IDENTIFIED BY '!utente@1224';

CREATE USER 'login' IDENTIFIED BY '!login@1224';

CREATE USER 'amministratori' IDENTIFIED BY '!amministratori@1224';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
