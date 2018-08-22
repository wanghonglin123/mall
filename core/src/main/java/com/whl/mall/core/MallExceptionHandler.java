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
package com.whl.mall.core;

/**
 * @Title: ShopExceptionHandle
 * @Package: com.shop.commons
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/3/23
 * @Version: V2.0.10
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/3/23
 * @Modify-version: 2.1.5
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ShopExceptionHandle
 * @Description: shop 全局异常处理器, 暂不使用，ajax异常处理会打印多条重复日志
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/3/23
 */
@ControllerAdvice
public final class MallExceptionHandler extends MallBeans{
    /**
     * 异常页面路径
     */
    private static final String ERROR_PATH = "error/";

    /**
     * MallException 异常处理
     * @param request
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    Object exceptionHandle(HttpServletRequest request, Exception ex) {
        MallException exception = null;
        boolean isPage = false;
        if (ex instanceof MallException) {
            if (ex instanceof MallGridException) {
                isPage = true;
            }
            exception = (MallException) ex;
        } else if (ex instanceof AuthenticationException) {
            AuthenticationException authenticationException = (AuthenticationException) ex;
            String msg = authenticationException.getMessage();
            if (MallMessage.LOGIN_MESSAGE.equals(msg)) {
                exception = new MallException(MallStatus.HTTP_STATUS_400, msg);
            } else {
                exception = new MallException(MallStatus.HTTP_STATUS_500, msg);
            }
        } else {
            exception = new MallException(MallStatus.HTTP_STATUS_500, ex);
        }
        return exceptionHandle(exception, isPage, request);
    }

    /**
     * MallException异常处理
     * @param exception 异常
     * @param isPage 是否返回分页结果
     * @return
     */
    private Object exceptionHandle(MallException exception, boolean isPage, HttpServletRequest request) {
        boolean isAjax = isAjax(request);
        int status = exception.getStatus(); // 异常状态
        boolean isException = false; // 是否是500异常
        if (status == MallStatus.HTTP_STATUS_500) {
            printLog(exception);
            isException = true;
        }
        if (!isAjax) {
            HttpStatus httpStatus = getStatus(request);
            return new ModelAndView(ERROR_PATH + httpStatus);
        }
        String msg = exception.getMessage();
        if (isPage) {
            if (isException) {
                return MallGridResult.fail(status, msg);
            }
        }
        return MallResult.build(status, msg);
    }

    private void printLog(MallException ex) {
        getLog4jLog().error(ex);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 是否是Ajax请求
     *
     * @param request 请求对象
     * @return 结果
     */
    public static boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
