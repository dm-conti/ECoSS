package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customUserDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiUtenteService;

	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		// Declare a null Spring User
		UserDetails user = null;
		
		try {
			
			// RITROVO LE CREDENZIALI ASSOCIATE ALlA USERNAME INSERITA
			List<Credenzialiutente> listaCredenziali = credenzialiUtenteService.getAll();
    		Iterator<Credenzialiutente> iteCredenziali = listaCredenziali.iterator();
    		
    		Credenzialiutente credenziali = null;
    		while(iteCredenziali.hasNext()){
    			 credenziali = iteCredenziali.next();
    			if(credenziali.getUserId().equalsIgnoreCase(username)){
    				break;
    			}
    		}

			user =  new User(
					credenziali.getUserId(), 
					credenziali.getPassword().toLowerCase(),
					true,
					true,
					true,
					true,
					getAuthorities(credenziali.getProfilo().getIdProfilo())
			);

		} catch (Exception e) {
			logger.error("CustomUserDetailsService: Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is authenticated or valid
		// We just merely retrieve a user that matches the specified username
		return user;
	}
	
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		//Creo la lista di autorizzazioni per l'Utente
		//List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(access);
		
		/* Tutti gli utenti hanno ruolo di Utente
		 * Therefore this user gets a ROLE_USER by default */
		logger.debug("CustomUserDetailsService: Cerco l'autorizzazione per l'utente");
		
		// Controllo se l'utente ha altri tipi di autorrizzazioni
		/**
         * @todo Effettuo un controllo sull'id. Quindi lo SWITCH seguente rimappa l'id alla 
         * descrizione corretta effettuando un passaggio inutile perche' le descrizioni sono
         * gia mappate nel DB. Dovrei modificare il DB aggiungendo una foreinkey cosi e' 
         * possibile recuperare la descrizione ed assegnare quest'ultima (eliminiamo 
         * completamente lo SWITCH ).
         **/
		switch (access.intValue()) {
			case 1:
				logger.debug("CustomUserDetailsService: Il ruolo di questo utente e' Admin");
				authList.add(new GrantedAuthorityImpl("Admin"));
				break;
			case 2: 
				logger.debug("CustomUserDetailsService: Il ruolo di questo utente e' Gestore");
				authList.add(new GrantedAuthorityImpl("Gestore"));
				break;
			case 3: 
				logger.debug("CustomUserDetailsService: Il ruolo di questo utente e' Subgestore");
				authList.add(new GrantedAuthorityImpl("Subgestore"));
				break;
			case 4:
				logger.debug("CustomUserDetailsService: Il ruolo di questo utente e' Utente");
				authList.add(new GrantedAuthorityImpl("Utente"));
				break;
			case 6:
				logger.debug("CustomUserDetailsService: Il ruolo di questo utente e' Utente_Societa");
				authList.add(new GrantedAuthorityImpl("Utente_Societa"));
				break;
		}

		// Restituisco la lista di autorizzazioni
		return authList;
  }
}