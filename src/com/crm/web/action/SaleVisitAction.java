package com.crm.web.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;
import com.crm.service.CustomerService;
import com.crm.service.SaleVisitService;
import com.crm.service.UserService;

public class SaleVisitAction extends BaseAction<SaleVisit> {
	@Autowired
	private SaleVisitService saleVisitService;

	private Date visit_end_time;
	
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	public String saveUI() {
		return "saveUI";
	}
	public String save() {
		saleVisitService.save(model);
		return "findAll";
	}
	public String delete() {
		model = saleVisitService.findById(model.getVisit_id());
		saleVisitService.delete(model);
		return "findAll";
	}
	public String edit() {
		SaleVisit saleVisit = saleVisitService.findById(model.getVisit_id());
		ServletActionContext.getContext().getValueStack().push(saleVisit);
		return "editUI";
	}
	public String update() {
		saleVisitService.update(model);
		return "findAll";
	}
	public String findByPage() {
		DetachedCriteria criteria=DetachedCriteria.forClass(SaleVisit.class);
		if (model.getVisit_time()!=null) {
			criteria.add(Restrictions.ge("visit_time", model.getVisit_time()));
		}
		if (visit_end_time!=null) {
			criteria.add(Restrictions.le("visit_time",visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(criteria,currPage,pageSize);
		ServletActionContext.getContext().getValueStack().push(pageBean);
		return "findByPage";
	}
}
