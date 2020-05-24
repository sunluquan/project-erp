package com.slq.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.slq.common.Template;

public class FileUtil {

	private static String templatePath="C:\\Users\\孙陆泉\\Desktop\\总模块\\erp\\src\\templates";
	  
	public static List<Template> getTemplates() {
		List<Template> templates=new ArrayList<Template>();
		File file=new File(templatePath);
		File [] files = file.listFiles();
		setTemplates(files,templates);
		return templates;
	}
	private static void setTemplates(File [] files,List<Template> templates) {
		if(files!=null&&files.length>0) {
			 //template=null;
			if(templates==null)templates=new ArrayList<Template>();
			for (File file : files) {
				Template template=new Template(file.getName());
				templates.add(template);
				setTemplates(file.listFiles(),template.initChildren());
			}
		}
	}
}
