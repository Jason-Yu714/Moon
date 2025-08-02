<template>
  <div>
    <!-- 导航栏 -->
    <Navbar />
    
    <!-- 页面内容区域 -->
    <div class="main-content">
      <router-view />
    </div>
  </div>
</template>

<script>
import Navbar from './Navbar.vue'

export default {
  components: { Navbar },
  name: 'MainLayout'
}
</script>
