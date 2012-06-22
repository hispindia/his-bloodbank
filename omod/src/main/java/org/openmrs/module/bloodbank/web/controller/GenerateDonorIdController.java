package org.openmrs.module.bloodbank.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/module/bloodbank/newIds.form")
public class GenerateDonorIdController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@RequestParam("numberofids") String ids){

		BloodBankService service = Context.getService(BloodBankService.class);
		String prefix = Context.getAdministrationService().getGlobalProperty( "billing.identifier_prefix" );
		
		List <String> extIds = new ArrayList<String>();
		for (PreparedDonorId preparedDonorId : service.getAllPreparedIds()) {
	        extIds.add(preparedDonorId.getIdentifier());
        }
		
		int numIds = 0;
		if(ids != null){
			if(ids != "")
				try {
					numIds = Integer.valueOf(ids);
                }
                catch (NumberFormatException e) {
                	log.info("not a number");
                	numIds = 0;
                }
		}
		int view = numIds;
		
		if(numIds > extIds.size())
			numIds = numIds - extIds.size();
		else
			numIds = 0;
		
		int tmp = numIds;
		Random r = new Random();
		while(tmp>0){
			String identifier = getNewIdentifier(prefix, r.nextInt(999));
			if(!extIds.contains(identifier)){
				PreparedDonorId pdi = new PreparedDonorId();
				pdi.setCreator(Context.getAuthenticatedUser());
				pdi.setDateCreated(new Date());
				pdi.setIdentifier(identifier);
				pdi.setUsed(false);
				extIds.add(pdi.getIdentifier());
				service.savePreparedId(pdi);
				tmp --;
			}
		}
		
		return "redirect:newIds.form?numberofids=" + view;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap map, @RequestParam(value="numberofids", required=false) String ids){
		
		int numIds = 0;
		if(ids != null){
			if(ids != "")
				numIds = Integer.valueOf(ids);
		}
		BloodBankService service = Context.getService(BloodBankService.class);
		List <PreparedDonorId> pdis = service.getUnusedPreparedId();
		
		List<String> identifiers = new ArrayList<String>();
		for (PreparedDonorId pdi : pdis) {
			if (numIds <= 0)
				break;
	        identifiers.add(pdi.getIdentifier());
	        numIds--;
        }
		map.addAttribute("ids", identifiers);

		return "module/bloodbank/newIdsForm";
	}
	


	
	private String getNewIdentifier(String prefix, int inc)
    {
        Calendar now = Calendar.getInstance();
        String shortName = prefix;
        String noCheck = shortName
            + String.valueOf( now.get( Calendar.YEAR ) ).substring( 2, 4 )
            + String.valueOf( now.get( Calendar.MONTH ) + 1 )
            + String.valueOf( now.get( Calendar.DATE ) )
            + String.valueOf( now.get( Calendar.MINUTE ) )
            + String.valueOf( inc);
        return noCheck + "-" + getCheckdigit( noCheck );
    }
    
    /**
     * Using the Luhn Algorithm to generate check digits
     * @param idWithoutCheckdigit
     * @return idWithCheckdigit
     */
    private static int getCheckdigit( String idWithoutCheckdigit )
    {
        String validChars = "0123456789ACDEFGHJKLMNPRSTUVWXY";
        idWithoutCheckdigit = idWithoutCheckdigit.trim().toUpperCase();
        int sum = 0;
        for ( int i = 0; i < idWithoutCheckdigit.length(); i++ )
        {
            char ch = idWithoutCheckdigit.charAt( idWithoutCheckdigit.length() - i - 1 );
            if ( validChars.indexOf( ch ) == -1 )
            {
                System.out.println( "\"" + ch + "\" is an invalid character" );
            }
            int digit = (int) ch - 48;
            int weight;
            if ( i % 2 == 0 )
            {
                weight = ( 2 * digit ) - (int) ( digit / 5 ) * 9;
            } else
            {
                weight = digit;
            }
            sum += weight;
        }
        sum = Math.abs( sum ) + 10;
        return ( 10 - ( sum % 10 ) ) % 10;
    }
    
}