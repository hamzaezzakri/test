package ma.formations.controller;

import ma.formations.dto.EmpDTO;
import ma.formations.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/5/2022 3:05 PM
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class EmpController {

    /**
     * Autowired permet d'injecter le bean de type IEmpService (objet
     * représentant la couche métier). Ici, le Design Pattern qui est
     * appliqué est l'IOC (Inversion Of Control).
     */
    @Autowired
    private IEmpService empService;

    /**
     * Pour chercher tous les employés
     */
    @GetMapping(value = "/employees")
    public List<EmpDTO> getAll() {

        return empService.getEmployees();
    }

    /**
     * Pour chercher un employé par son id
     */
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Object> getEmpById(@PathVariable(value = "id") Long empDTOid) {

        EmpDTO empDTOfound = empService.getEmpById(empDTOid);
        if (empDTOfound == null)
            return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);

        return new ResponseEntity<>(empDTOfound, HttpStatus.OK);
    }

    /**
     * Pour créer un nouveau employé
     */
    @PostMapping(value = "/admin/create")
    public ResponseEntity<Object> createEmp(@Valid @RequestBody EmpDTO empDTO) {

        empService.save(empDTO);
        return new ResponseEntity<>("employee is created successfully", HttpStatus.CREATED);
    }

    /**
     * Pour modifier un employé par son id
     */
    @PutMapping(value = "/admin/update/{id}")
    public ResponseEntity<Object> updateEmp(@PathVariable(name = "id") Long empDTOid, @Valid @RequestBody EmpDTO empDTO) {

        EmpDTO empDTOfound = empService.getEmpById(empDTOid);
        if (empDTOfound == null)
            return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);

        empDTO.setId(empDTOid);
        empService.save(empDTO);
        return new ResponseEntity<>("Employee is updated successfully", HttpStatus.OK);
    }

    /**
     * Pour supprimer un employé par son id
     */
    @DeleteMapping(value = "/admin/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteEmp(@PathVariable(name = "id") Long empDTOid) {

        EmpDTO empDTOFound = empService.getEmpById(empDTOid);
        if (empDTOFound == null)
            return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);

        empService.delete(empDTOid);
        return new ResponseEntity<>("Employee is deleted successfully", HttpStatus.OK);
    }

    /**
     * Pour chercher tous les employés
     */
    @GetMapping(value = "/rest/sort/{fieldName}")
    public List<EmpDTO> sortBy(@PathVariable String fieldName) {

        return empService.sortBy(fieldName);
    }

    /**
     * Afficher la liste des employés en utilisant la pagination
     */
    @GetMapping("/rest/pagination/{pageid}/{size}")
    public List<EmpDTO> pagination(@PathVariable int pageid, @PathVariable int size) {

        return empService.findAll(pageid, size);
    }
}
