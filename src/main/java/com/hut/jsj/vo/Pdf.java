package com.hut.jsj.vo;

import com.hut.jsj.dto.PdfData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pdf {
    private User user;
    private PdfData pdfData;
}
