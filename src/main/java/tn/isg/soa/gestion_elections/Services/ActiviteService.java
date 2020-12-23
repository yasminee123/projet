package tn.isg.soa.gestion_elections.Services;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.soa.gestion_elections.Models.Activite;
import tn.isg.soa.gestion_elections.Models.Candidat;
import tn.isg.soa.gestion_elections.Repositories.ActiviteRepository;
import tn.isg.soa.gestion_elections.Repositories.CandidatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepos;


    //Add activity
    public ResponseEntity<Activite> AddActivite(Activite a)
    {
        try {
            Activite el1= activiteRepos.save(a);
            return new ResponseEntity<Activite>(el1, HttpStatus.CREATED);
        }catch (Exception  e)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        }


    //Get all activities
    public ResponseEntity<List<Activite>> GetAllActivities()
    {
        List<Activite> lst= activiteRepos.findAll();
        if(lst.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity(lst,HttpStatus.OK);
    }

    //Get One activity
    public ResponseEntity<?> GetOneActivity(Long id)
    {
        Optional<Activite> res=activiteRepos.findById(id);
        if(res.isEmpty())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(res,HttpStatus.OK);
    }

    //Delete activity
    public ResponseEntity DeleteActivity(Long id)
    {
        Optional<Activite> res=activiteRepos.findById(id);
        if(res.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
        {    activiteRepos.deleteById(id);
            return new ResponseEntity(res,HttpStatus.OK);
        }
    }
    //Update activity
    public ResponseEntity<?> updateActivity(Activite newactivity,Long id)
    {
        Optional<Activite> activite = activiteRepos.findById(newactivity.getId());
        if(activite.isEmpty() )
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Activite a1= activite.get();
        a1.setType(newactivity.getType());
        a1.setDuree(newactivity.getDuree());
        a1.setDate(newactivity.getDate());
        a1.setLieu(newactivity.getLieu());
        Activite a2=activiteRepos.save(a1);
        return  new ResponseEntity(a1,HttpStatus.OK);
    }


}
