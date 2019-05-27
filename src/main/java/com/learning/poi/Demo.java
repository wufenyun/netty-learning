package com.learning.poi;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lbc.wrap.QueryingCollection;
import com.lbc.wrap.SimpleQueryingCollection;
import com.lbc.wrap.query.Criteria;
import com.lbc.wrap.query.Query;
import com.learning.poi.entity.CateMsg;
import com.learning.poi.entity.Cate2GuideDto;
import com.learning.poi.entity.Guide2CateDto;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {

    public final static String path = "D://data";
    public final static String reg = ".csv";


    @Test
    public void read() {
        //获取所有基础类目生效cndir
        File validCndirFile = new File("D://data/release/基础类目(2018-09-06).xls");
        Set<String> allValidCndir = ExcellUtil.readCndirColumn(validCndirFile,1);

        //获取文件列表
        File[] files = FileUtil.listFile(path,reg);
        for(File file:files) {
            List<UrlAndCndir> normalUrls = CsvUtil.listNormalUrls(file);
            for(UrlAndCndir urlAndCndir:normalUrls) {
                if(allValidCndir.contains(urlAndCndir.getCndir())) {
                    urlAndCndir.setIsValidCndir(1);
                } else {
                    urlAndCndir.setIsValidCndir(0);
                }
            }

            String fileName = file.getName();
            String newName = fileName.substring(0,fileName.indexOf(".")) + ".xlsx";
            ExcellUtil.write(normalUrls,newName);
        }

        System.out.println(JSON.toJSONString(allValidCndir));

    }

    @Test
    public void testReadCndir() {
        File validCndirFile = new File("D://data/release/基础类目(2018-09-06).xls");
        Set<String> allValidCndir = ExcellUtil.readCndirColumn(validCndirFile,0);
        System.out.println(JSON.toJSONString(allValidCndir));
        System.out.println(allValidCndir.size());
    }

    @Test
    public void generateCate2Guid() {
        //读取基础类目数据
        File cateFile = new File("D://data/基础类目(2018-09-10).xls");
        List<CateMsg> cates = ExcellUtil.readCateMsg(cateFile);
        System.out.println(JSON.toJSONString(cates));

        //读取导购类目数据
        File guideFile = new File("D://data/导购类目(2018-09-10).xls");
        Map<String, CateMsg> guides = ExcellUtil.readCateMsgWithCndirKey(guideFile);
        System.out.println(JSON.toJSONString(guides));

        //合并信息并
        List<Cate2GuideDto> allGC = new ArrayList<>();
        for(CateMsg cateMsg:cates) {
            String cateCndir = cateMsg.getCateCndir();
            if(StringUtils.isBlank(cateCndir)) {
                continue;
            }

            Cate2GuideDto g2c = new Cate2GuideDto();
            g2c.setCateMsg(cateMsg);
            g2c.setGuideMsg(guides.get(cateCndir));
            allGC.add(g2c);
        }

        //生成新文件
        System.out.println("开始合并文件");
        ExcellUtil.writeC2G(allGC,"基础类目-导购类目映射表.xlsx");
        System.out.println("合并文件结束");
    }

    @Test
    public void generateGuid2Cate() {
        //读取基础类目数据
        File cateFile = new File("D://data/基础类目(2018-09-10).xls");
        Map<String, CateMsg> cates = ExcellUtil.readCateMsgWithCndirKey(cateFile);
        System.out.println(JSON.toJSONString(cates));

        //读取导购类目数据
        File guideFile = new File("D://data/导购类目(2018-09-10).xls");
        List<CateMsg> guides = ExcellUtil.readCateMsg(guideFile);
        System.out.println(JSON.toJSONString(guides));

        //合并信息并
        List<Guide2CateDto> allGC = new ArrayList<>();
        for(CateMsg guideMsg:guides) {
            String cateCndir = guideMsg.getCateCndir();
            if(StringUtils.isBlank(cateCndir)) {
                continue;
            }
            Guide2CateDto g2c = new Guide2CateDto();
            g2c.setCateMsg(cates.get(guideMsg.getCateCndir()));
            g2c.setGuideMsg(guideMsg);
            allGC.add(g2c);
        }

        //生成新文件
        System.out.println("开始合并文件");
        ExcellUtil.writeG2C(allGC,"导购类目-基础类目映射表.xlsx");
        System.out.println("合并文件结束");
    }

    @Test
    public void pub2cate() {
        //读取基础类目数据
        File cateFile = new File("D://data/基础类目(2018-09-27).xls");
        Map<String,CateMsg> cateMap = ExcellUtil.readCateMsgWithMap(cateFile);
        System.out.println(JSON.toJSONString(cateMap));

        File pubFile = new File("D://data/发布-基础多对一统计表.xls");
        List<Integer> allCateId = ExcellUtil.readTargetColumn(pubFile,4);
        System.out.println(JSON.toJSONString(allCateId));

        List<CateMsg> taget = new ArrayList<>();
        for (Integer cateId:allCateId) {
            String key = cateId.toString();
            CateMsg cateMsg = cateMap.get(key);
            taget.add(cateMsg);
        }

        ExcellUtil.writeP2C(taget,"发布-基础.xls");
    }
}
