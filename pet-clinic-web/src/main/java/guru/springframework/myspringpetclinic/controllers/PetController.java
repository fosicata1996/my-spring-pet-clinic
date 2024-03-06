package guru.springframework.myspringpetclinic.controllers;

import guru.springframework.myspringpetclinic.model.Owner;
import guru.springframework.myspringpetclinic.model.Pet;
import guru.springframework.myspringpetclinic.model.PetType;
import guru.springframework.myspringpetclinic.services.OwnerService;
import guru.springframework.myspringpetclinic.services.PetService;
import guru.springframework.myspringpetclinic.services.PetTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
@RequiredArgsConstructor
public class PetController {
    public static final String PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private static final String PET = "pet";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.add(pet);
        model.addAttribute(PET, pet);

        return PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (!StringUtils.isEmpty(pet.getName())
                && pet.isNew()
                && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.add(pet);
        if (result.hasErrors()) {
            model.put(PET, pet);
            return PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute(PET, petService.findById(petId));
        return PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute(PET, pet);
            return PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
