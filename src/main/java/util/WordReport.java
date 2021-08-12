package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.aventstack.extentreports.Status;

public class WordReport {
	String reportpath;
	String reportName;
	Map<String, String> wordReportImages;
	String documentname;
	
public WordReport(String reportpath,String reportName, Map<String, String> wordReportImages) {
	this.reportName=reportName;
	this.wordReportImages=wordReportImages;
	this.reportpath=reportpath;
	 
	
}


public void createWordReport(Status status) {
	try {
	createWordDocument(status);
	XWPFDocument doc = new XWPFDocument(); 
	OutputStream os = new FileOutputStream(documentname);
	 XWPFParagraph paragraph = doc.createParagraph(); 
	 XWPFRun run = paragraph.createRun(); 
	 
	 for(Map.Entry<String, String> map:wordReportImages.entrySet()) {
		 run.addBreak();
		  run.addBreak();
		  run.addBreak();
		  run.setText(map.getKey());
		  run.addBreak();
		  run.addBreak();
		  run.addBreak();
		  run.addPicture(new FileInputStream(new File(map.getValue())), XWPFDocument.PICTURE_TYPE_JPEG, map.getValue(), Units.toEMU(500), Units.toEMU(300));
	 }
	 doc.write(os);  
     os.close();
     doc.close();
	
	}catch(Exception e){
		e.printStackTrace();
	}
	
}

public void createWordDocument(Status status) throws IOException {
	 documentname = reportpath + reportName + status.name() + new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date()) + ".docx";
	File file = new File(documentname);
	
		file.createNewFile();
	
		
}

}
