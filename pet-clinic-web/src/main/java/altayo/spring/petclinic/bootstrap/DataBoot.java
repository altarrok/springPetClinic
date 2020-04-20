package altayo.spring.petclinic.bootstrap;

import altayo.spring.petclinic.model.Owner;
import altayo.spring.petclinic.model.Vet;
import altayo.spring.petclinic.services.OwnerService;
import altayo.spring.petclinic.services.VetService;
import altayo.spring.petclinic.services.map.OwnerServiceMap;
import altayo.spring.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBoot implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataBoot() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Juice");
        owner1.setLastName("Brown");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Iona");
        owner2.setLastName("James");

        ownerService.save(owner2);

        System.out.println("## Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Ukra");
        vet1.setLastName("Appu");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Christophe");
        vet2.setLastName("Paolina");

        vetService.save(vet2);

        System.out.println("## Loaded Vets");
    }
}
