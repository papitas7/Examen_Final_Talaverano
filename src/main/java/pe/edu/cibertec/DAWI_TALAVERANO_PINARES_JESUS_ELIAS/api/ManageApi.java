package pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.dto.CarDto;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.response.*;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-cars")
public class ManageApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDto> optional = manageService.getAllUsersById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDto carDto = optional.get();
                    return new FindCarsResponse("01", "", List.of(carDto));
                } else {
                    return new FindCarsResponse("02", "Cars not found", null);
                }

            } else {
                List<CarDto> cars = manageService.getAllCars();
                if (!cars.isEmpty())
                    return new FindCarsResponse("01", "", cars);
                else
                    return new FindCarsResponse("03", "Cars list not found", cars);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "Service not found", null);

        }
    }

    @GetMapping("/detail")
    public FindCarsByIdResponse findCarsById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDto> optional = manageService.getAllUsersById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDto carDto = optional.get();
                    return new FindCarsByIdResponse("01", "", carDto);
                } else {
                    return new FindCarsByIdResponse("02", "Cars not found", null);
                }

            } else
                return new FindCarsByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsByIdResponse("99", "Service not found", null);

        }
    }

    @PostMapping("/update")
    public UpdateCarsResponse updateUser(@RequestBody CarDto carDto) {

        try {
            if (manageService.updateCar(carDto)) {
                return new UpdateCarsResponse("01", "");
            } else {
                return new UpdateCarsResponse("02", "Car not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarsResponse("99", "Service not found");

        }

    }

    @PostMapping("/delete")
    public DeleteCarResponse deleteCar(@RequestParam(value = "id") int id) {
        try {
            if (manageService.deleteCarById(id)) {
                return new DeleteCarResponse("01", "");
            } else {
                return new DeleteCarResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "Service not found");
        }
    }

    @PostMapping("/add")
    public AddCarResponse addCar(@RequestBody CarDto carDto) {
        try {
            if (manageService.addCar(carDto)) {
                return new AddCarResponse("01", "");
            } else {
                return new AddCarResponse("02", "Failed to add car");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AddCarResponse("99", "Service not found");
        }
    }
}
