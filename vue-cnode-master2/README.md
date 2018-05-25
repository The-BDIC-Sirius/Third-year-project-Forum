# Vue.js打造一个开源的CNode社区

Vue.js打造一个开源的CNode社区，实现了浏览、发帖、收藏、回复、点赞、个人中心等等功能。

## 源代码
源代码地址：👉 https://github.com/microzz/vue-cnode

欢迎大家star和fork😄

## 预览
在线预览地址：👉 https://microzz.com/vue-cnode/


## 技术栈
* **Vuex**：Vuex，实现不同组件间的状态共享
* **vue-router**：页面路由切换
* **axios**：一个基于 `Promise` 的 HTTP 库，向后端发起请求。
* **Moment.js**：一个时间处理的库，方便对时间进行格式化成需要的格式，如主题、回复时间显示"* 分钟前、* 小时前、*天前"等等。
* **localStorage**：保存用户信息。
* **Canvas**：页面顶部小雪花效果。
* **SASS**(**SCSS**)：用SCSS做CSS预处理语言，有些地方很方便，个人很喜欢用。(详看👉[SASS用法指南](https://microzz.com/2017/03/18/sass/))

## 总结
> Flux 架构就像眼镜：您自会知道什么时候需要它。
3. Moment.js在Vue中用ES6的方式引入会有问题，可以尝试在main.js尝试这样`import moment from 'moment'` `Vue.prototype.moment = moment;`给Vue的原型上添加moment，这样就可以在Vue的实例中随意使用它了。
4. 项目结构如下图

![Vue-CNode by microzz.com](https://github.com/microzz/preview/blob/master/vue_cnode.png?raw=true)



## 时间轨迹
* **4.13**：基本功能完成，后续完成登录后的操作。

* **4.14**：完成登录以及侧边栏。

* **4.15**：增加收藏

* **4.16**：加入回复、单条回复、点赞。项目完成。


## About

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

