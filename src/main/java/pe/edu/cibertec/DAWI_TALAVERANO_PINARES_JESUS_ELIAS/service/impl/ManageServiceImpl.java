package pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.dto.CarDto;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.entity.Car;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.repository.CarRepository;
import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.service.ManageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            CarDto carDto = new CarDto(car.getCar_id(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getLicense_plate(),
                    car.getEngine_type(),
                    car.getColor()
            );
            cars.add(carDto);
        });
        return cars;
    }

    @Override
    public Optional<CarDto> getAllUsersById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCar_id(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getLicense_plate(),
                car.getEngine_type(),
                car.getColor()
        ));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.car_id());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            car.setLicense_plate(carDto.license_plate());
            car.setEngine_type(carDto.engine_type());
            car.setColor(carDto.color());
            carRepository.save(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.car_id());
        if(optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            car.setLicense_plate(carDto.license_plate());
            car.setEngine_type(carDto.engine_type());
            car.setColor(carDto.color());
            carRepository.save(car);
            return true;
        }
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }
}




