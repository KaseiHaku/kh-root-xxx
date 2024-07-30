module com.xxx.starter.basic {

    /******************************** 强制传递依赖 ********************************
     * 声明顺序: 自研模块，三方模块，三方自动模块
     */
    requires static transitive lombok;
    requires transitive org.slf4j;

    requires transitive com.kaseihaku.core.ppe;
    requires transitive com.kaseihaku.cloud.starter.basic;



    /******************************** 可选依赖 *******************************
     * 以下为 非传递依赖 的 自动模块，即: 实际使用时，需要在使用 module 中再次引入
     * Maven 依赖中 <option>true</option> 的依赖
     * */
    requires static spring.boot.autoconfigure;
    requires static spring.context;
    requires static spring.boot;



    /******************************** 导出供其他模块使用 *******************************/
}
