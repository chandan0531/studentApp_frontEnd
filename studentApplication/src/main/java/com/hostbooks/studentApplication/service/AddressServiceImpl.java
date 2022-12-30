package com.hostbooks.studentApplication.service;

import com.hostbooks.studentApplication.entities.Address;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.AddressException;
import com.hostbooks.studentApplication.exception.StudentException;
import com.hostbooks.studentApplication.repository.AddressDao;
import com.hostbooks.studentApplication.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDao aDao;

    @Autowired
    private StudentDao sDao;


    @Override
    public String createAddressById(Address add, Integer Id) throws AddressException {

        Optional<Student> opt = sDao.findById(Id);
        if(!opt.isPresent()) {
            throw new StudentException("Address not found with this given ID" + Id);
        }

        opt.get().getAdds().add(add);
        add.setStudent(opt.get());

        aDao.save(add);

        return "Address on the Student successfully...";
    }

    @Override
    public List<Address> getAllAddressByStudentId(Integer Id) throws AddressException {

        Optional<Student> opt = sDao.findById(Id);

        if(!opt.isPresent()) {
            throw new StudentException("Student not found with this given ID " + Id);
        }
        else
        {
            return opt.get().getAdds();
        }


    }

    @Override
    public Address getAddressByAddressId(Integer stdId, Integer addId) throws AddressException {

        Optional<Student> opt =  sDao.findById(stdId);

        if(!opt.isPresent()) {
            throw new StudentException("Student not found with this given ID " + stdId);
        }

        Optional<Address> add =  aDao.findById(addId);
        if(!add.isPresent()) {
            throw new AddressException("Address Not Found with this given ID" + addId);
        }

        return add.get();
    }


    @Override
    public String deleteAddressById(Integer stdId, Integer Id) throws AddressException {


        Optional<Student> opt =  sDao.findById(stdId);

        if(!opt.isPresent()) {
            throw new StudentException("Student not found with this given ID" + stdId);
        }

        Optional<Address> add =   aDao.findById(Id);
        if(!add.isPresent()) {
            throw new AddressException("Address not found with this given ID" + Id);
        }

        aDao.deleteById(Id);

        return "Address deleted successfully...";
    }

    @Override
    public List<Address> getAllAddress() throws AddressException {
        List<Address> addresses = aDao.findAll();

        if(addresses.size()>0)
        {
            return addresses;

        }
        else
        {
            throw  new AddressException("No addresses found");
        }
    }
}
