package com.bs23;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JasperReportApplication {

  public static void main(String[] args) {
    SpringApplication.run(JasperReportApplication.class, args);

    try {
      String filePath = "D:\\jasper-report\\src\\main\\resources\\Simple_Blue.jrxml";
      String destinationFileWithName = "D:\\jasper-report\\src\\main\\resources\\studentInfo.pdf";
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("studentName", "Rifat");

      Student student1 = new Student(1L, "Khairul", "Islam", "Textile", "Chittagong");
      Student student2 = new Student(2L, "Abdur", "Rahime", "Oxyzen", "Chittagong");
      Student student3 = new Student(3L, "Nazim", "Udding", "Feni", "Chittagong");

      List<Student> field = new ArrayList<>();
      field.add(student1);
      field.add(student2);
      field.add(student3);

      // assigning data to jr data source
      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(field);

      // then file path that means the jrxl file need to compile
      JasperReport report = JasperCompileManager.compileReport(filePath);

      // after filling data into data source and and compile report we need fill all things into
      // JasperFillManger
      JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

      // after filing all data we need to export into perticular format like pdf, excel etc
      JasperExportManager.exportReportToPdfFile(print, destinationFileWithName);

      /*
      If there any uuid problem occurs during generating report then go the jasper studio then
      ---> windows
      ------> preference
      ---------> Jasper Studio
      -----------> Compatibility
      -------------> select version: JasperReports 3.5.1
       */

      System.out.println("Report Created");
    } catch (Exception e) {
e.printStackTrace();
    }
  }
}
