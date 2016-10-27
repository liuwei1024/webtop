# webtop
Spring + SpringMVC + DCTM框架

# 开发手册

## 分页查询

分页查询使用一个`PageVo`对象接收参数，该对象包含的属性格式如下：

```
    PageVo {
        page : '当前页码',
        length : '每页返回数',
        columns : [
            {
                value : '字段值',
                name : '字段名称'
                type : '字段类型'
                predicate : '查询谓词，目前支持的谓词有：EQUAL, START_WITH, END_WITH, CONTAIN, BETWEEN_AND, FOLDER_ID, FOLDER_PATH'
            }
        ],
        orders : [
            {
                column : '字段名称'
                direction : '排序方式，取值范围：ASC, DESC 大小写不明感'
            }
        ]
    }
```

例如，我需要查询用户名称以`dm_`开头的所有用户，请求接口的URL为：

`http://localhost:8080/webtop/user/list?page=1&length=10&columns[0].name=user_name&columns[0].type=string&columns[0].value=dm_&columns[0].predicate=START_WITH`

查询的参数`columns`可以为多个，上面URL中的`columns[0]`表示第一个`column`，如果有多个，可以追加`columns[1], columns[2], columns[3] ...`。

如果你有多个`columns`条件，查询的DQL语句中条件以`AND`的方式组织，如果你开启了`DctmTemplate`中`showDql`参数，可以在控制台看到执行的DQL语句。

### 谓词（predicate）参数的说明

| 参数值          | 对应DQL语句的条件约束                  | 支持的数据类型                                |
| -------------- | :--------------------------------  | :-------------------------------------------|
| `EQUAL`        | `=`                                | `string, date, datetime, boolean, number`   |
| `START_WITH`   | `'example%'`                       | `string`                                    |
| `END_WITH`     | `'%example'`                       | `string`                                    |
| `CONTAIN`      | `'%example%'`                      | `string`                                    |
| `BETWEEN_AND`  | `column >= min AND column <= max`  | `date, datetime, number`                    |
| `FOLDER_ID`    | `FOLDER(ID('0b1234567890'))`       | `string`                                    |
| `FOLDER_PATH`  | `FOLDER('/新建文件夹/2016年工作总结')` | `string`                                    |

**注意：**

- 如果谓词参数值为`FOLDER_ID`或`FOLDER_PATH`，参数`type`的值不能为string（实际上默认就是string），`type`应该为`true`或`false`，表示是否递归查询子文件夹。例如：

```
/file/list?columns[0].value=0b1234567890&columns[0].predicate=FOLDER_ID&columns[0].type=true

/file/list?columns[0].value=/新建文件夹/2016年工作总结&columns[0].predicate=FOLDER_PATH&columns[0].type=true
```

此时，`name`参数可以不用传值或传`null`。

- 如果谓词参数值为`BETWEEN_AND`，参数值为一个区间范围，以英文逗号分隔。例如：

```
/folder/list?columns[0].name=r_creation_date&columns[0].type=date&columns[0].predicate=BETWEEN_AND&columns[0].value=2016-10-27,2016-12-30
```

### 结果集排序

使用`orders`参数可以为查询的结果集进行排序，例如：

```
/folder/list?orders[0].column=r_modify_date&orders[0].direction=DESC
```

排序参数也可以指定多个，如果有多个，可以追加`orders[1], orders[2], orders[3] ...`。


## 异常处理

框架中已经整合了全局异常处理，如果需要处理异常返回结果，请参考：`UserService`中`create`方法。

## 国际化支持

在请求中加入参数locale可以使返回消息支持多语言，例如：`locale=zh_CN`或`locale=en_US`。

## 包结构说明

```
  src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─docworks
    │  │          └─webtop
    │  │              ├─bean
    │  │              │  ├─domain
    │  │              │  ├─dto
    │  │              │  ├─enums
    │  │              │  │  └─message
    │  │              │  └─vo
    │  │              ├─core
    │  │              │  ├─aspect
    │  │              │  └─convert
    │  │              ├─exception
    │  │              ├─mapper
    │  │              ├─service
    │  │              └─web
    │  │                  ├─controller
    │  │                  │  └─user
    │  │                  ├─interceptor
    │  │                  └─method
    │  │                      ├─annotation
    │  │                      └─resolver
    │  ├─resources
    │  └─webapp
    │      └─WEB-INF
    └─test
        ├─java
        │  └─com
        │      └─docworks
        │          └─webtop
        │              └─service
        └─resources
```

框架按照Maven标准web工程的目录结构。



