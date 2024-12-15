package pe.edu.cibertec.DAWI_TALAVERANO_PINARES_JESUS_ELIAS.dto;

public record CarDto(Integer car_id,
                     String make,
                     String model,
                     Integer year,
                     String license_plate,
                     String engine_type,
                     String color) {
}
