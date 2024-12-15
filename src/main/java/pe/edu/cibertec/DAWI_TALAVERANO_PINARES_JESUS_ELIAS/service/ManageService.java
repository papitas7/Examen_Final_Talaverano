package pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.service;

import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<CarDto> getAllCars() throws Exception;

    Optional<CarDto> getAllUsersById(int id) throws Exception;

    boolean updateCar(CarDto carDto) throws Exception;

    boolean deleteCarById(int id) throws Exception;

    boolean addCar(CarDto carDto) throws Exception;
}
