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
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: member
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-02 下午 9:23
 */
@Controller
public class MemberController {
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
}
