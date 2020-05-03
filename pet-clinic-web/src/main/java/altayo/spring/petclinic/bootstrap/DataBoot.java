package altayo.spring.petclinic.bootstrap;

import altayo.spring.petclinic.model.*;
import altayo.spring.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataBoot implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataBoot(OwnerService ownerService, VetService vetService,
                    PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) loadData();
        System.out.println("## Bootstrap successful");
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Juice");
        owner1.setLastName("Brown");
        owner1.setAddress("2323 Brisket St.");
        owner1.setCity("Vancouver");
        owner1.setTelephone("123 123 1212");

        Pet pet1 = new Pet();
        pet1.setName("Coo");
        pet1.setPetType(savedDogPetType);
        pet1.setBirthDate(LocalDate.now());
        pet1.setOwner(owner1);
        owner1.getPets().add(pet1);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Iona");
        owner2.setLastName("James");
        owner2.setAddress("9847 Qtip St.");
        owner2.setCity("Los Angeles");
        owner2.setTelephone("758 826 8383");

        Pet pet2 = new Pet();
        pet2.setName("Tippo");
        pet2.setPetType(savedCatPetType);
        pet2.setBirthDate(LocalDate.now());
        pet2.setOwner(owner2);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Ukra");
        vet1.setLastName("Appu");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Christophe");
        vet2.setLastName("Paolina");
        vet2.getSpecialties().add(savedSurgery);
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

    }
}
