package com.ymd.ddshop.service;

import com.ymd.ddshop.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentId);
}
