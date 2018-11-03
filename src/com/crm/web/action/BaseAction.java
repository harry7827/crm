package com.crm.web.action;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	protected T model;
	public BaseAction() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType p=(ParameterizedType) type;
		Class clazz=(Class) p.getActualTypeArguments()[0];
		try {
			model=(T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public T getModel() {
		return model;
	}
	
	protected Integer currPage=1;
	protected Integer pageSize=3;
	
	public void setCurrPage(Integer currPage) {
		if (currPage==null) {
			this.currPage=1;
		}else {
			this.currPage = currPage;
		}
	}
	public void setPageSize(Integer pageSize) {
		if (pageSize==null) {
			this.pageSize=3;
		}else {
			this.pageSize = pageSize;
		}
	}

	protected File upload;
	protected String uploadFileName;
	protected String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
}
