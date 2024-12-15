package pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.response;

import pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {
}
