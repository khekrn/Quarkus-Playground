package com.khekrn.superheroes.villain;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class VillainService {

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Villain> findAllVillains() {
        return Villain.listAll();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Villain findById(Long id) {
        return Villain.findById(id);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Villain findRandomVillain() {
        Villain randomVillain = null;
        while (randomVillain == null) {
            randomVillain = Villain.findRandom();
        }
        return randomVillain;
    }

    public Villain persistVillain(@Valid Villain villain) {
        Villain.persist(villain);
        return villain;
    }

    public Villain updateVillain(@Valid Villain villain) {
        Villain exitingVillain = Villain.findById(villain.id);
        exitingVillain.name = villain.name;
        exitingVillain.level = villain.level;
        exitingVillain.otherName = villain.otherName;
        exitingVillain.picture = villain.picture;
        exitingVillain.powers = villain.powers;
        return exitingVillain;
    }

    public void deleteVillain(Long id){
        Villain villain = findById(id);
        villain.delete();
    }
}
