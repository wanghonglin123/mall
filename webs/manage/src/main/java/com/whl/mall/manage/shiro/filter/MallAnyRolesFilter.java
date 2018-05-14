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
package com.whl.mall.manage.shiro.filter;

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallUrlConstants;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.core.common.utils.MallWebUtils;
import com.whl.mall.core.log.MallLog4jLog;
import com.whl.mall.interfaces.member.MemberService;
import com.whl.mall.interfaces.member.MenuService;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.MenuTree;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName: TimoAnyRolesFilter
 * @Description:    Shiro 权限过滤器
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2017/11/22
 */
public class MallAnyRolesFilter extends AccessControlFilter {
    private Short status = null;

    @Autowired
    private MallLog4jLog log4jLog;

    public MallAnyRolesFilter() {
    }

    /**
     * 判断是否允许访问， true 允许， flase 不允许
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = null; Session session = null;
        try {
            subject = getSubject(servletRequest, servletResponse);
            if (subject.getPrincipal() == null) {
                return failHandle();
            }
            session = subject.getSession(false);
            Member member = (Member) session.getAttribute("session_member");
            if (member == null) {
                return failHandle();
            }
            // 调用MallShiroRealm.doGetAuthorizationInfo() 进行授权和存放一些信息
            subject.hasRole(MallMessage.SUPPER_NAME);

            String requestURI = getPathWithinApplication(servletRequest);
            if (requestURI.equals(MallUrlConstants.INDEX_URL)) { // 登录成功页直接放过
                return true;
            }

            // 初始化访问的菜单
            List<MenuTree> menuTreeList = (List<MenuTree>) session.getAttribute("session_menuJson");
            // 获取可以访问菜单所有的Url
            List<String> urlMappingList = (List<String>) session.getAttribute("session_urlMapping");
            int size = urlMappingList.size();
            for (int i = 0; i < size; i++) {
                if (pathMatcher.matches(urlMappingList.get(i), requestURI)) {
                    return true;
                }
            }
        } catch (Exception e) { // 直接退出
            if (subject != null) {
                subject.logout();
            }
            status = 3;
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  如果isAccessAllowed返回false, 验证失败逻辑处理
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        boolean isAjax = MallWebUtils.isAjax(servletRequest);
        if (status == 1) {
            if (isAjax) {
                MallWebUtils.out(servletResponse, log4jLog, 1, "当前用户已失效，请重新登录");
            } else {
                WebUtils.issueRedirect(servletRequest, servletResponse, MallUrlConstants.LOGIN_URL);
            }
        } else if(status == null) {
            //
            if (StringUtils.hasText(MallUrlConstants.UNAUTHORIZED_URL)) {
                if (isAjax) {
                    MallWebUtils.out(servletResponse, log4jLog, 2, "您没有该请求的权限，请联系管理员");
                } else {
                    // 如果有未授权页面跳转过去
                    WebUtils.issueRedirect(servletRequest, servletResponse, MallUrlConstants.UNAUTHORIZED_URL);
                }
            } else {
                // 否则返回401未授权状态码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else if(status == 3) {
            // 如果有未授权页面跳转过去
            WebUtils.issueRedirect(servletRequest, servletResponse, MallUrlConstants.ERROR_URL);
        }
        return false;
    }

    private boolean failHandle() {
        status = 1;
        return false;
    }
}
