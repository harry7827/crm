package com.crm.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.crm.domain.BaseDict;
import com.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseDictAction extends BaseAction<BaseDict>{
	@Autowired
	private BaseDictService baseDictService;

	public String findByTypeCode() throws Exception {
		String dict_type_code=model.getDict_type_code();
		List<BaseDict> baseDicts=baseDictService.findByTypeCode(dict_type_code);
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter();
		filter.getIncludes().add("dict_id");
		filter.getIncludes().add("dict_item_name");
		String jsonString=JSONObject.toJSONString(baseDicts,filter);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(jsonString);
		return "none";
	}


}
