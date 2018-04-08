package com.whl.mall.manage.controller.member;
/**
 * @Title: member
 * @Package: com.whl.mall.manage.controller
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 * @Version: V2.0.0
 */

import com.whl.mall.common.MallAjaxException;
import com.whl.mall.common.MallResult;
import com.whl.mall.common.constants.MallStatus;
import com.whl.mall.interfaces.member.MemberService;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @ClassName: member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 */
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 进入登陆页
     *
     * @param model model
     * @return
     * @throws Exception Exception
     */
    @RequestMapping("/toLogin")
    public String login(Model model) throws MallAjaxException{
        return "member/member/login";
    }

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping("/")
    public String register() {
        return "member/member/index";
    }

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
     * @param member
     * @return
     */
    @RequestMapping("/member/do-save")
    @ResponseBody
    public MallResult doSave(Member member) {
        memberService.save(member);
        return MallResult.ok();
    }
}
