//package com.example.controller;
//
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.w3c.tidy.Tidy;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class PdfController
//{
//    private static final String UTF_8 = "UTF-8";
//
//    public void generatePdf() throws Exception
//    {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//        templateResolver.setCharacterEncoding(UTF_8);
//
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//
//        Context context = new Context();
//        String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
//        date = "Kraków, " + date;
//        context.setVariable("date", date);
//
//        String html = templateEngine.process("templates/pdf", context);
//        String xHtml = convertToXhtml(html);
//
//
//        OutputStream outputStream = new FileOutputStream("raport.pdf");
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(xHtml);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//        outputStream.close();
//    }
//
//    private Data exampleDataForJohnDoe() {
//        Data data = new Data();
//        data.setFirstname("John");
//        data.setLastname("Doe");
//        data.setStreet("Example Street 1");
//        data.setZipCode("12345");
//        data.setCity("Example City");
//        return data;
//    }
//
//    static class Data {
//        private String firstname;
//        private String lastname;
//        private String street;
//        private String zipCode;
//        private String city;
//
//        public String getFirstname() {
//            return firstname;
//        }
//
//        public void setFirstname(String firstname) {
//            this.firstname = firstname;
//        }
//
//        public String getLastname() {
//            return lastname;
//        }
//
//        public void setLastname(String lastname) {
//            this.lastname = lastname;
//        }
//
//        public String getStreet() {
//            return street;
//        }
//
//        public void setStreet(String street) {
//            this.street = street;
//        }
//
//        public String getZipCode() {
//            return zipCode;
//        }
//
//        public void setZipCode(String zipCode) {
//            this.zipCode = zipCode;
//        }
//
//        public String getCity() {
//            return city;
//        }
//
//        public void setCity(String city) {
//            this.city = city;
//        }
//    }
//
//    private String convertToXhtml(String html) throws UnsupportedEncodingException {
//        Tidy tidy = new Tidy();
//        tidy.setInputEncoding(UTF_8);
//        tidy.setOutputEncoding(UTF_8);
//        tidy.setXHTML(true);
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        tidy.parseDOM(inputStream, outputStream);
//        return outputStream.toString(UTF_8);
//    }
//}
