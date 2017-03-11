package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.ZoekIdentificatieResponse;
import nl.lakedigital.djfc.domain.Identificatie;
import nl.lakedigital.djfc.service.IdentificatieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@RequestMapping("/identificatie")
@Controller
public class IdentificatieController {private static final Logger LOGGER = LoggerFactory.getLogger(IdentificatieController.class);
    @Inject
    private IdentificatieService identificatieService;

    @RequestMapping(method = RequestMethod.GET, value = "/zoeken/{soortEntiteit}/{entiteitId}")
    @ResponseBody
    public ZoekIdentificatieResponse zoeken(@PathVariable("soortEntiteit")String soortEntiteit, @PathVariable("entiteitId") Long entiteitId) {
        LOGGER.debug("zoeken met {} en {}",soortEntiteit,entiteitId);
        ZoekIdentificatieResponse zoekIdentificatieResponse=new ZoekIdentificatieResponse();

        Identificatie identificatie=
        identificatieService.zoek(soortEntiteit,entiteitId);
        nl.lakedigital.djfc.commons.json.Identificatie json=new nl.lakedigital.djfc.commons.json.Identificatie();
        if(identificatie!=null) {
            json.setId(identificatie.getId());
            json.setEntiteitId(identificatie.getEntiteitId());
            json.setIdentificatie(identificatie.getIdentificatie());
            json.setSoortEntiteit(identificatie.getSoortEntiteit());

            zoekIdentificatieResponse.getIdentificaties().add(json);
        }
        return zoekIdentificatieResponse;
    }
    }
