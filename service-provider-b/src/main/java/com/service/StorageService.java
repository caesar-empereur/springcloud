package com.service;

import com.model.Storage;
import com.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/27.
 */
@Service
public class StorageService {

    @Resource
    private StorageRepository storageRepository;

    @Transactional(timeout = 3)
    public void save(Storage storage){
        storageRepository.save(storage);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
