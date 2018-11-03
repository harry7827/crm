package com.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.domain.Customer;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends BaseAction<LinkMan> {
	
	@Autowired
	private LinkManService linkManService;
	@Autowired
	private CustomerService customerService;
	public String saveUI() {
		List<Customer> list = customerService.findAll();
		ServletActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	public String save(){
		linkManService.save(model);
		return "success";
	}
	public String edit() {
		return "saveUI";
	}
	
	public String delete(){
		LinkMan lm=linkManService.findById(model.getLkm_id());
		linkManService.delete(lm);
		return "success";
	}
	public String update() {
		linkManService.update(model);
		return "success";
	}
	public String editUI(){
		LinkMan lm=linkManService.findById(model.getLkm_id());
		ServletActionContext.getContext().getValueStack().push(lm);
		List<Customer> list = customerService.findAll();
		ServletActionContext.getContext().getValueStack().set("list", list);
		return "editUI";
	}
	
	public String findByPage() {
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		if (model!=null&&StringUtils.isNotBlank(model.getLkm_name())) {
			String lkm_name = model.getLkm_name();
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		/*if (LinkMan.getBaseDictIndustry()!=null&&StringUtils.isNotBlank(LinkMan.getBaseDictIndustry().getDict_id())) {
			String dict_id=LinkMan.getBaseDictIndustry().getDict_id();
			criteria.add(Restrictions.eq("baseDictIndustry.dict_id", dict_id));
		}
		if (LinkMan.getBaseDictLevel()!=null&&StringUtils.isNotBlank(customer.getBaseDictLevel().getDict_id())) {
			String dict_id=customer.getBaseDictLevel().getDict_id();
			criteria.add(Restrictions.eq("baseDictLevel.dict_id", dict_id));
		}
		if (customer.getBaseDictSource()!=null&&StringUtils.isNotBlank(customer.getBaseDictSource().getDict_id())) {
			String dict_id=customer.getBaseDictSource().getDict_id();
			criteria.add(Restrictions.eq("baseDictSource.dict_id", dict_id));
		}*/
		PageBean<LinkMan> pageBean = linkManService.findByPage(criteria,currPage,pageSize);
		ServletActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}
}
