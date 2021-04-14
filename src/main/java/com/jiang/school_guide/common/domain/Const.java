package com.jiang.school_guide.common.domain;

public class Const {
    /**
     * @Description 用户角色
     * @Date 17:18 2019/11/18
     **/

    public static final String ADMIN = "admin";

    public static final String USER ="user";

    public enum  STATUS{


        /**
         * 用户状态
         */
        NORMAL(0,"正常"),
        PROHIBITION(1,"已封禁"),
        NEED_INFO(2,"未完善信息"),

        /**
         * 错误码
         */
        NOPERMISSION(424, "没有权限访问"),
        NEEDLOGIN(425, "需要登陆");



        private int status;
        private String desc;

        STATUS(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }


    }
    /**
     * @Description Token
     * @Date 19:29 2019/12/3
     **/
    public static final String TOKEN = "Authentication";

    public static final String TOKEN_CACHE_PREFIX = "practice.cache.token";




    /**
     * @Description:Excel操作结果状态
     * @Author: ashe
     * @Date: 2019/12/4
     */
    public static final Integer SUCCESS_INCREASE = 0;

    public static final Integer FAIL_INCREASE = 1;

    /**
     * @Description:数据库操作
     * @Author: ashe
     * @Date: 2019/12/6
     */

    public static final Integer CREATE = 0;

    public static final Integer UPDATE = 1;


}
