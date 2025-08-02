<template>
  <!-- 添加横向排列父容器 -->
  <div class="hack-container">
    <!-- 侧边栏（手风琴组件） -->
    <div class="accordion">
      <!-- hack -->
      <div class="tiem-title">
        <span>HACK</span>
      </div>

      <div class="accordion-item" @click="toggle('sqlModule')">
        <span>1: SQL Injection</span>
        <span :class="{ 'arrow-down': openKeys.sqlModule }">&gt;</span>
      </div>
      <transition name="slide">
        <div v-show="openKeys.sqlModule" class="content">
          <p>1:labs-1</p>
        </div>
      </transition>


      <div class="accordion-item" @click="toggle('fileUploadModule')">
        <span>2: File Upload</span>
        <span :class="{ 'arrow-down': openKeys.fileUploadModule }">&gt;</span>
      </div>
      <transition name="slide">
        <div v-show="openKeys.fileUploadModule" class="content">
          <a href="/hark/file_upload/file_upload_1">1: file_upload_1</a>
        </div>
      </transition>

    </div>

    <!-- 添加正文区域 -->
    <div class="main-content">
      <h1>HACK</h1>
          <hr>

          <h2>1: SQL Injection</h2>
          <ul>
            <li><a href="#sql-engineering">1:labs-1</a></li>

          </ul>
          <hr>

          <h2>2: File Upload</h2>
          <ul>
            <li><a href="/hark/file_upload/file_upload_1">1: file_upload_1</a></li>
          </ul>
          <hr>

    </div>
  </div>
</template>

<!-- 添加常规 script 标签定义组件名称 -->
<script>
export default {
  name: 'HarkPage' // 多词名称，满足 vue/multi-word-component-names 规则
}
</script>

<script setup>
import { ref } from 'vue';

// 删除 defineOptions 行，避免 no-undef 错误
const openKeys = ref({
  openClass: false,
  sqlModule: false,
  fileUploadModule: false,
});

const toggle = (key) => {
  openKeys.value[key] = !openKeys.value[key];
};
</script>

<style scoped>
    @import url('../../assets/css/hack.css');

    /* 禁止 span 文本被选择和编辑 */
    ::v-deep .accordion-item span {
        user-select: none; /* 核心属性：禁止文本选择 */
        -webkit-user-select: none; /* 兼容 Safari */
        -moz-user-select: none; /* 兼容 Firefox */
        cursor: default; /* 可选：鼠标指针变为默认样式（非文本选择样式） */
    }

    
body {
  font-family: Arial, sans-serif;
  line-height: 1.6;
  margin: 20px;
}

.container {
  max-width: 800px;
  margin: 0 auto;
}

h1 {
  font-size: 24px;
  color: #333;
}

.disclaimer {
  font-size: 14px;
  color: #666;
  margin-bottom: 20px;
  padding-left: 20px;
  border-left: 2px solid #ddd;
}

hr {
  border: none;
  height: 1px;
  background-color: #ddd;
  margin: 20px 0;
}

h2 {
  font-size: 18px;
  color: black;
  margin-top: 30px;
}

ul {
  list-style-type: disc;
  padding-left: 20px;
}

li a {
  text-decoration: none;
  color: #0056b3;
}

li a:hover {
  text-decoration: none;
}

</style>


