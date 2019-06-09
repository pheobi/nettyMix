package com.cn.core.model;

/**结果码 （q:为什么是interface不是enum  字段为什么是int 不是short）
 * @author liyahui
 * @create 2019-06-08
 */
public interface ResultCode {
    /**成功*/
    int SUCCESS =0;

    /**
     * 找不到命令
     */
    int NO_INVOKER =1;

    /**
     * 参数异常
     */
    int AGRUMENT_ERROR=2;

    /**
     * 未知异常
     */
    int UNKNOWN_EXCEPTION =3;

    /**
     * 玩家名或密码不能为空
     */
    int PLAYERNAME_NULL = 4;

    /**
     * 玩家名已使用
     */
    int PLAYER_EXIST =5;
}
