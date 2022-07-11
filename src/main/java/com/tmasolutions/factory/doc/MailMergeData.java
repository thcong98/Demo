/*
package com.tmasolutions.factory.doc;

import java.awt.Dimension;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.reporting.MergeImageFieldEventArgs;
import com.spire.doc.reporting.MergeImageFieldEventHandler;


@Service
public class MailMergeData {
    public static final Locale LOCALE = new Locale("id", "ID");
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMMM-yyyy", LOCALE);
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(LOCALE);
    public String MailMergeData() throws Exception {

        String pdfFileName="D:/Receipt.pdf";
        String[] fieldNames = new String[]{
                "id",
                "hovaten",
                "bophan",
                "chucvu",
                "hinhthucthanhtoan",
                "tendonvithuhuong",
                "sotaikhoan",
                "tenchinhanh",
                "ngaytao",
                "thangtao",
                "namtao"
        };
        String[] fieldValues = new String[]{
                "TTTT00001",*/
/**//*

                "Nguyễn Văn A",
                "Phòng IT",
                "Trường Phòng",
                "Chuyển khoản",
                "Công Ty TNHH ABC",
                "1900267668222",
                "Ngân hàng BIDV Thử Đức",
                "12",
                "2",
                "2022"
        };

        Document document = new Document();
        document.loadFromStream(MailMergeData.class.getResourceAsStream("/templates/docs/bao-cao.docx"), FileFormat.Auto);
        document.getMailMerge().execute(fieldNames, fieldValues);
        document.saveToFile(pdfFileName, FileFormat.PDF);


        return pdfFileName;

    }

}
*/
