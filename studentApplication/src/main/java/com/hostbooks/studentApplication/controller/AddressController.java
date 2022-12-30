package com.hostbooks.studentApplication.controller;

import com.hostbooks.studentApplication.entities.Address;
import com.hostbooks.studentApplication.service.AddressService;
import com.hostbooks.studentApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class AddressController {

    @Autowired
    private AddressService aService;

    @Autowired
    private StudentService sService;


    @PostMapping("api/address/{stdId}")
    ResponseEntity<String> createAddressByStudentIdController(@Valid @RequestBody Address add ,
                                                             @PathVariable("stdId") Integer Id){

        String message =  aService.createAddressById(add, Id);

        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @GetMapping("api/address")
    ResponseEntity<List<Address>> getAllAddressByStudentIddController(){

        List<Address> addresses =  aService.getAllAddress();

        return new ResponseEntity<List<Address>>(addresses, HttpStatus.ACCEPTED);
    }

    @GetMapping("api/address/{stdId}")
    ResponseEntity<List<Address>> getAllAddressByStudentIddController(@PathVariable("stdId") Integer stdId){

        List<Address> stds =  aService.getAllAddressByStudentId(stdId);

        return new ResponseEntity<List<Address>>(stds, HttpStatus.ACCEPTED);
    }



    @DeleteMapping("api/address/{stdId}/{Id}")
    ResponseEntity<String> deleteAddressByIdController(@PathVariable("stdId") Integer Id, @PathVariable("Id") Integer addId){
        String mess =  aService.deleteAddressById(Id, addId);
        return new ResponseEntity<String>(mess, HttpStatus.ACCEPTED);
    }
}
