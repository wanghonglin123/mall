package com.whl.mall.core.base.service.ext;
/**
 * @Title: MallDbServiceExt
 * @Package: com.whl.mall.core.base.service.ext
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-13 上午 12:12
 * @Version: V2.0.0
 */

import com.whl.mall.core.base.service.MallDBService;
import com.whl.mall.core.common.utils.MallJdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: MallDbServiceExt
 * @Description: DB接口
 * @Author: WangHongLin
 * @Date: 2018-04-13 上午 12:12
 */
public final class MallDbServiceExt {
    private MallDbServiceExt(){}

    public static void execute(MallDBService dbService, String sql) throws Exception{
        Connection connection = MallJdbcUtils.getConnection(MallJdbcUtils.DRIVER_NAME);
        PreparedStatement statement = connection.prepareStatement(sql);
        dbService.execute(statement);
        MallJdbcUtils.close(connection, statement);
    }

    @Test
    public void test() throws Exception{
        String sql = "";
        MallDbServiceExt.execute(preparedStatement -> {
            System.out.println("测试");
        },sql);
    }
}
