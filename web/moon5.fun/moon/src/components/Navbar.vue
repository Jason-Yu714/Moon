<template>
  <div class="menu-bar">
    <a class="menu-item" href="/port">Port</a>
    <a class="menu-item" href="/hack/hack">Hack</a>
    <!-- 修改：添加点击事件处理登出逻辑 -->
    <a 
      class="menu-item" 
      href="#" 
      style="margin-left: auto;" 
      @click.prevent="handleLogout" 
    >
      logout
    </a>
  </div>
</template>

<script>
export default {
  name: 'MainNavbar',
  methods: {
    // 新增：登出处理函数
    handleLogout() {
      // 1. 清除 localStorage 中存储的用户名和密码（如果有）
      localStorage.removeItem('savedUsername');
      localStorage.removeItem('savedPassword');
      
      // 2. 如果使用 sessionStorage 存储，也一并清除
      sessionStorage.removeItem('savedUsername');
      sessionStorage.removeItem('savedPassword');
      
      // 3. 重定向到登录页
      this.$router.push('/');
    }
  }
};
</script>

<style scoped>
@import url('../assets/css/index.css');
</style>