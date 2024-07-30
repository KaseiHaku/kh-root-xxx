PPE 相关的 BizGroup 划分建议:
    All(-300000) -> Business(-306000)       # 这里为 预埋组，禁止修改
        -> bpm-app-one                      # 以当前 微服务名 新建一个子 Group
            -> feature                      # 以当前 流程商标 新建一个子 Group
                -> feature:管理员组          # 当前 流程 的 管理员组
                -> feature:申请人组          # 当前 流程 的 申请人组
                -> feature:默认可见组        # 当前 流程 的 默认可见组

PPE 相关的 Role 划分建议: (注意：以下缩进的 role 是上层 role 的父 role，跟 Group 相反)
    bpm-app-one                      # 以当前 微服务名 新建一个 role 并分配基础 permission
        -> bpm-app-one:admin         # 流程管理员角色(该 role 是 role "bpm-app-one" 的 ParentRole)

开发一个新的 PPE APP 的步骤:
    - 按照 bpm-app-one 的目录结构，新建一个 boot 项目，项目名/包名 修改称自己公司的
      例如:
        bpm-app-yyy/src/main
            │
            ├─ java
                ├─ com.xxx.bpm.app.yyy
                    ├─ BpmAppYyyBoot.java   # java 启动类
                    ├─ config               # boot 自定义配置根包，包含 配置类
                        ├─ bean             # boot 自定义 bean 根包
                    ├─ workflow             # 流程配置根包
                        ├─ procBrand1       # 流程1 的根包
                    ├─ ctrl                 # ctrl/svc/repo   等其他常规包
            │
            ├─ resources
                ├─ application.yml          # 修改 spring.application.name(必须以 bpm-app- 开头) 和 server.port
                                            # 修改 spring.cloud.consul.discovery.metadata.bpm-app-name = yyy      自定义 ppe 应用名

    - 启动 bpm-app-yyy 并查看 bpm-portal 界面是否出现了 bpm-app-yyy 的应用
    - 登录 cloud-user(https://cloud.kaseihaku.com/cloud-user/) 前端界面
        - 打开 Permission/By Micro Service 菜单
            - 输入当前微服务名称，这里为: bpm-app-yyy
            - 点击 Load 按钮，将加载 bpm-app-yyy 所有的 http 接口 permission，例如: api:///bpm-app-yyy/... (禁止 / 结尾 )

            或者手动创建:
            - Resource/Create 菜单界面
            - URI: api:///bpm-app-yyy/... (禁止 / 结尾 )
            - 点击 Create 按钮
            - Permission/Create 菜单界面
            - Resource: 输入/选择 刚才创建的
            - Action: 选择该 api 对应的 http 方法，例如: post
            - 点击 Create 按钮

        - 创建 bpm-app-yyy 角色
            - Role/Create 菜单界面
            - name: bpm-app-yyy
            - 点击 Create 按钮
        - 将 permission 分配给 bpm-app-yyy 角色
            - Role/Role Table View 菜单界面
            - 输入 Name: bpm-app-yyy 回车搜索
            - 点击行按钮 '...' 下的 'Permissions'
            - 左侧勾选 想要的 Permission(许可证)
            - 点击中间 '>>' 按钮 将 permission 放到右边即可
        - 创建 All/Business/bpm-app-yyy 业务组
            - Group/Group Table View 菜单界面
            - 输入 Name: Business 回车搜索
            - 点击行按钮 '...' 下的 'Create Sub Group'
            - 输入 name: bpm-app-yyy
            - 点击 Create 按钮
        - 将 bpm-app-yyy 角色 分配给 bpm-app-yyy 业务组
            - Group/Group Table View 菜单界面
            - 输入 Name: Business 回车搜索
            - 点击行按钮 '...' 下的 'Roles' 进入 组角色分配 页面
            - 左侧勾选 bpm-app-yyy 角色
            - 点击中间 '>>' 按钮 将 bpm-app-yyy 角色 放到右边即可
        - 创建 YyyTest1 用户
            - User/Create 菜单界面
            - Nickname: YyyTest1
            - 点击 Create 按钮
        - 将 YyyTest1 用户 分配给 bpm-app-yyy 业务组
            - Group/Group Table View 菜单界面
            - 输入 Name: Business 回车搜索
            - 点击行按钮 '...' 下的 'Users' 进入 组用户分配 页面
            - 左侧勾选 YyyTest1 用户
            - 点击中间 '>>' 按钮 将 YyyTest1 用户 放到右边即可
        - 给 YyyTest1 用户配置登录方式(目前只支持 account)
            - User/Search 菜单界面
            - 输入 Nickname: YyyTest1 回车搜索
            - 点击行按钮 'Auth' 进入 认证方式配置 页面
            - 点击 'Create' 按钮创建 认证方式
            - 输入:
                IdentifierType: account
                Identifier: YyyTest1            # 账号，一般跟 nickname 保持一致
                Certificate: password           # 密码
            - 点击 'Confirm' 创建
    - 常用 permission 列表:
        # Role = registered = 普通用户必备权限;
        # @tips sit-online(线上 SIT 环境) 已经配置，新用户无需再次配置
        post|api:///cloud-file/FileUpload/single
        get |api:///cloud-file/FileDownload/single
        put |api:///cloud-user/v1/Authenticate/signOut
        put |api:///cloud-user/v1/Authenticate/refresh
        get |api:///cloud-user/v1/Group
        put |api:///cloud-user/v1/Group/queryByIds
        put |api:///cloud-user/v1/Group/transfer2HomonymGroup
        get |api:///cloud-user/v1/Group/getSubGroupById
        get |api:///cloud-user/v1/User
        put |api:///cloud-user/v1/User
        put |api:///cloud-user/v1/User/findByIds

        # PPE 普通用户 基本权限，即: role = bpm-app-one
        # @trap 如果是新的 bpm-app-??? 需要配置
        put   |api:///bpm-app-one/v1/ppe/ToDo/queryWithMeta           待办查询
        put   |api:///bpm-app-one/v1/ppe/CurrentVersion               流程版本指针查询
        get   |api:///bpm-app-one/v1/ppe/ToDo                         查询待办
        get   |api:///bpm-app-one/v1/ppe/Process                      查询流程
        put   |api:///bpm-app-one/v1/ppe/Process/queryWithMeta        查询流程
        get   |api:///bpm-app-one/v1/ppe/ProcessMeta                  查询流程模型
        put   |api:///bpm-app-one/v1/ppe/NodeMeta                     查询节点模型
        get   |api:///bpm-app-one/v1/ppe/Process/queryProcAllNode     查询流程所有节点
        put   |api:///bpm-app-one/v1/ppe/Node                         查询节点
        get   |api:///bpm-app-one/v1/ppe/NodeMeta/queryByToDoId       根据待办查询节点模型
        post  |api:///bpm-app-one/v1/ppe/Engine/processCreate         发起流程
        get   |api:///bpm-app-one/v1/ppe/Engine/nodeRenderModel       获取渲染模型
        get   |api:///bpm-app-one/v1/ppe/Engine/nodeRenderModelToDo   获取待办渲染模型
        post  |api:///bpm-app-one/v1/ppe/Engine/nodeSubmit            节点提交

        # PPE 流程管理员 额外权限，即: role = bpm-app-one:admin
        post  |api:///bpm-app-one/v1/ppe/ToDo       新建待办
        patch |api:///bpm-app-one/v1/ppe/ToDo       忽略待办


开发一个新的 PPE Process 的步骤:
    - 见 流程管理界面 中: 流程 -> 手册菜单
