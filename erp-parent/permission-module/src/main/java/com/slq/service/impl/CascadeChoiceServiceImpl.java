package com.slq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.slq.common.CascadeChoice;
import com.slq.common.Template;
import com.slq.constant.Constant;
import com.slq.dao.ICascadeChoiceDao;
import com.slq.mapper.CascadeChoiceMapper;
import com.slq.service.ICascadeChoiceService;
import com.slq.util.FileUtil;
@CacheConfig(cacheNames = "myCache")
@Service
public class CascadeChoiceServiceImpl implements ICascadeChoiceService{

	/***
	 * redis缓存的key
	 */
	//权限的存储数据Id
	private String CASCADE_MENU_PERMISSION_KEY="cascade_menu_permission";

	@Autowired
	private CascadeChoiceMapper cascadeChoiceMapper;
	@Autowired
	private ICascadeChoiceDao cascadeChoiceDao;
	@Override
	public List<CascadeChoice> getCascadePermission() {
//		String key=CASCADE_MENU_PERMISSION_KEY;
//		List<CascadeChoice> cascadeChoices= cascadeChoiceDao.getCascadeChoicePermissionCache(key);
//		if(cascadeChoices==null||cascadeChoices.isEmpty()) {
//			cascadeChoices = cascadeChoiceMapper.getCascadePermission();
//			boolean fals = cascadeChoiceDao.setCascadeChoicePermissionCache(key, cascadeChoices);
//		}
		List<CascadeChoice> cascadeChoices=cascadeChoiceMapper.getCascadePermission();
		// TODO Auto-generated method stub
		return cascadeChoices;
	}
	@Cacheable(keyGenerator = "keyGenerator")
	@Override
	public List<Template> getCascadeTemplates() {
		return FileUtil.getTemplates();
	}

	
}
