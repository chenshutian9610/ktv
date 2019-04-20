// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

/******************************************************************************/

// 导入 element-ui 及其样式
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 导入 md5，axios
import md5 from 'js-md5';
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(ElementUI)
Vue.use(VueAxios, axios)

Vue.prototype.md5 = md5

// 退出登陆
Vue.prototype.logout = function () {
  this.axios.post('/user/logout.do').then((res) => {
    if (res.data.success) {
      this.$message.success('登出成功')
      location = '/'
    }
  })
}

// 将对象转化为请求参数格式的字符串: x=1&y=2&z=3
Vue.prototype.queryString = function (obj) {
  if (obj == null) {
    return ''
  }
  var result = []
  var i = 0
  for (var key in obj) {
    result[i++] = key + '=' + obj[key]
  }
  return result.join('&')
}

// 复制对象
Vue.prototype.copy = function (obj) {
  var o = []
  for (var key in obj) {
    o[key] = obj[key]
  }
  return o
}

// 页面加载状态
Vue.prototype.loading = function (before) {
  if (before !== undefined) {
    before()
  }
  const loading = this.$loading({
    lock: true,
    text: 'Loading',
    spinner: 'el-icon-loading',
    background: 'rgba(0, 0, 0, 0.7)'
  })
  return loading
}

// 异步请求
Vue.prototype.post = function (url, data, success, fail) {
  this.axios.post(url, this.queryString(data)).then(
    (res) => {
      if (res.data.success) {
        if (success) {
          success(res.data.response)
        }
      } else {
        if (fail) {
          fail(res.data.message)
        } else {
          this.$message.error(res.data.message ? res.data.message : '操作失败，请检查您的操作是否合法')
        }
      }
    },
    () => {
      this.$message.error('网络开了点小差')
    }
  )
}

/******************************************************************************/

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
