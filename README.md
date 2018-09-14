# Log-analysis
大数据案例之日志分析

开发工具：Intellij idea 2017.3.5
虚拟机：centos6.4
Hadoop版本：2.7.3
Hadoop 工具：mysql、sqoop 、hive
辅助工具：SecureCRTPortable(远程连接工具)、SecureFXPortable(文件上传工具)、notepad++(NppFTP插件，连接虚拟机，文件编辑器)

数据格式：
183.162.52.7 - - [10/Nov/2016:00:01:02 +0800] "POST /api3/getadv HTTP/1.1" 200 813  "www.neusoft.com" "-"  cid=0&timestamp=1478707261865&uid=2871142&marking=androidbanner&secrect=a6e8e14701ffe9f60639  "neuedu/5.0.0 (Android 5.1.1; Xiaomi Redmi 3 Build/LMY47V),Network 2G/3G" "-"  10.100.134.244:80 200 0.027 0.027
10.100.0.1 - - [10/Nov/2016:00:01:02 +0800] "HEAD / HTTP/1.1" 301 0 "117.121.101.40" "-" - "curl/7.19.7 (x86_64-redhat-linux-gnu) libcurl/7.19.7 NSS/3.16.2.3 Basic ECC zlib/1.2.3 libidn/1.18 libssh2/1.4.2" "-" - - - 0.000

题目要求：
  1. 统计最受欢迎的视频/文章的TopN访问次数
  2. 按照流量统计最受欢迎的TopN课程
  
题目分析：
  1. 统计最受欢迎的视频/文章的TopN访问次数：统计访问次数最多的文章和视频
  2. 按照流量统计最受欢迎的TopN课程：统计访问流量最多的文章和视频
  
开发流程：
  1. 第一步：清洗数据，先将需要的数据从原始数据中提取出来，并将数据进行加工，最后导入到hive数据仓库中。
       数据清洗：利用MapReduce程序或者直接Java程序，读取原数据，对每行的数据进行切分，然后提取出需要的字段，无论是MapReduce程序还是Java程序，               核心代码都差不多，只是现在的数据只有一二百万，使用Java程序还是很快的，将提取出来的数据存放到一个文件中传到hive中
  2. 第二步：数据处理，由于hive的效率不太高，为了提高速度，提前将查询结果查询出来，并且利用sqoop将查询出来的数据导入到mysql数据库中。
       数据处理：由于hive的查询效率不高，将需要查询的数据提前查询出来，放到一个结果表中，并且将该表中的数据利用sqoop将数据导入到mysql数据库中。
  3. 第三步：可视化展示，利用Javaweb项目，查询mysql中的数据，在页面上利用echars图表对数据进行可视化展示。
       可视化展示：利用Javaweb，将mysql数据库中的数据利用echars进行报表展示。
        注：Javaweb的界面风格套用http://dblab.xmu.edu.cn/blog/1369-2/ 中的页面
 
本程序是利用idea Intellij开发工具中的Springboot框架，由于第一次使用还不太熟悉，文件上传成功/失败，数据清洗成功/失败等对应的消息提示还没有完成。
虚拟机的配置会在接下来的博客中一一说明。
