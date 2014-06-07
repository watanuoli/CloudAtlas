CloudAtlas
==========

GY4 team-10 project

项目描述：
利用Hadoop HDFS搭建云存储服务器，在客户端实现图片的上传，下载，删除，浏览以及展示。

工程目录结构参考

src
  |-client --提供照片的上传，下载，删除，浏览以及展示等功能
  |     |--android  --安卓客户端
  |     |--web      --HTML web客户端
  | server
        |--service  --云存储服务RESTful service,接受并处理Client HTTP请求。Java + Redis + Jersey(或CXF)
        |--storage  --Hadoop HDFS集群 
        
构建:maven，web client和service打在一个war包中统一部署。
服务器：Tomcat
文件元数据存储：Redis
文件存储：HDFS集群

系统架构：
<pre>
                    RESTful service
                         ||
|---------|       |--------------|       |--------------|
| Client  | ----> | File Service | ----> | HDFS cluster |
|---------|       |--------------|       |--------------|
                         ||                      ||
                         ||                      ||                         
                    |----------|                /root
                    | File     |                   \----user1_id\
            Redis-> | MetaData |                   \----user2_id\
                    | Storage  |                   \....
                    |----------|           

File Metadata Structure:
{ 
 FileName,
 CreateTime,
 URL       # 文件在HDFS上的访问地址
}
</pre>

客户端发出HTTP请求到File Service服务器的Webservice接口，File Service处理此请求，并返回应到Client。
 