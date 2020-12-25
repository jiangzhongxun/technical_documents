# Redis 入门

## Linux 安装

1. 下载安装包 `redis-6.0.9.tar.gz`

![image-20201223222027998](Redis文档.assets/image-20201223222027998.png)

2. 解压 Redis 安装包 ！ Linux 系统上程序推荐放在 /opt 目录下

![image-20201223222344122](Redis文档.assets/image-20201223222344122.png)

3. 进入解压后的文件，可以看到我们的 redis 配置文件

![image-20201223222455018](Redis文档.assets/image-20201223222455018.png)

4. 基本的环境安装

```bash
yum install gcc-c++

make

make install
```

![image-20201223222800199](Redis文档.assets/image-20201223222800199.png)

![image-20201223222855926](Redis文档.assets/image-20201223222855926.png)

5. redis 的默认安装路径 `/usr/local/bin`

![image-20201223223133461](Redis文档.assets/image-20201223223133461.png)

6. 将 /opt/ 目录下解压后的 redis 目录中的 redis.conf 文件复制一份到 /usr/local/bin 目录下

![image-20201223223627939](Redis文档.assets/image-20201223223627939.png)

7. redis 默认不是后台启动的，修改配置文件，改为守护进程方式运行 redis 

![image-20201223223845014](Redis文档.assets/image-20201223223845014.png)

8. 启动 redis ，测试连接是否通过

![image-20201223224200306](Redis文档.assets/image-20201223224200306.png)

9. 查看 redis 的进程是否开启！

![image-20201223224339015](Redis文档.assets/image-20201223224339015.png)

10. 如果关闭 redis 服务

![image-20201223224525534](Redis文档.assets/image-20201223224525534.png)

## 测试性能

redis-benchmark 是一个压力测试工具！

官方自带的性能测试工具！

简单测试下：

```bash
# 测试：100个并发连接 100000 请求
redis-benchmark -h localhost -p 6379 -c 100 -n 100000
```

![image-20201223230001383](Redis文档.assets/image-20201223230001383.png)

# 基础知识

(Redis命令手册)[https://www.redis.net.cn/order/]

```bash
Redis 			# 默认有 16 个数据库
redis 			# 默认选中第0个数据库
select index 	# 切换数据库
flushdb			# 清空当前数据库内容
flushall		# 清空全部数据库内容
dbsize			# 查看当前数据库大小
keys * 			# 查看当前数据库中所有内容
exists key		# 判断当前 key 是否存在
move key 1 		# 移动当前 key
expire key 10	# 设置当前 key 过期时间为 10 秒
ttl key			# 查看当前 key 还有多少秒过期 -2 表示已失效
type key		# 查看当前 key 的类型是什么
append key		# 基于当前 key 在后面追加内容，如果当前 key 不存在，就相当于 set key
strlen key		# 获取当前 key 的长度


incr key		# 步长 + 1
incrby key 5	# 步长 + 5（比较灵活，步长由自己控制）
decr key		# 步长 - 1
decrby key 5 	# 步长 - 5（比较灵活，步长由自己控制）

getrange key [start,end]	# 查看指定范围之间的内容
getrange key [0, -1] 		# 查看当前 key 全部内容
setrange key [start,end]	# 替换指定位置开始的字符串

setex key 10 hello		# 设置当前 key 10秒后过期,key的内容为 hello
setnx key hello			# 如果当前 key 不存在则添加成功，若当前 key 已经存在，则添加失败

mset [k v],[k v],[k v] 	# 同时设置多个 key
mget key [key2 key3]	# 同时获取多个 key
msetnx key value		# 同时设置多个 key，若当前 key 已经存在，则全部失败，该命令属于原子性操作

getset	key value		# 先获取 key 在设置 key，若 key 不存在，则直接设置值，若 key 已经存在，获取原来的值，并设置新的值进行替换

##########################################################

Lpush key value...			# 向当前 key 中尾部插入多个值，类似 list 集合
Rpush key value...			# 向当前 key 的头部插入多个值
Lrange key 0 -1				# 查看当前集合中所有值
Lpop key					# 从当前集合尾部中移除1个值
Rpop key					# 从当前集合头部中移除1个值
Lindex key index			# 跟据下标获取当前集合中某个元素（下标从0开始)
Llen key					# 查看当前集合长度
Lrem key count element		# 从当前集合中移除几个元素（count 个数）（element 要移除的元素）
Ltrim key start stop		# 从当前集合中阶段元素
RpopLpush source target		# 从当前集合头部移除一个元素，然后添加到新的集合中去（作用同等与移动效果）
exists key					# 判断当前集合中是否存在元素
Lset key index element		# 替换当前集合某个下标中的元素，不存在则会报错
Linsert key before pivot element # 向当前 key 某个元素前面插入一个值
Linsert key after pivot element  # 向当前 key 某个元素后面插入一个值

##########################################################

Sadd key value...		# 添加一个Set集合，可以同时添加多个值（无序集合）（不能添加重复的值）
Smembers key			# 查看 set 集合中所有元素内容
Sismember key element	# 查看当前 set 集合中是否存在某个元素（1存在/0不存在）
Scard key				# 查看当前 set 集合长度
Srandmember key			# 随机抽选出一个元素
Srandmember key count	# 随机抽选出指定个数的元素
Spop key				# 随笔移除 set 集合中的一个元素
Smove key1 key2 element	# 从当前 set 集合中移动元素到另一个集合中去（不存在的集合或者不存在的元素都不会生效，返回 0 表示不生效）
Sdiff key1 key2			# 查看 key1 集合和 key2 集合中的差集元素
Sinter key1 key2		# 查看 key1 集合和 Key2 集合中的交集元素
Sunion key1 key2		# 查看 key1 集合和 key2 集合中的并集元素

##########################################################

Hset myhash k1 v1 k2 v2 ...	# 向 myhash 集合中添加元素
Hget myhash k1				# 获取 myhash 集合中 k1 的value值
Hmset myhash k1 v1 k2 v2	# 向 myhash 集合中添加多个键值对元素
Hmget myhash k1 k2 			# 获取 myhash 集合中多个键值对元素内容
Hgetall myhash				# 获取 myhash 集合中全部键值对元素内容
Hdel myhash k3				# 从 myhash 集合中删除掉 k3 元素内容
Hlen myhash					# 获取 myhash 集合中元素的长度
Hexists myhash key			# 判断 myhash 集合中是否存在 key 这个元素（1存在/0不存在）
Hkeys myhash	# 获取 myhash 中所有的 key 元素
Hvals myhash 	# 获取 myhash 中所有的 value 元素
Hincrby myhash field1 5 # 设置 myhash 集合中 field1 元素内容自增步长为 5
Hsetnx myhash field3 java # 向 myhash 集合中添加元素，若 field3 不存在，则添加成功，存在则添加失败

```

