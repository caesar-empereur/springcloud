package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.model.Comment;
import com.view.CommentParameter;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public interface CommentRepository extends
                                   PageableRepository<CommentParameter>,
                                   CrudRepository<Comment, String> {

    Page<Comment> findAll(Pageable pageable);
    
    default Page<Comment> page(CommentParameter parameter) {
        return findAll(convertPageParameter(parameter));
    }
}
