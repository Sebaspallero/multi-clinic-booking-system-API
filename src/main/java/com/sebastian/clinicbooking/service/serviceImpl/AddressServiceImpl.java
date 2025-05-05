package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sebastian.clinicbooking.DTO.address.AddressRequestDTO;
import com.sebastian.clinicbooking.DTO.address.AddressResponseDTO;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.AddressMapper;
import com.sebastian.clinicbooking.model.Address;
import com.sebastian.clinicbooking.repository.AddressRepository;
import com.sebastian.clinicbooking.service.IAddressService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressServiceImpl implements IAddressService{

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper){
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {
        Address address = addressMapper.toEntity(addressRequestDTO);
        addressRepository.save(address);
        log.info("Address created: {}", address);
        return addressMapper.toDto(address);
    }

    @Override
    public AddressResponseDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        log.info("Address found: {}", address);
        return addressMapper.toDto(address);
    }

    @Override
    public List<AddressResponseDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        log.info("Found {} addresses", addresses.size());
        return addressMapper.toDtoList(addresses);
    }

    @Override
    public Page<AddressResponseDTO> getAllAddresses(Pageable pageable) {
        Page<Address> addresses = addressRepository.findAll(pageable);
        log.info("Found {} addresses", addresses.getTotalElements());
        return addressMapper.toDtoPage(addresses);
    }

    @Override
    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO addressRequestDTO) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        addressMapper.updateEntityFromDto(addressRequestDTO, address);
        addressRepository.save(address);
        log.info("Address updated: {}", address);
        return addressMapper.toDto(address);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));

        addressRepository.delete(address);
        log.info("Address deleted: {}", address);
    }
}
