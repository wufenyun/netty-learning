package com.learning.poi;

import com.alibaba.fastjson.JSON;
import com.learning.poi.entity.CateMsg;
import com.learning.poi.entity.Cate2GuideDto;
import com.learning.poi.entity.Guide2CateDto;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcellUtil {

    private final static String PATH = "D://data/release/";

    public static List<Integer> readTargetColumn(File file, int targetColumn) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            List<Integer> allColun = new ArrayList<>();

            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("行数：" + rowLength + ",列数：" + colLength);

            Cell cell = null;
            for (int i = 1; i < rowLength; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(targetColumn);
                int cateId = (int) cell.getNumericCellValue();
                allColun.add(cateId);
            }
            return allColun;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Set<String> readCndirColumn(File file, int targetColumn) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            HashSet<String> allCndir = new HashSet<>();

            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("行数：" + rowLength + ",列数：" + colLength);

            Cell cell = null;
            for (int i = 0; i < rowLength; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(targetColumn);
                String cndir = cell.getStringCellValue();
                if(StringUtils.isNotBlank(cndir)) {
                    allCndir.add(cndir);
                    System.out.println(cndir);
                }
            }
            return allCndir;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Map<String,CateMsg> readCateMsgWithCndirKey(File file) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            Map<String,CateMsg> all = new HashMap<>();

            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("行数：" + rowLength + ",列数：" + colLength);

            Cell cell = null;
            for (int i = 1; i < rowLength; i++) {
                CateMsg dto = new CateMsg();
                row = sheet.getRow(i);
                dto.setCateId(row.getCell(0).getStringCellValue());
                dto.setCateName(row.getCell(1).getStringCellValue());
                dto.setCateLevel(row.getCell(2).getStringCellValue());
                dto.setCateStatus(row.getCell(3).getStringCellValue());
                String cndir = row.getCell(4).getStringCellValue();
                dto.setCateCndir(cndir);
                if(StringUtils.isBlank(cndir)) {
                    System.out.println(dto.getCateId() + "的cndir为空");
                    continue;
                }
                CateMsg exist = all.put(cndir,dto);
                if(exist!=null) {
                    System.out.println(dto.getCateId() + "的cndir"+ cndir +"已经存在");
                }
            }
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<CateMsg> readCateMsg(File file) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            List<CateMsg> allDto = new ArrayList<>();

            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("行数：" + rowLength + ",列数：" + colLength);

            Cell cell = null;
            for (int i = 1; i < rowLength; i++) {
                CateMsg dto = new CateMsg();
                row = sheet.getRow(i);
                dto.setCateId(row.getCell(0).getStringCellValue());
                dto.setCateName(row.getCell(1).getStringCellValue());
                dto.setCateLevel(row.getCell(2).getStringCellValue());
                dto.setCateStatus(row.getCell(3).getStringCellValue());
                dto.setCateCndir(row.getCell(4).getStringCellValue());
                allDto.add(dto);
            }
            return allDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Map<String,CateMsg> readCateMsgWithMap(File file) {
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            Map<String,CateMsg> allDto = new HashMap<>();

            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("行数：" + rowLength + ",列数：" + colLength);

            Cell cell = null;
            for (int i = 1; i < rowLength; i++) {
                CateMsg dto = new CateMsg();
                row = sheet.getRow(i);
                dto.setCateId(row.getCell(0).getStringCellValue());
                dto.setCateName(row.getCell(1).getStringCellValue());
                dto.setCateLevel(row.getCell(2).getStringCellValue());
                dto.setCateStatus(row.getCell(3).getStringCellValue());
                //dto.setCateCndir(row.getCell(4).getStringCellValue());
                allDto.put(dto.getCateId(),dto);
            }
            return allDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static int toIntStatus(String status) {
        return  ("开启".equals(status))?1:0;
    }

    public static void write(List<UrlAndCndir> datas,String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置这些样式
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        row.createCell(0).setCellStyle(cellStyle);
        row.createCell(0).setCellValue("url");

        row.createCell(1).setCellStyle(cellStyle);
        row.createCell(1).setCellValue("是否有效(1：有效，0：无效)");

        row.createCell(2).setCellStyle(cellStyle);
        row.createCell(2).setCellValue("cndir");

        for(UrlAndCndir urlAndCndir:datas) {
            rowIndex++;
            row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(urlAndCndir.getUrl());
            row.createCell(1).setCellValue(urlAndCndir.getIsValidCndir());
            row.createCell(2).setCellValue(urlAndCndir.getCndir());
        }

        //workbook.setSheetName(0, "信息");
        try {
            File file = new File(PATH + name);
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeC2G(List<Cate2GuideDto> datas, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex++);
        setC2GHeader(row);

        for(Cate2GuideDto g2c:datas) {
            row = sheet.createRow(rowIndex++);
            int columnIndex = 0;
            CateMsg cateMsg = g2c.getCateMsg();
            row.createCell(columnIndex++).setCellValue(cateMsg.getCateId());
            row.createCell(columnIndex++).setCellValue(cateMsg.getCateName());
            row.createCell(columnIndex++).setCellValue(cateMsg.getCateLevel());
            row.createCell(columnIndex++).setCellValue(cateMsg.getCateCndir());
            row.createCell(columnIndex++).setCellValue(cateMsg.getCateStatus());

            CateMsg guide = g2c.getGuideMsg();
            if(guide!=null) {
                row.createCell(columnIndex++).setCellValue("");
                row.createCell(columnIndex++).setCellValue(guide.getCateId());
                row.createCell(columnIndex++).setCellValue(guide.getCateName());
                row.createCell(columnIndex++).setCellValue(guide.getCateLevel());
                row.createCell(columnIndex++).setCellValue(guide.getCateCndir());
                row.createCell(columnIndex++).setCellValue(guide.getCateStatus());
            }
        }

        //workbook.setSheetName(0, "信息");
        try {
            File file = new File(PATH + name);
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setC2GHeader(Row row) {
        int colunIndex = 0;
        row.createCell(colunIndex++).setCellValue("基础类目id");
        row.createCell(colunIndex++).setCellValue("基础类目名");
        row.createCell(colunIndex++).setCellValue("基础类目层级");
        row.createCell(colunIndex++).setCellValue("基础类目cndir");
        row.createCell(colunIndex++).setCellValue("基础类目状态(0关闭，1开启)");
        row.createCell(colunIndex++).setCellValue(" ");
        row.createCell(colunIndex++).setCellValue("导购类目id");
        row.createCell(colunIndex++).setCellValue("导购类目名");
        row.createCell(colunIndex++).setCellValue("导购类目层级");
        row.createCell(colunIndex++).setCellValue("导购类目cndir");
        row.createCell(colunIndex++).setCellValue("导购类目状态(0关闭，1开启)");
    }

    public static void writeG2C(List<Guide2CateDto> datas, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex++);
        setG2CHeader(row);

        for(Guide2CateDto g2c:datas) {
            row = sheet.createRow(rowIndex++);
            int columnIndex = 0;
            CateMsg guideMsg = g2c.getGuideMsg();
            row.createCell(columnIndex++).setCellValue(guideMsg.getCateId());
            row.createCell(columnIndex++).setCellValue(guideMsg.getCateName());
            row.createCell(columnIndex++).setCellValue(guideMsg.getCateLevel());
            row.createCell(columnIndex++).setCellValue(guideMsg.getCateCndir());
            row.createCell(columnIndex++).setCellValue(guideMsg.getCateStatus());

            CateMsg cateMsg = g2c.getCateMsg();
            if(cateMsg!=null) {
                row.createCell(columnIndex++).setCellValue("");
                row.createCell(columnIndex++).setCellValue(cateMsg.getCateId());
                row.createCell(columnIndex++).setCellValue(cateMsg.getCateName());
                row.createCell(columnIndex++).setCellValue(cateMsg.getCateLevel());
                row.createCell(columnIndex++).setCellValue(cateMsg.getCateCndir());
                row.createCell(columnIndex++).setCellValue(cateMsg.getCateStatus());
            }
        }

        //workbook.setSheetName(0, "信息");
        try {
            File file = new File(PATH + name);
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setG2CHeader(Row row) {
        int colunIndex = 0;
        row.createCell(colunIndex++).setCellValue("导购类目id");
        row.createCell(colunIndex++).setCellValue("导购类目名");
        row.createCell(colunIndex++).setCellValue("导购类目层级");
        row.createCell(colunIndex++).setCellValue("导购类目cndir");
        row.createCell(colunIndex++).setCellValue("导购类目状态(0关闭，1开启)");
        row.createCell(colunIndex++).setCellValue(" ");
        row.createCell(colunIndex++).setCellValue("基础类目id");
        row.createCell(colunIndex++).setCellValue("基础类目名");
        row.createCell(colunIndex++).setCellValue("基础类目层级");
        row.createCell(colunIndex++).setCellValue("基础类目cndir");
        row.createCell(colunIndex++).setCellValue("基础类目状态(0关闭，1开启)");
    }

    public static void writeP2C(List<CateMsg> datas, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex++);
        //setG2CHeader(row);

        for(CateMsg pubMsg:datas) {
            row = sheet.createRow(rowIndex++);
            int columnIndex = 0;
            row.createCell(columnIndex++).setCellValue(pubMsg.getCateId());
            row.createCell(columnIndex++).setCellValue(pubMsg.getCateName());
            row.createCell(columnIndex++).setCellValue(pubMsg.getCateLevel());
            row.createCell(columnIndex++).setCellValue(pubMsg.getCateStatus());
        }

        //workbook.setSheetName(0, "信息");
        try {
            File file = new File(PATH + name);
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
