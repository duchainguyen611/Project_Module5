package com.ra.model.serviceImp;

import com.ra.model.entity.Vendor;
import com.ra.model.repository.VendorRepository;
import com.ra.model.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImp implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public void delete(Long Id) {
        vendorRepository.deleteById(Id);
    }


    @Override
    public Vendor findById(Long Id) {
        return vendorRepository.findById(Id).orElse(null);
    }
}
