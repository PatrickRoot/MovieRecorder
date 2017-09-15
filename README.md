**恢复更新**

由于`GitHub`国内访问不畅，所以两个 Git 仓库作同步：
Git@OSC：https://git.oschina.net/nianqinianyi/MovieRecorder
GitHub：https://github.com/PatrickRoot/MovieRecorder

# 目标
实现一个跨平台的 app，记录电影、电视剧的观看进度，数据存储在服务器，目前暂定仍由服务器提供几个接口，存储在 MySQL 数据库中。

# 计划清单
**React Native 实现的跨平台 App:**

- [x] 完成代码初始化，基本的依赖和配置完成
- [ ] 完成 App 的主界面设计及开发
- [ ] 完成登录功能的开发
- [ ] 完成电影·已观影列表
- [ ] 完成电影·新增已观影/更新已观影信息
- [ ] 完成电影·重新观看
- [ ] 完成电视剧·观影列表，正在追/暂不追切换
- [ ] 完成电视剧·新增电视剧/更新电视剧信息
- [ ] 完成电视剧·进度更新

**服务器端：**

- [ ] 完成代码初始化，基本的依赖和配置完成
- [ ] 完成登录功能开发
- [ ] 完成电影·列表接口的开发
- [ ] 完成电影·新增、更新信息接口开发
- [ ] 完成电视剧·列表接口的开发
- [ ] 完成电视剧·新增、更新信息接口的开发
- [ ] 完成电视剧·更新进度接口的开发

# 使用到的技术&开源技术
1. [react-native](https://github.com/facebook/react-native)
2. [antd-mobile](https://mobile.ant.design/index-cn)
3. [react-navigation](https://github.com/react-community/react-navigation)
4. [redux](https://github.com/reactjs/redux)
5. [moment](https://github.com/moment/moment/)
5. [个推推送](https://github.com/GetuiLaboratory/react-native-getui)

# 旧版本

之前的旧版本放到了分支[archinve](https://github.com/PatrickRoot/MovieRecorder/tree/archive)以作纪念，旧版本内容：
1. Java 实现的电影记录，主要用到：Swing、Mybatis 和 sqlite
2. Python3 实现的第二版电影记录，主要用到：PyQt4、sqlite
3. Python3 实现的电视剧记录，主要用到：PyQt4、sqlite



