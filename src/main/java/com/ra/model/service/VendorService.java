package com.ra.model.service;

import com.ra.model.entity.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAll();
    Boolean add(Vendor vendor);
    Boolean update(Vendor vendor);

    Boolean delete(Long Id);
    Vendor findById(Long Id);
}
