package altayo.spring.petclinic.controllers;

import altayo.spring.petclinic.model.Owner;
import altayo.spring.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        // Works as a commands-converters structure
        webDataBinder.setDisallowedFields("id");
    }
//
//    @RequestMapping({"", "/", "/index", "/index.html"})
//    public String listOwners(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("owners/show");
        mav.addObject(ownerService.findById(id));
        return mav;
    }

    @GetMapping("/find")
    public String findOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwner";
    }

    @GetMapping("/list")
    public String findOwnerProcess(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> ownerResults = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (ownerResults.isEmpty()) {
            result.rejectValue("lastName", "notfound", "not found");
            return "owners/findOwner";
        } else if (ownerResults.size() == 1) {
            owner = ownerResults.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", ownerResults);
            return "owners/ownersList";
        }
    }
}
