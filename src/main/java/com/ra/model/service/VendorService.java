package com.ra.model.service;

import com.ra.model.entity.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAll();
    Vendor save(Vendor vendor);
    void delete(Long Id);
    Vendor findById(Long Id);
}
