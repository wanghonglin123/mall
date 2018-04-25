package com.whl.mall.manage.controller.member;
/**
 * @Title: ResourceGroupController
 * @Package: com.whl.mall.manage.controller.member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:26
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Resource;
import com.whl.mall.pojo.member.ResourceGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/resourceGroup/toChooseResourceGroup/{roleIdx}")
    public String toChooseResourceGroup(@PathVariable String roleIdx, HttpServletRequest request) throws Exception{
        String resourceGroupIdx = request.getParameter("resourceGroupIdx");
        String[] resourceGroupIdxs = null;
        if (StringUtils.isNoneEmpty(resourceGroupIdx)) {
            resourceGroupIdxs = resourceGroupIdx.split(",");
        }
        request.setAttribute("resourceGroupIdxsJson", MallJsonUtils.objectToJson(resourceGroupIdxs));
        return "member/resourceGroup/chooseResourceGroup";
    }

    /**
     * 操作 1：新增 2：编辑 3：查看 4：删除
     *
     * @param po po
     * @return
     */
    @RequestMapping("/resourceGroup/operation/{type}")
    @ResponseBody
    public MallResult operation(@PathVariable Short type, ResourceGroup po, String[] menuStr, String[] buttonsStr) throws Exception{
        if (type == MallNumberConstants.ONE) { // 新增
            po = super.getResourceGroupService().save(po);
            long resourcesGroupIdxCode = po.getIdxCode();
            Resource resource = null;
            Long idxCode = null;
            for (String menu : menuStr) {
                idxCode = Long.valueOf(menu);
                resource = new Resource();
                resource.setResourceGroupIdxCode(resourcesGroupIdxCode);
                resource.setMenuButtonIdxCode(idxCode);
                resource.setResourceType(MallNumberConstants.ONE);
                getResourceService().save(resource);
            }

            for (String button : buttonsStr) {
                idxCode = Long.valueOf(button);
                resource = new Resource();
                resource.setResourceGroupIdxCode(resourcesGroupIdxCode);
                resource.setMenuButtonIdxCode(idxCode);
                resource.setResourceType(MallNumberConstants.TWO);
                getResourceService().save(resource);
            }
            return MallResult.ok();
        }

        Long idxCode = po.getIdxCode();
        if (idxCode == null) {
            return MallResult.build(MallStatus.HTTP_STATUS_400, MallMessage.CONTROLLER_INVALID_PARAMETER);
        }
        if (type == MallNumberConstants.TWO) {
            super.getResourceGroupService().update(po);
        } else if (type == MallNumberConstants.THREE) {
            return MallResult.ok(super.getResourceGroupService().queryOneSomeInfoByCondition(po));
        } else {
            return MallResult.ok(super.getResourceGroupService().delete(po));
        }
        return MallResult.ok();
    }

    /**
     * 跳转到操作页码 1：新增 2：编辑 3：查看
     *
     * @param idxCode idxCode
     * @return
     */
    @RequestMapping("/resourceGroup/toOperation/{type}/{idxCode}")
    public String toOperation(@PathVariable String type, @PathVariable Long idxCode, HttpServletRequest request) throws Exception{
        request.setAttribute("type", type);

        if ("edit".equals(type) || "see".equals(type)) {
            ResourceGroup po = new ResourceGroup();
            po.setIdxCode(idxCode);
            po = getResourceGroupService().queryOneSomeInfoByCondition(po);
            request.setAttribute("obj", po);
        }
        return "member/resourceGroup/saveOrEditOrViewResourceGroup";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/resourceGroup/paging")
    @ResponseBody
    public MallGridResult paging(ResourceGroup po, Integer page, Integer rows, String order) throws Exception {
        return getResourceGroupService().queryPageDataByCondition(po, page, rows, order);
    }
}
