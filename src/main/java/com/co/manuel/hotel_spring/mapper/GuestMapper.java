package com.co.manuel.hotel_spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.model.Guest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class GuestMapper extends AbstractGuestMapper {

  public static final GuestMapper INSTANCE = Mappers.getMapper(GuestMapper.class);

  @Mapping(target = "idGuest", source = "id")
  @Mapping(target = "lastName", source = "last_name")
  @Mapping(target = "reservations", source = "reservationsDto")
  public abstract Guest mapperGestFromGuestDto(GuestDTO guestDTO);

  // @Mapping(target = "id", source = "idGuest")
  // @Mapping(target = "last_name", source = "lastName")
  // @Mapping(target = "reservationsDto", source = "reservations")
  // @InheritInverseConfiguration
  // public abstract GuestDTO mapperGestDtoFromGuest(Guest guest);

  abstract List<GuestDTO> mapperListGuestDtoFromGuest(List<Guest> listGuest);

}
