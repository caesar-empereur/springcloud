package com.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.view.PageParameter;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public interface PageableRepository<P extends PageParameter> {
    
    default Pageable convertPageParameter(P parameter) {
        return PageRequest.of(parameter.getPageNo(),
                              parameter.getPageSize(),
                              new Sort(Sort.Direction.DESC, "createTime"));
    }
}
