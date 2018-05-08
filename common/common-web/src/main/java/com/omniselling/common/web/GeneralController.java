package com.omniselling.common.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.omniselling.common.model.BasePageDetail;

public class GeneralController {

	
	protected ResponseEntity<byte[]> excelWorkBookToResponseEntity(Workbook wb, String fileName ) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		HttpHeaders headers = new HttpHeaders();  
		headers.setContentType(new MediaType("application","x-excel"));
		headers.setContentDispositionFormData("attachment", fileName);  
		return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);                                                    
	}
	
	protected <T> BasePageDetail<T> productViewPageFromBizPage(BasePageDetail<?> bizBasePageDetail, Class<T> t_class) {
		BasePageDetail<T> destBasePageDetail = new BasePageDetail<T>();
		List<T> destList = new ArrayList<T>();
		destBasePageDetail.setDatas(destList);
		destBasePageDetail.setOffset(bizBasePageDetail.getOffset());
		destBasePageDetail.setRowsPerPage(bizBasePageDetail.getRowsPerPage());
		destBasePageDetail.setTotal(bizBasePageDetail.getTotal());
		return destBasePageDetail;
	}
	
}
