package com.bjpowernode.service.serviceimpl;

import com.bjpowernode.bean.User;
import com.bjpowernode.master.MasterUserMapper;
import com.bjpowernode.service.SqlService;
import com.bjpowernode.slave.SlaveUserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
/**
 * 普通多数据源，这种方案我们需要准备多套Mapper使用目录和类型来隔离我们的读写操作
 * 缺点
 *   1、有很多的重复代码例如Spring中的配置以及多套Mapper映射中的代码，每一个数据库基本上都应该拥有独立的配置
 *   2、切换主从进行读写操作时需要程序认为手动选择对应的类，这样就有可能会形成向从库写数据向主库读数据
 *   3、如果有很多个从节点，那么我们没有办法进行负载均衡
 *   4、如果某个节点崩溃我们不能切换到另外一台节点中，不能故障转移
 * 注意：
 *   如果需要解决上面的问题我们需要自己写一套管理代码来动态的切换数据源进行读写分离，故障转移以及负载均衡，因此这种
 *   方案通常我们在工作中不推荐使用
 */
@Service("sqlServiceImpl")
public class SqlServiceImpl implements SqlService {
    @Resource
    private MasterUserMapper masterUserMapper;
    @Resource
    private SlaveUserMapper slaveUserMapper;
    public List<User> select() {
        return slaveUserMapper.selectAll();
    }

    public int add(User user) {
        return masterUserMapper.insertSelective(user);
    }
}
