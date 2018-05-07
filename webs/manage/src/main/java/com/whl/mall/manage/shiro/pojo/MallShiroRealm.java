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
package com.whl.mall.manage.shiro.pojo;

/**
 * @Title: TimoShiroSecurityRealm
 * @Package: com.timowang.common.configura.shiro
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2017/11/21
 * @Version: V2.0.10
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2017/11/21
 * @Modify-version: 2.1.5
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallSymbolConstants;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.core.common.utils.MallMd5Utils;
import com.whl.mall.core.log.MallLog4jLog;
import com.whl.mall.core.log.adapter.MallLoggerAdapter;
import com.whl.mall.ext.component.AuthorityComponent;
import com.whl.mall.interfaces.member.MemberRoleService;
import com.whl.mall.interfaces.member.MemberService;
import com.whl.mall.interfaces.member.MenuService;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.MemberRole;
import com.whl.mall.pojo.member.MenuTree;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @ClassName: TimoShiroSecurityRealm
 * @Description: 自定义ShiroSecurityRealm 管理
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2017/11/21
 */
public class MallShiroRealm extends AuthorizingRealm{
    /**
     * 权限组件
     */
    @Autowired
    private AuthorityComponent authorityComponent;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        Session session = authorityComponent.getSession(false);

        // 获取登录成员信息
        Member member = (Member) session.getAttribute("session_member");
        Long userId = member.getIdx();
        String userName = member.getName();
        MallLoggerAdapter loggerAdapter = authorityComponent.getLog4jLog();
        try {
            SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) session.getAttribute("session_info");
            if (info == null) {
                info = new SimpleAuthorizationInfo();
                // 获取角色
                List<String> roles = authorityComponent.getRoleByUserIdx(userId);
                loggerAdapter.info("当前用户：" + userName + ", roleList: " + roles);
                info.addRoles(roles);

                // 获取许可
                Set<String> permissions = null;
                List<MenuTree> menuTreeList = null;
                MenuService menuService = authorityComponent.getMenuService();
                if (MallMessage.SUPPER_NAME.equals(userName)) {
                    permissions = authorityComponent.getPermissions(null);
                    menuTreeList = menuService.getTreeData();
                } else {
                    permissions = authorityComponent.getPermissions(userId);
                    menuTreeList = authorityComponent.getMemberMenuTree(userId);
                }
                if (CollectionUtils.isNotEmpty(permissions)) {
                    loggerAdapter.info("当前用户：" + userName + ", permissions: " + permissions);
                    info.addStringPermissions(permissions);
                }

                // 获取所有urlMapping 集合 （bfs）
                getUrlMappingList(menuTreeList, session);

                session.setAttribute("session_info", info);
                session.setAttribute("session_menuJson", MallJsonUtils.objectToJson(menuTreeList));
            }
            return info;
        } catch (Exception e) {
            loggerAdapter.error(e);
        }
        return null;
    }

    /**
     * 信息认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authcToken;
        String userName = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        Member member = new Member();
        member.setName(userName);
        try {
            member.setPwd(MallMd5Utils.md5ForData(password));
            member = authorityComponent.getMemberService().queryOneSomeInfoByCondition(member);
            if (member == null) {
                throw new AuthenticationException(MallMessage.LOGIN_MESSAGE);
            }
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }

        authorityComponent.getSession(true).setAttribute("session_member", member);

        String principal = member.getIdx() + MallSymbolConstants.COMMA + userName + MallSymbolConstants.COMMA + password;
        return new SimpleAuthenticationInfo(principal, password, getName());
    }

    /**
     * 获取所有urlMapping 集合 （bfs）
     * @param trees 拥有的菜单
     * @return urlMapping 集合
     */
    private void getUrlMappingList(List<MenuTree> trees, Session session) {
        List<String> menuUrlList = (List<String>) session.getAttribute("session_urlMapping");
        if (menuUrlList == null) {
            menuUrlList = new ArrayList<>();
            menuUrlList.add("/sys/**");
            menuUrlList = getUrlMappingList(trees, menuUrlList);
            session.setAttribute("session_urlMapping", menuUrlList);
        }
    }

    private List<String> getUrlMappingList(List<MenuTree> trees, List<String> urlList) {
        if (trees == null) {
            return urlList;
        }
        Map attributes = null;
        List<MenuTree> childrens = null;
        for (MenuTree menuTree : trees) {
            attributes = menuTree.getAttributes();
            urlList.add(attributes.get("url").toString());
            childrens = menuTree.getChildren();
            getUrlMappingList(childrens, urlList);
        }
        return urlList;
    }
}
