package com.learning.es;

import com.alibaba.fastjson.JSON;
import com.learning.es.dto.Employee;
import com.learning.es.dto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author linzhiqiang
 */
@RestController
@RequestMapping("es")
public class EmployeeController {

    @Autowired
    private HerosRepository herosRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add(String text) {
        Hero employee = new Hero();
        //employee.setId("1");
        employee.setFirstName("hero");
        employee.setLastName("hero");
        employee.setAge(26);
        employee.setAbout(text);
        herosRepository.save(employee);
        System.err.println("add a obj");
        return "success";
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        Hero employee = herosRepository.queryHeroById("1");
        herosRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("update")
    public String update() {
        Hero employee = herosRepository.queryHeroById("1");
        employee.setFirstName("哈哈");
        herosRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("query")
    public Hero query(String id) {
        Hero accountInfo = herosRepository.queryHeroById(id);
        System.err.println(JSON.toJSONString(accountInfo));
        return accountInfo;
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping("search")
    public String search(String key) {
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQuery("about",key)).build();
        Iterable<Hero> accountInfo = herosRepository.search(query);
        List<Hero> list = elasticsearchTemplate.queryForList(query,Hero.class);
        System.err.println(JSON.toJSONString(accountInfo));
        return JSON.toJSONString(accountInfo);
    }
}

