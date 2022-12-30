package com.hostbooks.studentApplication.service;

import com.hostbooks.studentApplication.entities.Address;
import com.hostbooks.studentApplication.exception.AddressException;

import java.util.List;

public interface AddressService {


    public String createAddressById(Address add, Integer Id) throws AddressException;

    public List<Address> getAllAddressByStudentId(Integer Id) throws AddressException;

    public Address getAddressByAddressId(Integer stdId ,Integer addId) throws AddressException;

    public String deleteAddressById(Integer stdId ,Integer Id) throws AddressException;

    public List<Address> getAllAddress() throws  AddressException;
}
