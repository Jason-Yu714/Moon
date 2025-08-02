<template>

    <div class="container" id="app">

        <form class="form" @submit.prevent="handleLogin" autocomplete="off">
            <div class="header">Login</div>

            <div class="flex-column">
            <label>Username </label></div>
            <div class="inputForm">
                <i class="fa fa-user"></i>
                <!-- 用户名输入框添加 autocomplete="off" -->
                <input 
                type="text" 
                class="input" 
                placeholder="Enter your Username" 
                name="username" 
                v-model="username"
                autocomplete="off"  
                >
            </div>

            <div class="flex-column">
            <label>Password </label>
            <!-- 修复：移除末尾多余的 '<' 字符 -->
            <PasswordInput 
                placeholder="Enter your Password" 
                v-model="password" 
                autocomplete="new-password"  
            />
            </div>
            
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
      username: '',  // 确保初始化为空字符串
      password: ''   // 确保初始化为空字符串
    }
  },
  methods: {
    togglePasswordVisibility,
    handleLogin() {
      this.$axios.post('http://127.0.0.1:9876/login', {
        username: this.username,
        password: this.password
      })
      .then(response => {
        if (response.data.code === 1) {
          // 仅在勾选"Remember me"时存储凭据
          if (this.rememberMe) {
            localStorage.setItem('savedUsername', this.username);
            localStorage.setItem('savedPassword', this.password);
          }
          window.location.href = 'http://localhost:5173'; 
        } else {
          alert('登录失败：' + response.data.msg);
          this.password = '';
        }
      })
      .catch(error => {
        console.error('登录请求失败:', error);
        alert('网络错误，请检查后端服务');
        this.password = '';
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
