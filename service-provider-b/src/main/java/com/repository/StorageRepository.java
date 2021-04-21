package com.repository;

import com.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/27.
 */
@Repository
public interface StorageRepository extends JpaRepository<Storage, String> {
}
