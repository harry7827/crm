package com.crm.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.domain.User;
import com.crm.service.CustomerService;
import com.crm.service.UserService;
import com.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends BaseAction<Customer> {
	@Autowired
	private CustomerService customerService;
	
	public String saveUI() {
		return "saveUI";
	}
	
	public String save() throws IOException {
		if(upload != null){
			String path=UploadUtils.getPath();
			String fileName=UploadUtils.getUUIDFileName(uploadFileName);
			File file=new File("f:/upload");
			File realPath=new File(file, path);
			if (!realPath.exists()) {
				realPath.mkdirs();
			}
			File destFile=new File(file, path+"/"+fileName);
			FileUtils.copyFile(upload,destFile);
			model.setCust_image(destFile.toString());
		}
		customerService.save(model);
		return "success";
	}
	public String delete() throws IOException {
		Customer c=customerService.findById(model.getCust_id());
		String cust_image = c.getCust_image();
		if (!StringUtils.isBlank(cust_image)) {
			File imageFile=new File(cust_image);
			if (imageFile.exists()) {
				imageFile.delete();
			}
		}
		customerService.delete(c);
		return "success";
	}
	
	public String editUI(){
		Customer c=customerService.findById(model.getCust_id());
		ServletActionContext.getContext().getValueStack().push(c);
		return "editUI";
	}
	public String update() throws IOException{
		if(upload != null){
			String cust_image = model.getCust_image();
			//上传新图片
			String path=UploadUtils.getPath();
			String fileName=UploadUtils.getUUIDFileName(uploadFileName);
			File file=new File("f:/upload");
			File realPath=new File(file, path);
			if (!realPath.exists()) {
				realPath.mkdirs();
			}
			File destFile=new File(file, path+"/"+fileName);
			FileUtils.copyFile(upload,destFile);
			model.setCust_image(destFile.toString());
			//删除相关图片
			if (!StringUtils.isBlank(cust_image)) {
				File imageFile=new File(cust_image);
				if (imageFile.exists()) {
					imageFile.delete();
				}
			}
		}
		customerService.update(model);
		return "success";
	}
	public String findByPage() {
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		if (model!=null&&StringUtils.isNotBlank(model.getCust_name())) {
			String cust_name = model.getCust_name();
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		if (model.getBaseDictIndustry()!=null&&StringUtils.isNotBlank(model.getBaseDictIndustry().getDict_id())) {
			String dict_id=model.getBaseDictIndustry().getDict_id();
			criteria.add(Restrictions.eq("baseDictIndustry.dict_id", dict_id));
		}
		if (model.getBaseDictLevel()!=null&&StringUtils.isNotBlank(model.getBaseDictLevel().getDict_id())) {
			String dict_id=model.getBaseDictLevel().getDict_id();
			criteria.add(Restrictions.eq("baseDictLevel.dict_id", dict_id));
		}
		if (model.getBaseDictSource()!=null&&StringUtils.isNotBlank(model.getBaseDictSource().getDict_id())) {
			String dict_id=model.getBaseDictSource().getDict_id();
			criteria.add(Restrictions.eq("baseDictSource.dict_id", dict_id));
		}
		PageBean<Customer> pageBean = customerService.findByPage(criteria,currPage,pageSize);
		ServletActionContext.getContext().getValueStack().push(pageBean);
		return "findByPage";
	}
	private InputStream inputStream;
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public String download() throws Exception{
		Customer c=customerService.findById(model.getCust_id());
		String image = c.getCust_image();
		File file=new File(image);
		int indexOf = image.lastIndexOf("\\");
		String fileName = image.substring(indexOf+1);
		inputStream=new FileInputStream(file);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("contentType",ServletActionContext.getServletContext().getMimeType(fileName));
		request.setAttribute("filename", fileName);
		return "download";
	}
	
	public String findAll(){
		List<Customer> list = customerService.findAll();
		ServletActionContext.getContext().getValueStack().push(list);
		return "list";
	}

}
