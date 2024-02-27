package com.ra.uploadFile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface StorageService {
    void store(MultipartFile file);
    void init ();
}
