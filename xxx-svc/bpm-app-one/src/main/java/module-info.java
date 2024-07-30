module com.kaseihaku.bpm.app.one {


    /******************************** 强制传递依赖 *******************************
     * 业务模块，不会被其他模块引用，所以不需要传递依赖
     * */
    requires com.kaseihaku.cloud.starter.mvc;
    requires com.kaseihaku.bpm.starter.ppe;
    requires com.github.benmanes.caffeine;
    requires org.mybatis.spring;
    requires org.mybatis.spring.boot.autoconfigure;
    requires com.baomidou.mybatis.plus.annotation;
    requires com.baomidou.mybatis.plus.core;
    requires com.baomidou.mybatis.plus.extension;
    requires com.kaseihaku.cloud.starter.mybatis;

    /******************************** 可选依赖 *******************************
     * 以下为 非传递依赖 的 自动模块，即: 实际使用时，需要在使用 module 中再次引入
     * Maven 依赖中 <option>true</option> 的依赖
     * */



    /******************************** 导出供其他模块使用 *******************************
     * 业务模块，不会被其他模块引用，所以不需要导出
     * */
}
