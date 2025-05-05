package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {
        Address address = addressMapper.toEntity(addressRequestDTO);
        addressRepository.save(address);
        log.info("Address created: {}", address);
        return addressMapper.toDto(address);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponseDTO getAddressById(Long id) {
        Address address = findAddressById(id);
        
        log.info("Address found: {}", address);
        return addressMapper.toDto(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        log.info("Found {} addresses", addresses.size());
        return addressMapper.toDtoList(addresses);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AddressResponseDTO> getAllAddresses(Pageable pageable) {
        Page<Address> addresses = addressRepository.findAll(pageable);
        log.info("Found {} addresses", addresses.getTotalElements());
        return addressMapper.toDtoPage(addresses);
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO addressRequestDTO) {
        Address address = findAddressById(id);

        addressMapper.updateEntityFromDto(addressRequestDTO, address);
        addressRepository.save(address);
        log.info("Address updated: {}", address);
        return addressMapper.toDto(address);
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteAddress(Long id) {
        Address address = findAddressById(id);

        addressRepository.delete(address);
        log.info("Address deleted: {}", address);
    }

    private Address findAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));
    }
}
