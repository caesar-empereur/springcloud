package com.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.*;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * @Description 改配置类为了修复 feign 返回的分页对象无法实例化的bug
 * @author: yangyingyang
 * @date: 2018/9/12.
 */
@Configuration
public class PageJacksonConfiguration {
    
    static class JacksonModule extends SimpleModule {
        
        @Override
        public void setupModule(SetupContext context) {
            context.setMixInAnnotations(Page.class, PageMixIn.class);
        }
    }
    
    @Bean
    public Module myJacksonModule() {
        return new JacksonModule();
    }
    
    @JsonDeserialize(as = SimplePageImpl.class)
    interface PageMixIn {
    }
    
    static class SimplePageImpl<T> implements Page<T> {
        
        private final Page<T> delegate;
        
        public SimplePageImpl(@JsonProperty("content") List<T> content,
                              @JsonProperty("page") int number,
                              @JsonProperty("size") int size,
                              @JsonProperty("totalElements") long totalElements) {
            delegate = new PageImpl<>(content, PageRequest.of(number, size), totalElements);
        }
        
        @Override
        public int getTotalPages() {
            return delegate.getTotalPages();
        }
        
        @Override
        public long getTotalElements() {
            return delegate.getTotalElements();
        }
        
        @Override
        public <U> Page<U> map(Function<? super T, ? extends U> converter) {
            return delegate.map(converter);
        }
        
        @Override
        public int getNumber() {
            return delegate.getNumber();
        }
        
        @Override
        public int getSize() {
            return delegate.getSize();
        }
        
        @Override
        public int getNumberOfElements() {
            return delegate.getNumberOfElements();
        }
        
        @Override
        public List<T> getContent() {
            return delegate.getContent();
        }
        
        @Override
        public boolean hasContent() {
            return delegate.hasContent();
        }
        
        @Override
        public Sort getSort() {
            return delegate.getSort();
        }
        
        @Override
        public boolean isFirst() {
            return delegate.isFirst();
        }
        
        @Override
        public boolean isLast() {
            return delegate.isLast();
        }
        
        @Override
        public boolean hasNext() {
            return delegate.hasNext();
        }
        
        @Override
        public boolean hasPrevious() {
            return delegate.hasPrevious();
        }
        
        @Override
        public Pageable nextPageable() {
            return delegate.nextPageable();
        }
        
        @Override
        public Pageable previousPageable() {
            return delegate.previousPageable();
        }
        
        @Override
        public Iterator<T> iterator() {
            return delegate.iterator();
        }
    }
}
