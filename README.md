# CloundFlareDDNS
基于JDK8开发的CloundFlare DDNS程序
第一次运行会在根目录生成一个user.properties
```properties
#user.properties
#Date
ttl=
zone_id=
name=
record_id=
email=
key=
type=
```
只需要ttl可以留空默认为120
zone_id等可以去CloundFlare的网站上获取到
name则是需要被DDNS的域名:example.youdomina.com
key则是访问令牌
type为dns解析类型A为IPV4，AAAA为IPV6默认为IPV6
