package com.whl.mall.manage.controller.member; /**
 * @Title: RoleController
 * @Package: com.whl.mall.manage.controller.role
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Menu;
import com.whl.mall.pojo.member.ResourceGroupRole;
import com.whl.mall.pojo.member.Role;
import com.whl.mall.pojo.member.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
        return "/member/role/rolelist";
    }

    /**
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/role/toChooseRolelist/{idx}")
    public String toChooseRolelist(@PathVariable String idx, HttpServletRequest request) throws MallException{
        String roleIdx = request.getParameter("roleIdx");
        String[] roles = null;
        if (StringUtils.isNoneEmpty(roleIdx)) {
            roles = roleIdx.split(",");
        }
        request.setAttribute("roleIdxListJson", MallJsonUtils.objectToJson(roles));
        return "/member/role/chooseRolelist";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/role/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "/member/role/saveOrEditOrViewRole";
    }

    /**
     * 操作 1：新增 2：编辑 3：查看 4：删除
     *
     * @param po po
     * @return
     */
    @RequestMapping("/role/operation/{type}")
    @ResponseBody
    public MallResult operation(@PathVariable Short type, Role po, HttpServletRequest request) throws Exception{
        if (type == MallNumberConstants.ONE) { // 新增
            po = super.getRoleService().save(po);
            Long roleIdx = po.getIdx();
            String resourceGroupIdxStr = request.getParameter("resourceGroupIdxs");
            String[] resourceGroupIdxs = null;
            if (StringUtils.isNoneEmpty(resourceGroupIdxStr)) {
                resourceGroupIdxs = resourceGroupIdxStr.split(",");
                Long idx = null;
                ResourceGroupRole resourceGroupRole = null;
                for (String resourceGroupIdx : resourceGroupIdxs) {
                    resourceGroupRole = new ResourceGroupRole();
                    idx = Long.valueOf(resourceGroupIdx);
                    resourceGroupRole.setRoleIdxCode(roleIdx);
                    resourceGroupRole.setResourceGroupIdxCode(idx);
                    super.getResourceGroupRoleService().save(resourceGroupRole);
                }
            }
            return MallResult.ok();
        }

        Long idxCode = po.getIdxCode();
        if (idxCode == null) {
            return MallResult.build(MallStatus.HTTP_STATUS_400, MallMessage.CONTROLLER_INVALID_PARAMETER);
        }
        if (type == MallNumberConstants.TWO) {
            super.getRoleService().update(po);
        } else if (type == MallNumberConstants.THREE) {
            return MallResult.ok(super.getRoleService().queryOneSomeInfoByCondition(po));
        } else {
            return MallResult.ok(super.getRoleService().delete(po));
        }
        return MallResult.ok();
    }

    /**
     * 跳转到操作页码 1：新增 2：编辑 3：查看
     *
     * @return
     */
    @RequestMapping("/role/toOperation/{type}/{idxCode}")
    public String toOperation(@PathVariable String type, @PathVariable Long idxCode, HttpServletRequest request) throws Exception{
        request.setAttribute("type", type);

        if ("edit".equals(type) || "see".equals(type)) {
            Role po = new Role();
            po.setIdxCode(idxCode);
            po = getRoleService().queryOneSomeInfoByCondition(po);
            request.setAttribute("obj", po);
        }
        return "/role/saveOrEditOrViewRole";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/role/paging")
    @ResponseBody
    public MallGridResult paging(Role po, Integer page, Integer rows, String order) throws MallException {
        return getRoleService().queryPageDataByCondition(po, page, rows, order);
    }
}
