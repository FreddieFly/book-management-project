package com.huangcihong.common.entity.enums.auth;


public enum MenuKeyEnum {
    SERVICE("服务监控"),

    LOG("日志管理"),
    /**
     * 领域
     */
    DOMAIN("领域"),
    /**
     * 建模
     */
    BUILDER("建模"),
    /**
     * 图谱
     */
    GRAPH("图谱"),
    /**
     * 规则
     */
    RULE("规则"),
    /**
     * 指标
     */
    QUOTA("指标"),
    /**
     * 模型
     */
    MODEL("模型"),
    /**
     * 自动规则发现
     */
    KNOWLEDGE_AUTO_DISCOVERY("自动规则发现"),
    /**
     * 模糊模型
     */
    MODEL_FUZZY("模糊模型"),
    /**
     * 名单库
     */
    NAME_LIST_LIBRARY("名单库"),
    /**
     * 信息脱敏
     */
    MASKING("信息脱敏"),
    /**
     * 同构图基础配置
     */
    ISO_BASIS_CONF("同构图基础配置"),
    /**
     * 图谱探索
     */
    OPERATION ("图谱探索"),
    /**
     * 分析
     */
    INFERENCE ("分析"),
    /**
     * 图分析
     */
    GRAPH_INFERENCE ("图分析"),
    /**
     * 研判
     */
    SPACE("研判"),
    /**
     * 标签管理
     */
    LABEL("标签管理"),
    /**
     * 组员管理
     */
    GROUP("组员管理"),
    /**
     * 主题应用
     */
    THEME("主题应用"),
    /**
     * 数据源管理
     */
    DS("数据源管理"),
    /**
     * 系统管理
     */
    SYS_MANA("系统管理"),
    /**
     * 知识资产
     */
    DASHBOARD("知识资产"),
    /**
     * 用户角色权限
     */
    AUTH("用户角色权限"),
    /**
     * 角色管理
     */
    ROLE("角色管理"),
    /**
     * 用户管理
     */
    USER("用户管理"),
    /**
     * 菜单管理
     */
    MENU("菜单管理"),

    BASE_BUILDER("建模"),

    ONTOLOGY_BUILDER("本体建模"),

    GRAPH_BUILDER("图谱实列管理"),

    /**
     * 图谱实例
     */
    BUILDER_OPERATION("图谱实列"),

    /**
     * 子图
     */
    SUBGRAPH("子图"),

    /**
     * 子图列表
     */
    SUBGRAPH_LIST("查询子图列表"),

    /**
     * 图谱定义
     */
    GRAPH_DEFINITION("图谱定义"),

    /**
     * 流程管理
     */
    PROCESS_MANAGEMENT("流程管理"),

    /**
     * 流程定义
     */
    PROCESS_DEFINITION("流程定义"),

    /**
     * 流程实例
     */
    PROCESS_INSTANCE("流程实例"),

    /**
     * 任务实例
     */
    TASK_INSTANCE("任务实例"),

    /**
     * 知识定义
     */
    KNOWLEDGE_DEFINITION("知识定义"),

    /**
     * 可疑名单(规划中)
     */
    SUSPECT_LIST("可疑名单(规划中)"),

    /**
     * 团伙识别(规划中)
     */
    GANG_IDENTIFICATION("团伙识别(规划中)"),

    /**
     * 规则发现(规划中)
     */
    RULE_DISCOVERY("规则发现(规划中)"),

    /**
     * 案例库(规划中)
     */
    CASE("案例库(规划中)"),

    /**
     * 指标集
     */
    QUOTA_COLLECTION("指标集"),

    /**
     * 归因分析
     */
    ATTR_ANALYZER("归因分析"),

    /**
     * 任务结果检索
     */
    TASK_INSTANCE_RESULT("任务结果检索"),

    /**
     * 问答图谱
     */
    GRAPH_IQA("问答图谱"),

    /**
     * 智能问答
     */
    QUESTION_ANSWER("智能问答"),

    /**
     * 群组切分规则管理
     */
    SUBGRAPH_GROUP_RULE("群组切分规则管理"),

    /**
     * 群组关注程度
     */
    SUBGRAPH_GROUP_ATTENTION("群组关注程度"),

    /**
     * 字典管理
     */
    DICT("字典管理"),

    /**
     * 标签分类管理
     */
    LABEL_CATEGORY("标签分类管理"),

    ORGANIZATION("法人机构管理"),

    RESOURCES("资源管理"),

    /**
     * 标签管理
     */
    LABEL_MANAGE("标签管理"),

    /**
     * 尽调触发规则管理
     */
    INVESTIGATION_RULE("尽调触发规则管理"),

    /**
     * 实体加密管理
     */
    ENCRYPTION_CONFIG("实体加密管理"),
    ;

    private String message;

    MenuKeyEnum(String message) {
        this.message = message;
    }

    MenuKeyEnum() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
