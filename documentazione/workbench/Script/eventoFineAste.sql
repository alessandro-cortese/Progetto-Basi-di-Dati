use aste_onlineDB;
DELIMITER //

CREATE EVENT IF NOT exists `aste_onlineDB`.`controllo_stato_asta`
on schedule every 1 minute starts current_time on completion preserve
comment "implementazione cambio stato di un oggetto in asta al termine dell'asta"
do
begin

	declare var_cf_utente varchar(16);
	declare var_codice_oggetto varchar(10);
    declare var_prezzo_di_vendita float;
    declare var_numero_offerte int;
    
    -- cerco i dati relativi agli oggetti in asta per cui l'asta riferita ad essi è terminata
    select codice, numero_offerte, valore_massima_offerta
    from oggetto_in_asta 
    where current_date() >= data_fine_asta and current_time() > orario_inizio_asta 
		and tipo = 'oggetto in asta'
	limit 1
    into var_codice_oggetto, var_numero_offerte, var_prezzo_di_vendita;
    
    -- se il numero di offerte è 0 allora l'oggetto non è stato venduto
    if(var_numero_offerte = 0) then 
		
        update oggetto_in_asta
        set tipo = 'oggetto non venduto'
        where codice = var_codice_oggetto;
    
    elseif(var_numero_offerte > 0) then 
		
        -- trovo l'utente che ha fatto l'offerta massima
        select offerta.utente
        from oggetto_in_asta join offerta on offerta.oggetto_in_asta = oggetto_in_asta.codice
        where oggetto_in_asta.codice = var_codice_oggetto and var_prezzo_di_vendita = offerta.importo
        into var_cf_utente;
		
        -- aggiorno lo stato dell'oggetto
        update oggetto_in_asta 
        set tipo = oggetto venduto, prezzo_di_vendita = var_prezzo_di_vendita, 
			utente = var_cf_utente
		where codice = var_codice_oggetto;
        
	end if;    

end//

DELIMITER ;