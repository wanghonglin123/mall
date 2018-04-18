package com.whl.mall.manage.controller.role;
/**
 * @Title: RoleController
 * @Package: com.whl.mall.manage.controller.role
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallResult;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Menu;
import com.whl.mall.pojo.member.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: RoleController
 * @Description: 角色Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 */
@Controller
public class RoleController extends MallBaseController {
    /**
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/role/toList")
    public String toList() {
        return "member/role/rolelist";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/role/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/role/saveOrEditOrViewRole";
    }

    /**
     * 新增或者修改
     *
     * @param po
     * @return
     */
    @RequestMapping("/role/do-saveOrEdit")
    @ResponseBody
    public MallResult saveOrEdit(Role po) throws Exception{
        super.getRoleService().save(po);
        return MallResult.ok();
    }
}
