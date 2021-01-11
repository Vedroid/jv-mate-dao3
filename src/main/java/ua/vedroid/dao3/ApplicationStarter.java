package ua.vedroid.dao3;

import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.service.ManufacturerService;

public class ApplicationStarter {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());

    public static void main(String[] args) {
        ManufacturerService manufacturerService =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer volkswagen = new Manufacturer("Volkswagen", "Germany");
        Manufacturer audi = new Manufacturer("Avde", "Germany");
        Manufacturer tesla = new Manufacturer("Tesla", "USA");

        manufacturerService.create(volkswagen);
        manufacturerService.create(audi);
        manufacturerService.create(tesla);

        System.out.println("All Manufacturers: " + manufacturerService.getAllManufacturers());

        Manufacturer updatedAudi = manufacturerService.getById(1L);
        updatedAudi.setName("Audi");
        manufacturerService.update(updatedAudi);

        System.out.println("Update 'Avde' -> 'Audi': " + manufacturerService.getAllManufacturers());

        manufacturerService.deleteById(0L);

        System.out.println("Deleted Id '0': " + manufacturerService.getAllManufacturers());
    }
}
