package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.user.UserResponseDTO;
import com.sebastian.clinicbooking.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(User user);

    List<UserResponseDTO> toDtoList(List<User> users);

    default Page<UserResponseDTO> toDetailDtoPage(Page<User> page) {
        List<UserResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }
}
