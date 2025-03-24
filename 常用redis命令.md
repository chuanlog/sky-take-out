# 常用 Redis 指令

## 1. 字符串操作（String）

- `SET key value`：设置字符串值。
- `GET key`：获取字符串值。
- `DEL key`：删除指定键。
- `INCR key`：将指定键的值增加 1。
- `DECR key`：将指定键的值减少 1。

## 2. 哈希操作（Hash）

- `HSET key field value`：设置哈希表字段的值。
- `HGET key field`：获取哈希表字段的值。
- `HDEL key field`：删除哈希表的字段。
- `HGETALL key`：获取哈希表中所有字段和值。

## 3. 列表操作（List）

- `LPUSH key value`：将一个或多个值插入到列表的左侧。
- `RPUSH key value`：将一个或多个值插入到列表的右侧。
- `LPOP key`：移除并返回列表左侧的元素。
- `RPOP key`：移除并返回列表右侧的元素。
- `LRANGE key start stop`：获取列表中指定范围的元素。

## 4. 集合操作（Set）

- `SADD key member`：向集合添加成员。
- `SREM key member`：从集合中删除成员。
- `SMEMBERS key`：获取集合中的所有成员。
- `SISMEMBER key member`：判断成员是否在集合中。

## 5. 有序集合操作（Sorted Set）

- `ZADD key score member`：向有序集合添加一个成员，或者更新已存在成员的分数。
- `ZREM key member`：删除有序集合中的成员。
- `ZRANGE key start stop`：获取有序集合中指定范围的成员，按分数从低到高排序。
- `ZREVRANGE key start stop`：获取有序集合中指定范围的成员，按分数从高到低排序。
- `ZINCRBY key increment member`：给有序集合成员的分数加上增量。

## 6. 键操作（Key）

- `EXISTS key`：检查给定键是否存在。
- `EXPIRE key seconds`：设置键的过期时间（单位：秒）。
- `TTL key`：获取键的剩余过期时间（单位：秒）。
- `DEL key`：删除指定键。
- `KEYS pattern`：查找所有符合给定模式的键。

## 7. 事务操作（Transaction）

- `MULTI`：标记事务的开始。
- `EXEC`：执行事务中的命令。
- `DISCARD`：放弃事务中的命令。
- `WATCH key`：监视某个键，若在事务执行前该键被修改，事务将会失败。

## 8. 其他常用操作

- `PING`：测试与 Redis 服务器的连接。
- `FLUSHDB`：删除当前数据库中的所有键。
- `FLUSHALL`：删除所有数据库中的所有键。

---

这些是 Redis 中一些常用的指令，涵盖了不同的数据结构操作，帮助开发者高效地操作 Redis。
