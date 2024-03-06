package guru.springframework.myspringpetclinic.formatters;

import guru.springframework.myspringpetclinic.model.PetType;
import guru.springframework.myspringpetclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        return petTypeService.findAll().stream()
                .filter(type -> type.getName().equals(text))
                .findFirst()
                .orElseThrow(() -> new ParseException("type not found: " + text, 0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
