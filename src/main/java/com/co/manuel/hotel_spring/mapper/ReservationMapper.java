package com.co.manuel.hotel_spring.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.co.manuel.hotel_spring.dto.ReservationDTO;
import com.co.manuel.hotel_spring.model.Reservation;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

  ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

  @Mapping(target = "idReservation", source = "id")
  Reservation mapperReservationDtoFromReservation(ReservationDTO reservationDto);

  @InheritInverseConfiguration
  ReservationDTO mapperReservationFromReservationDto(Reservation reservation);

}
