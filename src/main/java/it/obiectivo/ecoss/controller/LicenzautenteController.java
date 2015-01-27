package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Licenzautente;
import it.obiectivo.ecoss.domain.Licenzecommerciali;
import it.obiectivo.ecoss.domain.Tabellastati;
import it.obiectivo.ecoss.json.CustomObjectResponse;
import it.obiectivo.ecoss.service.CredenzialiutenteService;
import it.obiectivo.ecoss.service.LicenzautenteService;
import it.obiectivo.ecoss.service.LicenzecommercialiService;
import it.obiectivo.ecoss.service.TabellastatiService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/licenzautente")
public class LicenzautenteController {
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="licenzautenteService")
	private LicenzautenteService licenzautenteService;
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiService;
	
	@Resource(name="tabellastatiService")
	private TabellastatiService tabellastatiService;
	
	@Resource(name="licenzecommercialiService")
	private LicenzecommercialiService licenzeCommercialiService;
	
	//INVOCO LA JSP New - Inserimento di una nuova licenza utente
    @RequestMapping(value = "/New", method = RequestMethod.GET)
    public String getAdd(HttpServletRequest request, Model model) {
    	logger.debug("Visualizzazione della pagina licenzautente/New");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else{	
	        Credenzialiutente credUte = credenzialiService.getByUserId(request.getRemoteUser());
	        List<Licenzecommerciali> licenzecommerciali = licenzeCommercialiService.getAll();
	        List<Tabellastati> tabellastati = tabellastatiService.getByNomeTabella("Licenzautente");
	        model.addAttribute("tabellastati", tabellastati);
			model.addAttribute("licenzecommerciali", licenzecommerciali);
			model.addAttribute("licenzautenteAttribute", new Licenzautente());
	        return "licenzautente/New";
    	}
    }
    
    //RESTITUISCO una lista di Licenzecommerciali 
	@RequestMapping(value = "/Listed", method = RequestMethod.GET)
	public ModelAndView getAll( HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView modelAndView = new ModelAndView("licenzautente/Listed");
        List<Licenzautente> listaLicenze = licenzautenteService.getAll();
        	
        String jsonText = new ObjectMapper().writeValueAsString(listaLicenze);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        
        modelAndView.addObject("listaLicenze", json);
        	
        return modelAndView;
	}
    
    
    @RequestMapping(value = "/Listed", method = RequestMethod.POST)
    public @ResponseBody  CustomObjectResponse getAll() {
        	logger.debug("LicenzautenteController: - Ricevuta richiesta Listed Licenzautente");

        	List<Licenzautente> licenze = licenzautenteService.getAll();
        	
        	CustomObjectResponse response = new CustomObjectResponse();
        	response.setRows(licenze);

        	// Assegna il numro totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(licenze.size()) );
        	
        	// Since our service is just a simple service for teaching purposes
        	// We didn't really do any paging. But normally your DAOs or your persistence layer should support this
        	// Assign a dummy page
        	response.setPage( "1" );
        	
        	// Same. Assign a dummy total pages
        	response.setTotal( "10" );
        	
        	// Return the response
        	// Spring will automatically convert our CustomUserResponse as JSON object. 
        	// This is triggered by the @ResponseBody annotation. 
        	// It knows this because the JqGrid has set the headers to accept JSON format when it made a request
        	// Spring by default uses Jackson to convert the object to JSON
        	
        	logger.debug("Prima del return: response = "+response.toString());
        	return response;
    	}
    
    /* SALVO la licenza CREATA */
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView add( @Valid @ModelAttribute("licenzautenteAttribute") Licenzautente licenzautente, 
    		BindingResult result, HttpServletRequest request, ModelAndView modelAndView ){
    	
    	logger.debug("LicenzautenteController: Salvataggio della licenza utente");
        
        List<Licenzecommerciali> licenzecommercialiList = new ArrayList<Licenzecommerciali>();
        licenzecommercialiList.add(licenzeCommercialiService.get(licenzautente.getLicenzecommerciali().getIdLicenzeCommerciali()));
        licenzautente.setLicenzecommerciali(licenzeCommercialiService.get(licenzautente.getLicenzecommerciali().getIdLicenzeCommerciali()));
        licenzautente.setTabellastati(tabellastatiService.get(licenzautente.getTabellastati().getIdstato()));
                
		if(result.hasErrors()){
			modelAndView = new ModelAndView("licenzautente/erroreLicenza");
        	modelAndView.addObject("licenzautenteAttribute", licenzautente);
        	return modelAndView;
		}
        
		else{
			licenzautenteService.add(licenzautente);
			modelAndView = new ModelAndView("licenzautente/riepilogoLicenza");
        	modelAndView.addObject("licenzautente", licenzautente);
        	modelAndView.addObject("stato", licenzautente.getTabellastati().getStato());
        	modelAndView.addObject("licenzacommerciale", licenzautente.getLicenzecommerciali().getLicomNomeLicenza());
        	return modelAndView;
        }
    }
    
}