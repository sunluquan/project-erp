package com.slq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slq.common.CascadeChoice;
@Mapper
public interface CascadeChoiceMapper {

	List<CascadeChoice> getCascadePermission();

	List<CascadeChoice> getCascadePermissionChild(Integer parentId);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6

	List<CascadeChoice> getCascadeProductKind();
	
	List<CascadeChoice> getKindChildren(Integer id);
<<<<<<< HEAD
=======
>>>>>>> warehouse
=======
>>>>>>> 225d5e24f3d21f7d9440ccec2cddb462756396d6
}
