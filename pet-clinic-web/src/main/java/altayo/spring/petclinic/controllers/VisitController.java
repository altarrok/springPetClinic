package altayo.spring.petclinic.controllers;

import altayo.spring.petclinic.model.Pet;
import altayo.spring.petclinic.model.Visit;
import altayo.spring.petclinic.services.PetService;
import altayo.spring.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/new")
    public String createVisit() {
        return "pets/createUpdateVisit";
    }

    @PostMapping("/new")
    public String storeVisit(@Valid Visit visit, BindingResult bindingResult, @PathVariable Long ownerId) {
        if (bindingResult.hasErrors()) {
            return "pets/createUpdateVisit";
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }
}
