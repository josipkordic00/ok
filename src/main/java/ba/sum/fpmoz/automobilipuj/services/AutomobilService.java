package ba.sum.fpmoz.automobilipuj.services;

import ba.sum.fpmoz.automobilipuj.models.automobil;
import ba.sum.fpmoz.automobilipuj.repositories.AutomobilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutomobilService {
    @Autowired
    private AutomobilRepository automobilRepository;

    public automobil saveautomobil(automobil automobil) {
        return automobilRepository.save(automobil);
    }

    public List<automobil> findAllautomobili() {
        return automobilRepository.findAll();
    }

    public Optional<automobil> getautomobilById(int id) {
        return automobilRepository.findById(id);
    }

    public void deleteautomobil(int id) {
        automobilRepository.deleteById(id);
    }


    @Transactional
    public automobil updateautomobil(Integer id, automobil updatedautomobil) {
        return automobilRepository.findById(id)
                .map(automobil -> {
                    automobil.setMarka(updatedautomobil.getMarka());
                    automobil.setModel(updatedautomobil.getModel());
                    automobil.setGodina(updatedautomobil.getGodina());
                    automobil.setBoja(updatedautomobil.getBoja());
                    automobil.setSnaga(updatedautomobil.getSnaga());
                    return automobilRepository.save(automobil);
                })
                .orElseThrow(() -> new RuntimeException("automobil not found with id " + id));
    }
}
