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
force=false
```
只需要ttl可以留空默认为120
zone_id等可以去CloundFlare的网站上获取到
name则是需要被DDNS的域名:example.youdomina.com
key则是访问令牌
type为dns解析类型A为IPV4，AAAA为IPV6默认为IPV6
force=true时会开启强制更新(即时检测到的ip地址和域名解析出的地址一致也会执行dns更新操作,防止了一些服务器由于dns服务器设置导致的延迟以至于错过需要更改的时机)
