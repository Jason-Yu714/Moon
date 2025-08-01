<template>

    <div class="container" id="app">

        <form class="form" @submit.prevent="handleLogin">
            <div class="header">Login</div>

            <div class="flex-column">
            <label>Username </label></div>
            <div class="inputForm">
                <i class="fa fa-user"></i>
                <input type="text" class="input" placeholder="Enter your Username" name="username" v-model="username">
            </div>
    
            <div class="flex-column">
            <label>Password </label></div>
                <PasswordInput 
                    placeholder="Enter your Password" 
                    v-model="password" 
                />
            
            <div class="flex-row">
                    <div>
                        <label>
                            <input type="checkbox">
                                Remember me
                        </label>
                    </div>
                <span class="span">Forgot password?</span>
            </div>

            <button class="button-submit">Sign In</button>

            <p class="p">
                Don't have an account? 
                <!-- 将 a 标签替换为 router-link，使用路由跳转 -->
                <router-link to="/regist">  <!-- 修改：使用路由链接 -->
                    <input type="button" class="button-sign" value="Sign Up">
                </router-link>
                <!-- 移除原 a 标签：<a href="regist.html">...</a> -->
            </p>


        </form>





    </div>
</template>

<script>
import { togglePasswordVisibility } from '../assets/js/PasswdClick';
import PasswordInput from './PasswordInput.vue';

export default {
  name: 'LoginPage',
  components: { PasswordInput },
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    togglePasswordVisibility,
    handleLogin() {
      // 发送登录请求到后端接口
        this.$axios.post('http://127.0.0.1:9876/login', {
        username: this.username,
        password: this.password
      })
      .then(response => {
        // 后端返回 code:1 表示登录成功
        if (response.data.code === 1) {
          alert('登录成功！');
          // 跳转到 test.html 页面
          this.$router.push('/test')
        } else {
          alert('登录失败：' + response.data.msg);
          this.password = ''; // 清空密码
        }
      })
      .catch(error => {
        console.error('登录请求失败:', error);
        alert('网络错误，请检查后端服务');
        this.password = ''; // 清空密码
      });
    }
  }
}
</script>

<style scoped>

        @import url('../assets/css/login.css');

        .form {
            padding-bottom: 0px;
        }
</style>
