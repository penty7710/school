package com.hut.jsj.service;

import com.hut.jsj.dto.PdfData;

import java.text.ParseException;

public interface PdfService {
    public String createPdf(PdfData pdfData) throws ParseException;

    public int insert();

    public String findPdf(String information);

}
