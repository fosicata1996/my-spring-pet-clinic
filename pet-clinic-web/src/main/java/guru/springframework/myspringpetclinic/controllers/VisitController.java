package guru.springframework.myspringpetclinic.controllers;

import guru.springframework.myspringpetclinic.model.Pet;
import guru.springframework.myspringpetclinic.model.Visit;
import guru.springframework.myspringpetclinic.services.PetService;
import guru.springframework.myspringpetclinic.services.VisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@RequiredArgsConstructor
public class VisitController {
    public static final String VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute
    public Visit loadPetWIthVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.add(visit);

        return visit;
    }

    @GetMapping("/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model) {
        return VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return VISITS_CREATE_OR_UPDATE_FORM;
        } else {
            visitService.save(visit);

            return "redirect:/owners/" + visit.getPet().getOwner().getId();
        }
    }
}
