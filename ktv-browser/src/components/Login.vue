<template>
  <div>

    <!-- 登陆模块：username, password, url, millseconds, pictureCode -->
    <div class="form"><center>

      <center><h2>KTV 智能选歌系统</h2></center>

      <el-input v-model="user.username" placeholder="请输入您的账号" maxlength="30" clearable>
        <template slot="prepend">账号</template>  
      </el-input><br><br>

      <el-input v-model="password" placeholder="请输入您的密码"  maxlength="20"  type ="password" clearable>
        <template slot="prepend">密码</template>
      </el-input><br><br> 

      <el-row>
        <img :src="url + millseconds" style="width: 90px; height: 38px;  vertical-align: middle" @click="getPictureCode()">&ensp;
        <el-input v-model="user.pictureCode" placeholder="请输入图片验证码" style="width: 70%;" maxlength="4" clearable/>
      </el-row><br>

      <el-switch v-model="user.admin" active-text="管理员登陆" inactive-text="用户登陆"/><br><br>

      <el-button type="primary" style="width: 100%" @click="login()">登陆</el-button>

      <span style="position:absolute;right: 0px">
        <el-button type="text" @click="showDialog(0)">忘记密码</el-button>
        <el-button type="text" @click="showDialog()">注册账户</el-button>
      </span>

    </center></div>

    <!-- 弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogShow" width="35%">
      
      <el-input v-model="user.username" placeholder="请输入您的账号" maxlength="30" style="width: 90%" clearable>
        <template slot="prepend">&emsp;账号&emsp;</template>
      </el-input><br><br>

      <el-input v-model="user.realName" placeholder="请输入您的真实姓名" maxlength="30" style="width: 90%" clearable>
          <template slot="prepend">真实姓名</template>
      </el-input><br><br>
      
      性别：&emsp;
      <el-radio v-model="user.userSex" label="1">男</el-radio>
      <el-radio v-model="user.userSex" label="0">女</el-radio>&emsp;&emsp;
      
      出生日期：&emsp;
      <el-date-picker
        v-model="birthday"
        type="date"
        placeholder="选择日期"
        style="width: 40%">
      </el-date-picker><br><br>
  
      <el-input v-model="password" placeholder="请设置您的密码"  maxlength="20"  type ="password"  style="width: 90%" clearable>
        <template slot="prepend">&emsp;密码&emsp;</template>
      </el-input><br><br> 

      <el-input v-model="rePassword" placeholder="再次输入您的密码"  maxlength="20"  type ="password" style="width: 90%" clearable>
        <template slot="prepend" >确认密码</template>
      </el-input><br><br><br>

      <img :src="url + millseconds" style="width: 90px; height: 38px;  vertical-align: middle" @click="getPictureCode()">&ensp;
      <el-input v-model="user.pictureCode" placeholder="请输入图片验证码" style="width: 30%;" maxlength="4" clearable/>
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <el-button type="primary" @click="go()">{{ dialogButton }}</el-button>

    </el-dialog>

 </div>
</template>
<script>
export default {
  data() {
    return {
      user: {
        username: '',
        realName: '',
        userSex: '1',
        birthday: '',
        password: '',
        admin: false,
        pictureCode: ''
      },

      url: '/user/getPictureCode.do?',
      millseconds: '',

      birthday: '',
      password: '',
      rePassword: '',

      dialogTitle: '',
      dialogButton: '',
      dialogShow: false,
      dialogType: 0, // 0：忘记密码；1：注册

      currentPage: true
    }
  },
  methods: {

    // type 为 0 时为忘记密码，否则为注册
    showDialog(type) {
      this.pictureCode = ''
      this.dialogShow = true
      if (type == 0) {
        this.dialogTitle = '忘记密码'
        this.dialogButton = '重置密码'
        this.dialogType = 0
      } else {
        this.password = ''
        this.rePassword = ''
        this.dialogTitle = '注册账户'
        this.dialogButton = '注册'
        this.dialogType = 1
      }
    },

    // 忘记密码或注册账户
    go() {
      if (!this.user.username) {
        this.$message.error('账号不能为空')
        return;
      } else if (!this.user.realName) {
        this.$message.error('真实姓名不能为空')
        return;
      } else if (!this.birthday) {
        this.$message.error('生日不能为空')
        return;
      } else if (!this.password) {
        this.$message.error('密码不能为空')
        return;
      } else if (!this.user.pictureCode) {
        this.$message.error('验证码不能为空')
        return;
      }

      if (this.password != this.rePassword) {
        this.$message.error('两次密码输入不相同，请重新输入')
        this.user.password = ''
        this.rePassword = ''
        return;
      }

      var url, msg 
      if (this.dialogType == 1) {
        url = '/user/register.do'
        msg = '注册成功'
      } else {
        url = '/user/resetPassword.do'
        msg = '重置密码成功'
      }

      var month = this.birthday.getMonth() + 1
      this.user.birthday = this.birthday.getFullYear() + '-' + month + '-' + this.birthday.getDate()
      this.user.password = this.md5(this.password)
      this.post(url, this.user, () => {
        this.$message.success(msg)
        this.dialogShow = false
      })
    },
    
    // 登陆
    login() {
      if (!this.user.username) {
        this.$message.error('账号不能为空')
        return;
      } else if (!this.password) {
        this.$message.error('密码不能为空')
        return;
      } else if (!this.user.pictureCode) {
        this.$message.error('验证码不能为空')
        return;
      } else {
        this.user.password = this.md5(this.password)
        this.post('/user/login.do', this.user, () => {
          this.currentPage = false
          this.$message.success('登陆成功')
          location = '/#/music'
        })
      }
    },

    // 获取图片验证码
    getPictureCode() {
      this.millseconds = new Date().getUTCMilliseconds()
    }
  },
  created() {
    document.onkeyup = e => {
      if (e.keyCode === 13 && this.currentPage) {
        if (this.dialogShow) {
          this.go()
        } else {
          this.login()
        }
      }
    }
  },
  mounted() {
    this.currentPage = true
  }
}
</script>
<style>
.form {
  position: absolute;
  top: 20%;
  left: 40%;
  width: 350px;
  vertical-align: middle
}
</style>