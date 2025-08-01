<template>
  <div class="container" id="app">
    <form class="form">
      <div class="header">Regist</div>

      <div class="flex-column"></div>
      <div class="inputForm">
        <i class="fa fa-user"></i>
        <input type="text" class="input" placeholder="Enter your Username" name="username" v-model="username">
      </div>

      <div class="inputForm">
        <i class="fa fa-envelope"></i>
        <input type="text" class="input" placeholder="Enter your Email" name="email" v-model="email">
      </div>

    <PasswordInput 
        placeholder="Enter your Password" 
        v-model="password2" 
        name="pwd1"
    />

    <PasswordInput 
        placeholder="Enter your Confirm Password" 
        v-model="password" 
        name="pwd2"
    />

      <div class="error-message" v-if="showError">{{ errorMessage }}</div>

      <button class="button-submit" @click.prevent="handleSubmit">Sign Up</button>
    </form>
  </div>
</template>

<script>
import PasswordInput from './PasswordInput.vue';



export default {
    components: { PasswordInput },
  name: 'RegistPage', // 多单词组件名，符合 ESLint 规则
  data() {
    return {
      username: '',
      email: '',
      password: '',
      password2: '',
      showError: false,
      errorMessage: ''
    }
  },
  methods: {
    handleSubmit() {
      // 密码验证逻辑
      if (this.password !== this.password2) {
        this.showError = true;
        alert("password is error")
        return;
      }

      // 发送注册请求
      this.$axios.post('http://127.0.0.1:9876/regist', {
        username: this.username,
        email: this.email,
        password: this.password
      })
      .then(response => {
        if (response.data.code === 1) {
          alert('Registration successful!');
          this.$router.push('/login'); // 注册成功跳转登录页
        } else {
          this.showError = true;
          alert(response.data.msg)
        }
      })
      .catch(error => {
        console.error('Registration error:', error);
        this.showError = true;
        alert("Network error, please try again")
      });
    }
  }
}
</script>

<style scoped>
/* 导入公共样式（与 Login 组件共享） */
@import url('../assets/css/login.css');

/* 组件专属样式（原 inline style） */
.form {
  padding-bottom: 10px;
}
.header {
  margin-bottom: 10px;
}
.error-message {
  color: red;
  font-size: 12px;
  margin-top: 5px;
  height: 16px;
}
</style>