package com.learning.es;


import com.learning.es.dto.Hero;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface HerosRepository extends ElasticsearchRepository<Hero,String>{

    /**
     * 查询雇员信息
     * @param id
     * @return
     */
    Hero queryHeroById(String id);
}

