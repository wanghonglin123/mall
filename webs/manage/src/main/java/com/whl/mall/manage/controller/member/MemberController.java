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
import com.whl.mall.core.MallResult;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.pojo.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "member/member/list";
    }

    /**
     * 进入新增成员页面
     *
     * @return
     */
    @RequestMapping("/member/toAddMember")
    public String toAddMember() {
        return "member/member/addMemberInfo";
    }

    /**
     * 新增或者修改
     *
     * @param po
     * @return
     */
    @RequestMapping("/member/do-save")
    @ResponseBody
    public MallResult doSave(Member po) throws MallException{
        super.getMemberService().save(po);
        return MallResult.ok();
    }
}
