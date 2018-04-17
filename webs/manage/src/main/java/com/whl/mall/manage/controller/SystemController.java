/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package com.whl.mall.manage.controller;/**
 * @Title: SystemController
 * @Package: com.whl.mall.manage.controller
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/4/9
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/4/9
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.MallAjaxException;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.core.common.constants.MallSessionConstants;
import com.whl.mall.core.common.utils.MallBase64Utils;
import com.whl.mall.core.common.utils.MallJdbcUtils;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.ext.controller.MallBaseController;
import com.whl.mall.interfaces.member.MenuService;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.Menu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @ClassName: SystemController
 * @Description: 系统Controller
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/4/9
 */
@Controller
public class SystemController extends MallBaseController{
    /**
     * 进入登陆页
     *
     * @param model model
     * @return
     * @throws Exception Exception
     */
    @RequestMapping("/toLogin")
    public String toLogin(Model model) throws MallAjaxException {
        // 获取访问主体Subject
        Subject subject = SecurityUtils.getSubject();
        // subject.getSession(false) 设置为true，会动态代码创建一个Session,但是这里不需要使用Session，所以设置为false
        Session session = subject.getSession(false);

        // 如果session存在，name判断会话是否存在登录成员信息，因为用户未登录或者登录失败或者退出操作,或者清空Session操作，
        // name会话会清空成员属性，或者不能创建成员属性，只有登录成功才会存在成员属性
        if (session != null && session.getAttribute(MallSessionConstants.SESSION_MEMBER) == null) {
            return "redirect:/";
        }

        return getLoginName();
    }

    /**
     * 获取登录页面名称
     * @return
     */
    private String getLoginName() {
        return "login";
    }

    /**
     * 执行登录
     * @param username 会员名称
     * @param password 密码
     * @return
     * @throws Exception Exception
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public MallResult doLogin(String username, String password) throws Exception{
        Subject subject = SecurityUtils.getSubject();

        try {
            // 登陆验证
            username = MallBase64Utils.decode(username);
            password = MallBase64Utils.decode(password);
            Member member = new Member();
            member.setUserName(username);
            member.setPwd(password);
            member = getMemberService().login(member);
            if (member == null) {
                return MallResult.build(400, "账号或者用户不存在", null);
            }

            // Shiro token唯一标识，登陆成功保存认证信息和授权信息
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MallResult.ok();
    }

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, Menu po) throws Exception{
        //List<Menu> menuList = menuService.queryDataByCondition(po);
        String json = MallJsonUtils.objectToJson("");
        request.setAttribute("menuJson", json);
        return "member/member/index";
    }
}
