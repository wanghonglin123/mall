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
package com.whl.mall.manage.shiro.listener;/**
 * @Title: MallAuthencationListener
 * @Package: com.whl.mall.manage.shiro.listener
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/5/10
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/5/10
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.core.common.constants.MallUrlConstants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.servlet.ServletContextSupport;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MallAuthencationListener
 * @Description: SHiro 验证监听器
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/5/10
 */
public class MallAuthencationListener extends MallBeans implements AuthenticationListener {
    /**
     * 验证成功
     *
     * @param token
     * @param info
     */
    @Override
    public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
        getLog4jLog().info(String.format("认证成功，token = %s", token));
    }

    /**
     * 验证失败
     *
     * @param token
     * @param ae
     */
    @Override
    public void onFailure(AuthenticationToken token, AuthenticationException ae) {
    }

    /**
     * 退出
     *
     * @param principals
     */
    @Override
    public void onLogout(PrincipalCollection principals) {
        getLog4jLog().info(String.format("退出成功，principals = %s", principals));
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从session里面获取对应的值
        String str = (String) requestAttributes.getAttribute("name", RequestAttributes.SCOPE_SESSION);
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        try {
            WebUtils.issueRedirect(request, response, MallUrlConstants.LOGIN_URL);
        } catch (Exception e) {
            getLog4jLog().error(e, String.format("退出失败，principals = %s", principals));
        }
    }
}
