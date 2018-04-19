package com.whl.mall.manage.controller.member;
/**
 * @Title: MemberCatController
 * @Package: com.whl.mall.manage.controller.member
 * @Description: 成员类别
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:23
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Button;
import com.whl.mall.pojo.member.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: MemberCatController
 * @Description: 用户类别Controller
 * @Author: WangHongLin
 * @Date: 2018-04-08 下午 11:23
 */
@Controller
public class MemberCateController extends MallBaseController {
    /**
     * 进入列表
     *
     * @return
     */
    @RequestMapping("/memberCate/toList")
    public String toList() {
        return "member/memberCate/memberCatelist";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping("/memberCate/{type}/{idx}")
    public String toAddOrEditOrSee(@PathVariable String type, @PathVariable Long idx) {
        return "member/memberCate/saveOrEditOrViewMemberCate";
    }

    /**
     * 操作 1：新增 2：编辑 3：查看 4：删除
     *
     * @param po po
     * @return
     */
    @RequestMapping("/memberCate/operation/{type}")
    @ResponseBody
    public MallResult operation(@PathVariable Short type, Button po) throws Exception{
        if (type == MallNumberConstants.ONE) { // 新增
            super.getButtonService().save(po);
        }

        Long idxCode = po.getIdxCode();
        if (idxCode == null) {
            return MallResult.build(MallStatus.HTTP_STATUS_400, MallMessage.CONTROLLER_INVALID_PARAMETER);
        }
        if (type == MallNumberConstants.TWO) {
            super.getButtonService().update(po);
        } else if (type == MallNumberConstants.THREE) {
            return MallResult.ok(super.getButtonService().queryOneSomeInfoByCondition(po));
        } else {
            return MallResult.ok(super.getButtonService().delete(po));
        }
        return MallResult.ok();
    }

    /**
     * 跳转到操作页码 1：新增 2：编辑 3：查看
     *
     * @param po po
     * @return
     */
    @RequestMapping("/memberCate/toOperation/{type}")
    public String toOperation(@PathVariable Short type, Button po, HttpServletRequest request) throws Exception{
        if (type == MallNumberConstants.THREE) {
            request.setAttribute("type", "see");
        }
        if (type == MallNumberConstants.THREE || type == MallNumberConstants.TWO) {
            Button memberCate = getButtonService().queryOneSomeInfoByCondition(po);
            request.setAttribute("obj", memberCate);
        }
        return "/memberCate/saveOrEditOrViewButton";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/memberCate/paging")
    @ResponseBody
    public MallGridResult paging(Button po, Integer number, Integer rows, String order) throws MallException {
        return getButtonService().queryPageDataByCondition(po, number, rows, order);
    }
}
