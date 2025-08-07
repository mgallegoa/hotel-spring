package com.co.manuel.hotel_spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.GuestDTOResponse;
import com.co.manuel.hotel_spring.model.Guest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class GuestMapper extends AbstractGuestMapper {

  @Mapping(target = "idGuest", source = "id")
  @Mapping(target = "lastName", source = "last_name")
  @Mapping(target = "reservations", source = "reservationsDto")
  public abstract Guest mapperGestFromGuestDto(GuestDTO guestDTO);

  // @Mapping(target = "id", source = "idGuest")
  // @Mapping(target = "last_name", source = "lastName")
  // @Mapping(target = "reservationsDto", source = "reservations")
  // @InheritInverseConfiguration
  // public abstract GuestDTO mapperGestDtoFromGuest(Guest guest);

  public abstract List<GuestDTO> mapperListGuestDtoFromGuest(List<Guest> listGuest);

  public abstract GuestDTOResponse mapperGuestDtoResponseFromPageGuest(Page<Guest> guests);

}
