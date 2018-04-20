package com.whl.mall.manage.controller.member;
/**
 * @Title: member
 * @Package: com.whl.mall.manage.controller
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 */
@Controller
public class MemberController extends MallBaseController{
    /**
     * 进入成员列表
     *
     * @return
     */
    @RequestMapping("/member/toList")
    public String toList() {
        return "/member/member/list";
    }

    /**
     * 操作 1：新增 2：编辑 3：查看 4：删除
     *
     * @param po po
     * @return
     */
    @RequestMapping("/member/operation/{type}")
    @ResponseBody
    public MallResult operation(@PathVariable Short type, Member po) throws Exception{
        if (type == MallNumberConstants.ONE) { // 新增
            super.getMemberService().save(po);
        }

        Long idxCode = po.getIdxCode();
        if (idxCode == null) {
            return MallResult.build(MallStatus.HTTP_STATUS_400, MallMessage.CONTROLLER_INVALID_PARAMETER);
        }
        if (type == MallNumberConstants.TWO) {
            super.getMemberService().update(po);
        } else if (type == MallNumberConstants.THREE) {
            return MallResult.ok(super.getMemberService().queryOneSomeInfoByCondition(po));
        } else {
            return MallResult.ok(super.getMemberService().delete(po));
        }
        return MallResult.ok();
    }

    /**
     * 跳转到操作页码 1：新增 2：编辑 3：查看
     *
     * @return
     */
    @RequestMapping("/member/toOperation/{type}/{idxCode}")
    public String toOperation(@PathVariable String type, @PathVariable Long idxCode, HttpServletRequest request) throws Exception{
        request.setAttribute("type", type);

        if ("edit".equals(type) || "see".equals(type)) {
            Member po = new Member();
            po.setIdxCode(idxCode);
            po = getMemberService().queryOneSomeInfoByCondition(po);
            request.setAttribute("obj", po);
        }
        return "/member/member/addMemberInfo";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/member/paging")
    @ResponseBody
    public MallGridResult paging(Member po, Integer number, Integer rows, String order) throws MallException {
        return getMemberService().queryPageDataByCondition(po, number, rows, order);
    }
}
