package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.model.Moment;
import com.view.MomentParameter;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public interface MomentRepository extends
                                  PageableRepository<MomentParameter>,
                                  CrudRepository<Moment, String> {
    
    Page<Moment> findAll(Pageable pageable);
    
    default Page<Moment> page(MomentParameter parameter) {
        return findAll(convertPageParameter(parameter));
    }
    
}
