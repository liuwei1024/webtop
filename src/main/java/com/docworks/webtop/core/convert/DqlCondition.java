/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.core.convert;

import com.docworks.webtop.bean.vo.OrderVo;
import com.docworks.webtop.bean.vo.PageVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import com.docworks.webtop.bean.vo.ColumnVo;

import java.util.List;

/**
 * @author liuwei
 * @version 1.0
 */
public class DqlCondition implements Condition {

    private PageVo pageVo;

    public DqlCondition(PageVo pageVo) {
        Validate.notNull(pageVo, "Argument 'pageVo' cannot be null.");
        this.pageVo = pageVo;
    }

    @Override
    public String toCondition() {
        StringBuilder dqlCondition = new StringBuilder(256);
        List<ColumnVo> columns = pageVo.getColumns();
        if (CollectionUtils.isNotEmpty(columns)) {
            for (ColumnVo column : columns) {
                // Column properties value cannot be null
                if (StringUtils.isNotBlank(column.getValue()) &&
                        StringUtils.isNotBlank(column.getPredicate())) {
                    // 拼接查询Column
                    if (column.getName() != null) {
                        dqlCondition.append(column.getName()).append(" ");
                    }

                    // 处理谓词和值
                    if ("EQUAL".equals(column.getPredicate())) {
                        dqlCondition.append("=").append(" ");
                        if ("string".equals(column.getType())) {
                            dqlCondition.append("'").append(column.getValue()).append("'").append(" ");
                        } else if ("date".equals(column.getType())) {
                            dqlCondition.append("DATE('").append(column.getValue()).append("', 'yy-mm-dd')").append(" ");
                        } else if ("datetime".equals(column.getType())) {
                            dqlCondition.append("DATE('").append(column.getValue()).append("', 'yy-mm-dd hh:mi:ss')").append(" ");
                        } else if ("boolean".equals(column.getType())) {
                            String value = Boolean.valueOf(column.getValue()) ? "TRUE" : "FALSE";
                            dqlCondition.append(value).append(" ");
                        } else {
                            dqlCondition.append(column.getValue()).append(" ");
                        }
                    }

                    else if ("START_WITH".equals(column.getPredicate())) {
                        dqlCondition.append("LIKE").append(" '").append(column.getValue()).append("%'").append(" ");
                    }

                    else if ("END_WITH".equals(column.getPredicate())) {
                        dqlCondition.append("LIKE").append(" '%").append(column.getValue()).append("'").append(" ");
                    }

                    else if ("CONTAIN".equals(column.getPredicate())) {
                        dqlCondition.append("LIKE").append(" ").append("'%").append(column.getValue()).append("%'").append(" ");
                    }

                    else if ("BETWEEN_AND".equals(column.getPredicate())) {
                        String[] values = column.getValue().split(",");
                        if (values.length == 2) {
                            if (column.getType().startsWith("date")) {
                                String pattern = "";
                                if ("date".equals(column.getType())) {
                                    pattern = "yy-mm-dd";
                                } else if ("datetime".equals(column.getType())) {
                                    pattern = "yy-mm-dd hh:mi:ss";
                                }

                                dqlCondition.append(">=").append("DATE('").append(values[0]).append("', '").append(pattern).append("')").append(" ");
                                dqlCondition.append("AND ");
                                dqlCondition.append("<=").append("DATE('").append(values[1]).append("', '").append(pattern).append("')").append(" ");
                            } else {
                                dqlCondition.append(">=").append(values[0]).append(" ");
                                dqlCondition.append("AND ");
                                dqlCondition.append("<=").append(values[1]).append(" ");
                            }
                        }
                    }

                    else if (column.getPredicate().startsWith("FOLDER")) {
                        if ("FOLDER_ID".equals(column.getPredicate())) {
                            dqlCondition.append("FOLDER(ID('").append(column.getValue()).append("')");
                        } else if ("FOLDER_PATH".equals(column.getPredicate())) {
                            dqlCondition.append("FOLDER('").append(column.getValue()).append("'");
                        }

                        // 根据类型判断是否递归查询子目录
                        if (Boolean.valueOf(column.getType())) {
                            dqlCondition.append(", DESCEND) ");
                        } else {
                            dqlCondition.append(") ");
                        }
                    }

                    if (column != columns.get(columns.size() - 1)) {
                        dqlCondition.append("AND ");
                    }
                }
            }
        } else {
            dqlCondition.append("1=1 ");
        }

        return dqlCondition.toString();
    }

    @Override
    public String toOrder() {
        StringBuilder dqlOrder = new StringBuilder(128);
        if (CollectionUtils.isNotEmpty(pageVo.getOrders())) {
            dqlOrder.append("ORDER BY ");
            appendOrderCause(dqlOrder);
        }
        return dqlOrder.toString();
    }

    @Override
    public String toPagination() {
        StringBuilder dqlPagination = new StringBuilder(128);

        if (pageVo.getPage() != null && pageVo.getLength() != null) {
            int startIndex = (pageVo.getPage() - 1) * pageVo.getLength() + 1;
            int endIndex = pageVo.getPage() * pageVo.getLength();

            dqlPagination.append("ENABLE(RETURN_RANGE ");
            dqlPagination.append(startIndex);
            dqlPagination.append(" ");
            dqlPagination.append(endIndex);

            // 拼接排序条件
            dqlPagination.append(" '");
            appendOrderCause(dqlPagination);
            dqlPagination.append("') ");
        }

        return dqlPagination.toString();
    }

    private void appendOrderCause(StringBuilder dqlBuilder) {
        List<OrderVo> orders = pageVo.getOrders();
        if (CollectionUtils.isNotEmpty(orders)) {
            for (OrderVo order : orders) {
                if (StringUtils.isNotBlank(order.getColumn()) &&
                        StringUtils.isNotBlank(order.getDirection())) {
                    dqlBuilder.append(order.getColumn()).append(" ");
                    dqlBuilder.append(order.getDirection().toUpperCase());
                }
                if (order != orders.get(orders.size() - 1)) {
                    dqlBuilder.append(", ");
                }
            }
        }
    }
}
