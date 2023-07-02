use aste_onlineDB;
DELIMITER //

CREATE EVENT IF NOT exists `aste_onlineDB`.`elimazione_dati_vecchi`
on schedule every 1 year starts current_time + interval 5 year on completion preserve
comment "Rimozione dati più vecchi di 5 anni"
do
begin
	
    delete from utente 
    where cf in (
		select cf from utente 
        where cf not in (
				select cf
				from utente as u join offerta as o on o.utente = u.cf
				group by data_inserimento, u.cf
				having max(data_inserimento) >= date_sub(now(), interval 1 year)
			)
    );
    
    delete from oggetto_in_asta 
    where codice in (
			select codice 
            from oggetto_in_asta
            where data_fine_asta >= date_sub(now, interval 5 year)
    );

end//

DELIMITER ;