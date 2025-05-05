package com.sebastian.clinicbooking.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.address.AddressRequestDTO;
import com.sebastian.clinicbooking.DTO.address.AddressResponseDTO;

public interface IAddressService {

    AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO getAddressById(Long id);
    List<AddressResponseDTO> getAllAddresses();
    Page<AddressResponseDTO> getAllAddresses(Pageable pageable);
    AddressResponseDTO updateAddress(Long id, AddressRequestDTO addressRequestDTO);
    void deleteAddress(Long id);
}
