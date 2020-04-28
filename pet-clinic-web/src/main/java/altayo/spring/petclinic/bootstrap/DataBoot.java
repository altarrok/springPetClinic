package altayo.spring.petclinic.bootstrap;

import altayo.spring.petclinic.model.Owner;
import altayo.spring.petclinic.model.PetType;
import altayo.spring.petclinic.model.Vet;
import altayo.spring.petclinic.services.OwnerService;
import altayo.spring.petclinic.services.PetTypeService;
import altayo.spring.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBoot implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataBoot(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");

        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Juice");
        owner1.setLastName("Brown");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Iona");
        owner2.setLastName("James");

        ownerService.save(owner2);

        System.out.println("## Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ukra");
        vet1.setLastName("Appu");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Christophe");
        vet2.setLastName("Paolina");

        vetService.save(vet2);

        System.out.println("## Loaded Vets");
    }
}
