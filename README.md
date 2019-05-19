# KTV 智能选歌系统

### 环境搭建

需要安装 node.js，maven，git，java

### 创建数据库

使用 MySQL 运行 ddl.sql 文件

### 修改配置文件

修改 \ktv-server\base\src\main\resources\conf.properties
> 修改数据源和音乐文档地址

### 部署脚本

|文件 |作用 |
|--|--|
|preBuild.sh|安装 org.tree:commons:1.0.0 依赖|
|build.sh|部署，如果本地不存在 commons 包，先运行 preBuild.sh|
|run.sh |运行，执行之前需要先运行前面介绍的脚本 |
