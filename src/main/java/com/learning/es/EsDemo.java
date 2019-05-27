package com.learning.es;

import com.alibaba.fastjson.JSON;
import com.learning.es.dto.Hero;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EsApplication.class})
public class EsDemo {

    @Autowired
    private HerosRepository herosRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void initdata() {
        String text = "拥有超越普通人的特殊能力，做出一些不同寻常的壮举和英勇的行为，保护人民，与恶势力搏斗。他们主要是由超人，蝙蝠侠，神奇女侠，闪电侠，绿灯侠，绿巨人，蜘蛛侠，钢铁侠和许多有超能力的英雄组成的。";
        /*Hero employee = new Hero();
        employee.setFirstName("蝙蝠侠");
        employee.setLastName("蝙蝠侠");
        employee.setAge(26);
        employee.setAbout(text);
        herosRepository.save(employee);

        employee = new Hero();
        employee.setAge(26);
        employee.setAbout(text);
        employee.setFirstName("神奇女侠");
        employee.setLastName("神奇女侠");
        herosRepository.save(employee);

        employee = new Hero();
        employee.setAge(26);
        employee.setAbout(text);
        employee.setFirstName("闪电侠");
        employee.setLastName("闪电侠");
        herosRepository.save(employee);

        employee = new Hero();
        employee.setAge(26);
        employee.setAbout(text);
        employee.setFirstName("绿灯侠");
        employee.setLastName("绿灯侠");
        herosRepository.save(employee);

        employee = new Hero();
        employee.setAge(26);
        employee.setAbout(text);
        employee.setFirstName("绿巨人");
        employee.setLastName("绿巨人");
        herosRepository.save(employee);

        employee = new Hero();
        employee.setAge(26);
        employee.setAbout(text);
        employee.setFirstName("蜘蛛侠");
        employee.setLastName("蜘蛛侠");
        herosRepository.save(employee);

        employee = new Hero();
        employee.setAge(26);
        employee.setAbout(text);
        employee.setFirstName("钢铁侠");
        employee.setLastName("钢铁侠");
        herosRepository.save(employee);*/
    }

    @Test
    public void test() {
        String key = "蜘蛛侠";
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("about",key))
                .build();
        Iterable<Hero> accountInfo = herosRepository.search(query);
        List<Hero> list = elasticsearchTemplate.queryForList(query,Hero.class);
        System.err.println(JSON.toJSONString(accountInfo));
    }

    @Test
    public void highlight() {
        String field = "about";
        String searchMessage = "蜘蛛侠";
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(field, searchMessage))
                .withHighlightFields(new HighlightBuilder.Field(field))
                .build();
        List<Hero> list = elasticsearchTemplate.queryForList(searchQuery,Hero.class);
        System.err.println(JSON.toJSONString(list));
    }

    @Test
    public void muiltyquery() {
        String field = "lastName";
        String field2 = "firstName";
        String searchMessage = "蜘蛛侠";
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(searchMessage,field, field2))
                .build();
        List<Hero> list = elasticsearchTemplate.queryForList(searchQuery,Hero.class);
        //elasticsearchTemplate.delete(new DeleteQuery());
        System.err.println(JSON.toJSONString(list));
    }

    @Test
    public void termsQueryTest() {
        String field = "firstName";
        String searchMessage = "蜘蛛";
        String searchMessage2 = "侠";
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termsQuery(field, searchMessage,searchMessage2))
                .build();
        List<Hero> list = elasticsearchTemplate.queryForList(searchQuery,Hero.class);
        //elasticsearchTemplate.delete(new DeleteQuery());
        System.err.println(JSON.toJSONString(list));
    }
}
