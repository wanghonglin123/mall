package com.whl.mall.manage.controller.member;
/**
 * @Title: ResourceGroupController
 * @Package: com.whl.mall.manage.controller.member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ResourceGroupController
 * @Description: 资源组Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 */
@Controller
public class ResourceGroupController {
    /**
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/authManage/toList")
    public String toList() {
        return "member/resourceGroup/resourceGrouplist";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/authManage/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/resourceGroup/saveOrEditOrViewResourceGroup";
    }
}
