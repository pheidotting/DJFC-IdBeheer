package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Identificatie;
import nl.lakedigital.djfc.repository.IdentificatieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class IdentificatieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdentificatieService.class);

    @Inject
    private IdentificatieRepository identificatieRepository;

    public void verwijder(Identificatie identificatie) {
        identificatieRepository.verwijder(identificatie);
    }

    public void verwijder(List<Identificatie> identificaties) {
        identificatieRepository.verwijder(identificaties);
    }

    public void opslaan(Identificatie identificatie) {
        LOGGER.debug("{}", identificatie);
        if (zoek(identificatie.getSoortEntiteit(), identificatie.getEntiteitId()) == null) {
            identificatieRepository.opslaan(identificatie);
        }
    }

    public void opslaan(List<Identificatie> identificaties) {
        identificatieRepository.opslaan(identificaties);
    }

    public Identificatie zoek(String soortEntiteit, Long entiteitId) {
        return identificatieRepository.zoek(soortEntiteit, entiteitId);
    }

    public Identificatie zoekOpIdentificatieCode(String identificatieCode) {
        return identificatieRepository.zoekOpIdentificatieCode(identificatieCode);
    }
}
