package com.whl.mall.manage.controller.member;
/**
 * @Title: ResourceGroupController
 * @Package: com.whl.mall.manage.controller.member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallResult;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Menu;
import com.whl.mall.pojo.member.ResourceGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ResourceGroupController
 * @Description: 资源组Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 */
@Controller
public class ResourceGroupController extends MallBaseController {
    /**
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/resourceGroup/toList")
    public String toList() {
        return "member/resourceGroup/resourceGrouplist";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/resourceGroup/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/resourceGroup/saveOrEditOrViewResourceGroup";
    }

    /**
     * 新增或者修改
     *
     * @param po
     * @return
     */
    @RequestMapping("/resourceGroup/do-saveOrEdit")
    @ResponseBody
    public MallResult saveOrEdit(ResourceGroup po) throws Exception{
        super.getResourceGroupService().save(po);
        return MallResult.ok();
    }
}
